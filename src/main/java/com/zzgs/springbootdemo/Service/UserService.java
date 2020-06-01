package com.zzgs.springbootdemo.Service;

import com.zzgs.springbootdemo.Bean.User;
import com.zzgs.springbootdemo.Dto.UserDto;

import java.util.List;
import java.util.Set;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-30 14:06:50
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User findUserByUserName(String userName);

    /**
     * 根据用户id查询用户的职位信息
     *
     * @param userId 用户id
     * @return 职位信息
     */
    Set<String> findUserDepartmentPositionByID(Integer userId);

    /**
     * 查询所有的用户信息
     *
     * @return 用户列表
     */
    List<UserDto> findAll();

    /**
     * 根据 用户姓名和部门id分页查询用户信息
     *
     * @param pageNum      当前页数
     * @param pageSize     每页显示条数
     * @param name         用户姓名
     * @param departmentId 部门id
     * @return 用户列表
     */
    List<User> findByNameAndDepartmentId(Integer pageNum, Integer pageSize, String name, Integer departmentId);

    /**
     * 添加用户时的重复性校验
     *
     * @param name          员工姓名
     * @param userJobNumber 员工工号
     * @param userName      员工用户名
     * @param userEmail     员工邮箱
     * @param userPhone     员工手机号
     * @return 重复性信息
     */
    String findRepeatability(String name, String userJobNumber, String userName, String userEmail, String userPhone);

    /**
     * 添加员工
     *
     * @param user 员工对象
     * @return 受影响行数
     */
    Integer addUser(User user);
}
