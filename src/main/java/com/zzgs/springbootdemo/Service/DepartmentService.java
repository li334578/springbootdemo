package com.zzgs.springbootdemo.Service;

import com.zzgs.springbootdemo.Bean.Department;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-31 20:38:24
 */
public interface DepartmentService {

    /**
     * 查询所有的部门列表
     *
     * @return 部门列表
     */
    List<Department> findAllDepartment();

    /**
     * 插入一条部门数据
     *
     * @param department 部门对象
     * @return 受影响行数
     */
    Integer addDepartment(Department department);
}
