package com.zzgs.springbootdemo.Controller;

import cn.hutool.core.date.DateUtil;
import com.zzgs.springbootdemo.Bean.Department;
import com.zzgs.springbootdemo.Bean.ResultBean;
import com.zzgs.springbootdemo.Bean.User;
import com.zzgs.springbootdemo.Dto.UserDto;
import com.zzgs.springbootdemo.Service.DepartmentService;
import com.zzgs.springbootdemo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-29 19:37:43
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/login")
    public String login() {
        return "pages/login";
    }

    /**
     * 登录认证
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/authentication")
    @ResponseBody
    public ResultBean authentication(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        ResultBean resultBean = new ResultBean();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException uae) {
            //账户不存在 未知账户
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("账户不存在");
        } catch (IncorrectCredentialsException ice) {
            //密码错误
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("密码错误");
        }
        if (subject.isAuthenticated()) {
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("请求成功");
        }
        return resultBean;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null) {
            model.addAttribute("user", userService.findUserByUserName(subject.getPrincipal().toString()));
        }
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "pages/login";
    }

    @RequestMapping("/member")
    public String member(Model model,
                         @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(required = false, defaultValue = "5", value = "pageSize") Integer pageSize,
                         @RequestParam(required = false, value = "name") String name,
                         @RequestParam(required = false, value = "departmentId") Integer departmentId) {
        Subject subject = SecurityUtils.getSubject();
        // 根据部门和名字分页查询部门
        List<UserDto> userList = userService.findAll();
//        List<User> userList = userService.findByNameAndDepartmentId(pageNum, pageSize, name, departmentId);
        ResultBean<Map<String, Object>> resultBean = new ResultBean();
        Map<String, Object> map = new HashMap<>();
        List<Department> departmentList = departmentService.findAllDepartment();
        map.put("userList", userList);
        map.put("departmentList", departmentList);
        resultBean.setData(map);
        log.info(resultBean.toString());
        model.addAttribute("user", userService.findUserByUserName(subject.getPrincipal().toString()));
        model.addAttribute("ResultBean", resultBean);
        return "pages/member";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public ResultBean addUser(@RequestParam("deptId") Integer deptId,
                          @RequestParam("name") String name,
                          @RequestParam("userJobNumber") String userJobNumber,
                          @RequestParam("userPassword") String userPassword,
                          @RequestParam("userName") String userName,
                          @RequestParam("userEmail") String userEmail,
                          @RequestParam("userPhone") String userPhone) {
        // 对密码进行加密
        SimpleHash hash_account_password = new SimpleHash("MD5", userPassword, userName, 10);
        User user = new User(null, name, userName, hash_account_password.toString(),
                userEmail, userPhone, userJobNumber, DateUtil.now(), 0, deptId);
        ResultBean resultBean = new ResultBean();
        if (userService.addUser(user)==0){
            // 添加失败
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("添加失败,数据库出现异常请重试");
        }else {
            // 添加成功
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("添加成功,点击确定刷新页面");
        }
        return resultBean;
    }

    @RequestMapping("/addUserRepeatabilityCheck")
    @ResponseBody
    public ResultBean addUserRepeatabilityCheck(@RequestParam("name") String name,
                                                @RequestParam("userJobNumber") String userJobNumber,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("userEmail") String userEmail,
                                                @RequestParam("userPhone") String userPhone) {
        ResultBean resultBean = new ResultBean();
        String result = userService.findRepeatability(name, userJobNumber, userName, userEmail, userPhone);
        if (result == null) {
            // 没有重复项
            resultBean.setMsg("可以新增");
            resultBean.setCode(ResultBean.SUCCESS);
        } else {
            // 有重复项
            resultBean.setMsg(result);
            resultBean.setCode(ResultBean.FAIL);
        }
        return resultBean;
    }
}
