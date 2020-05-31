package com.zzgs.springbootdemo.Service.Impl;

import cn.hutool.core.date.DateUtil;
import com.zzgs.springbootdemo.Bean.Department;
import com.zzgs.springbootdemo.Mapper.DepartmentDao;
import com.zzgs.springbootdemo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-31 20:38:32
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Department> findAllDepartment() {
        return departmentDao.findAllDepartment();
    }

    /**
     * 插入一条部门数据
     *
     * @param department 部门对象
     * @return 受影响行数
     */
    @Override
    public Integer addDepartment(Department department) {
        String now = DateUtil.now();
        department.setDepartmentCreateTime(now);
        return departmentDao.addDepartment(department);
    }
}
