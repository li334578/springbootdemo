package com.zzgs.springbootdemo.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.zzgs.springbootdemo.Bean.User;
import com.zzgs.springbootdemo.Mapper.UserDao;
import com.zzgs.springbootdemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<User> findAll() {
        return userDao.findAll();
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
        PageHelper.startPage(pageNum,pageSize);
        return userDao.findByNameAndDepartmentId(name,departmentId);
    }
}
