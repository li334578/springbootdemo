package com.zzgs.springbootdemo.Mapper;

import com.zzgs.springbootdemo.Bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-30 14:26:35
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Select("select * from user where user_name = #{userName}")
    User findUserByUserName(@Param("userName") String userName);

    /**
     * 根据用户id查询用户的职位信息
     *
     * @param userId 用户id
     * @return 用户的职位列表
     */
    @Select("select department_position_name from user " +
            "left join user_department_poistion on user.user_id = user_department_poistion.user_id " +
            "left join department_position on user_department_poistion.department_position_id = department_position.department_position_id " +
            "where user.user_id = #{userId} " +
            "and department_position.department_position_del_flag = 0 ")
    Set<String> findUserDepartmentPositionByID(@Param("userId") Integer userId);

    /**
     * 查询所有的用户信息
     *
     * @return 用户信息列表
     */
    @Select("select * from user where user_del_flag = 0 ")
    List<User> findAll();

    @Select(value = {
            "<script>select * from user " +
                    "<where> user_del_flag = 0 " +
                    "<if test = \"name != null \"> and name like concat('%',#{name},'%') </if>" +
                    "<if test = \"departmentId != null \"> and department_id = #{departmentId} </if>" +
                    "</where>" +
                    "</script>"
    })
    List<User> findByNameAndDepartmentId(@Param("name") String name, @Param("departmentId") Integer departmentId);
}
