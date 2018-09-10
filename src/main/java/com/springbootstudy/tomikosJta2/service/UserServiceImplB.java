package com.springbootstudy.tomikosJta2.service;

import com.springbootstudy.demo.Annotation.SystemServiceLog;
import com.springbootstudy.tomikosJta2.entity.User;
import com.springbootstudy.tomikosJta2.mapper.User.UserMapperB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户信息处理实现
 * @Author：pengrj
 * @Date : 2018/7/22 0022 15:22
 * @version:1.0
 */
@Service
public class UserServiceImplB implements UserServiceB {


    /***
      * 采用jpa方式与数据库进行交互
      */
    @Autowired
    private UserRepositoryB userRepository;

    /**
      * 采用Mybatis的方式与数据库进行交互
      */
    @Autowired
    private UserMapperB userMapper;

    @Override
    public List<User> getAllUsersByMybatis() {
        return userMapper.getAllUsers();
    }

    //Mybatis方式添加用户
    @Override
    public Integer saveorupdateUserB(User user) {
        return userMapper.saveorupdateUserB(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public List<User> getAllUsersByLocation(String location) {
        return userRepository.findByLocation(location);
    }


    @Override
    @SystemServiceLog(description = "getUserInfoById :servie")
    public User getUserById(Integer id) {

        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Integer id) {
       userRepository.deleteById(id);
    }

    @Override
    public User saveorupdateUser(User user) {
      return   userRepository.save(user);
    }



}


