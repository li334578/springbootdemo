package com.zzgs.springbootdemo.Controller;

import com.zzgs.springbootdemo.Bean.Department;
import com.zzgs.springbootdemo.Bean.ResultBean;
import com.zzgs.springbootdemo.Service.DepartmentService;
import com.zzgs.springbootdemo.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-31 22:35:52
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    UserService userService;

    @RequestMapping("/addDepartment")
    @ResponseBody
    public ResultBean addDepartment(@RequestParam("departmentName") String departmentName) {
        Department department = new Department();
        department.setDepartmentName(departmentName);

        ResultBean resultBean = new ResultBean();
        // 查询部门是否已存在
        if (departmentName.equals("") || departmentService.findDeptByDeptName(departmentName) != null) {
            // 部门为空或已存在
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("部门已存在");
            return resultBean;
        }
        if (departmentService.addDepartment(department) != 0) {
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("添加成功");
            return resultBean;
        } else {
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("添加失败");
            return resultBean;
        }
    }

    @RequestMapping("/department")
    public String department(Model model) {
        Subject subject = SecurityUtils.getSubject();
        List<Department> departmentList = departmentService.findAllDepartment();
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("user", userService.findUserByUserName(subject.getPrincipal().toString()));
        return "pages/department";
    }
}
