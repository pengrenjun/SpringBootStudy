package com.springbootstudy.demo.service;

import com.springbootstudy.demo.entity.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    //Mybatis�ķ�ʽ
    List<User> getAllUsersByMybatis();

    List<User> getAllUsersByLocation(String location);

    User getUserById(Integer id);

    void deleteUserById(Integer id);

    User saveorupdateUser(User user);


}
