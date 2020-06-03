package com.zzgs.springbootdemo.Controller;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.PathVariable;
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
        // 根据部门和名字分页查询员工
        List<UserDto> userList = userService.findAll();
//        List<User> userList = userService.findByNameAndDepartmentId(pageNum, pageSize, name, departmentId);
        ResultBean<Map<String, Object>> resultBean = new ResultBean();
        Map<String, Object> map = new HashMap<>();
        List<Department> departmentList = departmentService.findAllDepartment();
        map.put("userList", userList);
        map.put("departmentList", departmentList);
        resultBean.setData(map);
//        PageInfo pageInfo = new PageInfo(userList);
        model.addAttribute("user", userService.findUserByUserName(subject.getPrincipal().toString()));
//        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("ResultBean", resultBean);
        return "pages/member";
    }

    /**
     * 新增用户
     *
     * @param deptId        部门id
     * @param name          员工姓名
     * @param userJobNumber 用户工号
     * @param userPassword  用户密码
     * @param userName      用户名
     * @param userEmail     用户邮箱
     * @param userPhone     用户手机号
     * @return 处理结果
     */
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
        if (userService.addUser(user) == 0) {
            // 添加失败
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("添加失败,数据库出现异常请重试");
        } else {
            // 添加成功
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("添加成功,点击确定刷新页面");
        }
        return resultBean;
    }

    /**
     * 更新用户信息
     *
     * @param deptId        部门id
     * @param name          员工姓名
     * @param userJobNumber 用户工号
     * @param userName      用户账户名
     * @param userEmail     用户邮箱
     * @param userPhone     用户手机号
     * @return 处理结果
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultBean updateUser(@RequestParam("userId") Integer userId,
                                 @RequestParam("deptId") Integer deptId,
                                 @RequestParam("name") String name,
                                 @RequestParam("userJobNumber") String userJobNumber,
                                 @RequestParam("userName") String userName,
                                 @RequestParam("userEmail") String userEmail,
                                 @RequestParam("userPhone") String userPhone) {
        User user = new User(userId, name, userName, null, userEmail, userPhone, userJobNumber, null, null, deptId);
        ResultBean resultBean = new ResultBean();
        if (userService.updateUser(user) == 1){
            // 修改成功
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("修改用户信息成功");
        }else {
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("修改用户信息失败");
        }
        return resultBean;
    }

    /**
     * 用户的重复性校验
     *
     * @param name          姓名
     * @param userJobNumber 工号
     * @param userName      用户名
     * @param userEmail     用户邮箱
     * @param userPhone     用户邮箱
     * @return 处理结果
     */
    @RequestMapping("/UserRepeatabilityCheck")
    @ResponseBody
    public ResultBean addUserRepeatabilityCheck(@RequestParam(value = "userId", required = false) Integer userId,
                                                @RequestParam("name") String name,
                                                @RequestParam("userJobNumber") String userJobNumber,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("userEmail") String userEmail,
                                                @RequestParam("userPhone") String userPhone) {
        ResultBean resultBean = new ResultBean();
        String result;
        if (userId == null) {
            result = userService.findRepeatability(name, userJobNumber, userName, userEmail, userPhone);
        } else {
            result = userService.findRepeatability(userId, name, userJobNumber, userName, userEmail, userPhone);
        }
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

    /**
     * 删除员工信息
     *
     * @param userId 员工id
     * @return 处理结果
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public ResultBean delUser(@RequestParam("userId") Integer userId) {
        ResultBean resultBean = new ResultBean();
        if (userService.delUser(userId) == 1) {
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("删除成功");
        } else {
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("删除失败，请重试");
        }
        return resultBean;
    }

    /**
     * 跳转编辑员工信息页面
     *
     * @param userId 用户id
     * @param model  页面模型
     * @return 编辑员工信息页面
     */
    @RequestMapping("/editUser/{userId}")
    public String editUser(@PathVariable("userId") Integer userId, Model model) {
        log.info("被访问了 用户id是" + userId);
        // 查询用户信息
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        // 查询部门列表
        List<Department> departmentList = departmentService.findAllDepartment();
        model.addAttribute("departmentList", departmentList);
        return "pages/editUser";
    }
}
