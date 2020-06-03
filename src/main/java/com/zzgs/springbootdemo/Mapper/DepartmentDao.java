package com.zzgs.springbootdemo.Mapper;

import com.zzgs.springbootdemo.Bean.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-31 20:31:43
 */
@Mapper
public interface DepartmentDao {


    /**
     * 查询所有的部门列表
     *
     * @return 部门列表
     */
    @Select("select * from department where department_del_flag = 0 ")
    List<Department> findAllDepartment();

    /**
     * 插入一条部门数据
     *
     * @param department 部门对象
     * @return 受影响行数
     */
    @Insert("insert into " +
            "department(department_name,department_create_time,department_del_flag) " +
            "values(#{departmentName},#{departmentCreateTime},0)")
    Integer addDepartment(Department department);

    /**
     * 根据部门名称查询部门信息
     *
     * @param departmentName 部门名称
     * @return 部门对象
     */
    @Select("select * from department where department_name = #{departmentName}")
    Department findDeptByDeptName(@Param("departmentName") String departmentName);
}
