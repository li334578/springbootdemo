package com.zzgs.springbootdemo.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.zzgs.springbootdemo.Bean.User;
import com.zzgs.springbootdemo.Dto.UserDto;
import com.zzgs.springbootdemo.Mapper.UserDao;
import com.zzgs.springbootdemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Set;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-30 14:08:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    /**
     * 根据用户id查询用户的职位信息
     *
     * @param userId 用户id
     * @return 职位信息
     */
    @Override
    public Set<String> findUserDepartmentPositionByID(Integer userId) {
        return userDao.findUserDepartmentPositionByID(userId);
    }

    /**
     * 查询所有的用户信息
     *
     * @return 用户列表
     */
    @Override
    public List<UserDto> findAll() {
        return userDao.findAllUserDto();
    }

    /**
     * 根据 用户姓名和部门id分页查询用户信息
     *
     * @param pageNum      当前页数
     * @param pageSize     每页显示条数
     * @param name         用户姓名
     * @param departmentId 部门id
     * @return 用户列表
     */
    @Override
    public List<User> findByNameAndDepartmentId(Integer pageNum, Integer pageSize, String name, Integer departmentId) {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.findByNameAndDepartmentId(name, departmentId);
    }

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
    @Override
    public String findRepeatability(String name, String userJobNumber, String userName, String userEmail, String userPhone) {
        User user;
        user = userDao.findUserByName(name);
        if (user != null) {
            return "员工姓名已存在";
        }
        user = userDao.findUserByUserJobNumber(userJobNumber);
        if (user != null) {
            return "员工工号已存在";
        }
        user = userDao.findUserByUserName(userName);
        if (user != null) {
            return "员工账户名已存在";
        }
        user = userDao.findUserByUserEmail(userEmail);
        if (user != null) {
            return "员工邮箱已存在";
        }
        user = userDao.findUserByUserPhone(userPhone);
        if (user != null) {
            return "员工手机号已存在";
        }
        return null;
    }

    /**
     * 添加员工
     *
     * @param user 员工对象
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer addUser(User user) {
        try {
            if (userDao.addUser(user) == 1) {
                // 添加成功 添加职位信息
                if (userDao.addUserPosition(user.getUserId(), 0) == 1) {
                    return 1;
                } else {
                    throw new RuntimeException("用户职位信息没有添加成功");
                }
            } else {
                // 没有添加成功
                throw new RuntimeException("用户没有添加成功");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }
}
