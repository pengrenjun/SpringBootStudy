package com.springbootstudy.dataSourceTest.service;

import com.springbootstudy.dataSourceTest.entity.User;

import java.util.List;

public interface UserService {




    //Mybatis�ķ�ʽ
    List<User> getAllUsersByMybatis();

    //Mybatis方式进行数据添加
    Integer saveorupdateUserB(User user);

   List<User> getAllUsers();

    List<User> getAllUsersByLocation(String location);

    User getUserById(Integer id);

    void deleteUserById(Integer id);

    User saveorupdateUser(User user);



}
