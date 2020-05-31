package com.zzgs.springbootdemo.Controller;

import com.zzgs.springbootdemo.Bean.Department;
import com.zzgs.springbootdemo.Bean.ResultBean;
import com.zzgs.springbootdemo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/addDepartment")
    @ResponseBody
    public ResultBean addDepartment(@RequestParam("departmentName") String departmentName) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        Integer lineNum = departmentService.addDepartment(department);
        ResultBean resultBean = new ResultBean();
        if (lineNum != 0) {
            resultBean.setCode(ResultBean.SUCCESS);
            resultBean.setMsg("添加成功");
            return resultBean;
        }else {
            resultBean.setCode(ResultBean.FAIL);
            resultBean.setMsg("添加失败");
            return resultBean;
        }
    }
}
