package com.zzgs.springbootdemo.Mapper;

import com.zzgs.springbootdemo.Bean.User;
import com.zzgs.springbootdemo.Dto.UserDto;
import org.apache.ibatis.annotations.*;

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

    /**
     * 查询所有的用户信息
     *
     * @return 用户信息Dto列表
     */
    @Select("select user.*,department.department_name departName " +
            "from user left join department " +
            "on user.department_id = department.department_id " +
            "where user_del_flag = 0 ")
    List<UserDto> findAllUserDto();

    @Select(value = {
            "<script>select * from user " +
                    "<where> user_del_flag = 0 " +
                    "<if test = \"name != null \"> and name like concat('%',#{name},'%') </if>" +
                    "<if test = \"departmentId != null \"> and department_id = #{departmentId} </if>" +
                    "</where>" +
                    "</script>"
    })
    List<User> findByNameAndDepartmentId(@Param("name") String name, @Param("departmentId") Integer departmentId);

    /**
     * 根据员工姓名查询用户信息
     *
     * @param name 员工姓名
     * @return 用户信息
     */
    @Select("select * from user where name = #{name}")
    User findUserByName(@Param("name") String name);

    /**
     * 根据员工工号查询用户信息
     *
     * @param userJobNumber 员工工号
     * @return 用户信息
     */
    @Select("select * from user where user_job_number = #{userJobNumber}")
    User findUserByUserJobNumber(@Param("userJobNumber") String userJobNumber);

    /**
     * 根据员工邮箱查询用户信息
     *
     * @param userEmail 员工邮箱
     * @return 用户信息
     */
    @Select("select * from user where user_email = #{userEmail}")
    User findUserByUserEmail(@Param("userEmail") String userEmail);

    /**
     * 根据员工手机号查询用户信息
     *
     * @param userPhone 员工手机号
     * @return 用户信息
     */
    @Select("select * from user where user_phone = #{userPhone}")
    User findUserByUserPhone(@Param("userPhone") String userPhone);


    /**
     * 新增员工对象
     *
     * @param user 员工对象
     * @return 受影响行数
     */
    @Insert("insert into user(name,user_name,user_password,user_email,user_phone," +
            "user_job_number,user_entry_time,user_del_flag,department_id) " +
            "values(#{name},#{userName},#{userPassword},#{userEmail}," +
            "#{userPhone},#{userJobNumber},#{userEntryTime},#{userDelFlag},#{departmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    Integer addUser(User user);

    /**
     * @param userId                 用户id
     * @param department_position_id 职位id
     * @return 受影响行数
     */
    @Insert("insert into user_department_poistion(user_id,department_position_id) " +
            "values(#{userId},#{department_position_id})")
    Integer addUserPosition(@Param("userId") Integer userId, @Param("department_position_id") Integer department_position_id);

}
