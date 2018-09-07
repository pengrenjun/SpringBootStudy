package com.springbootstudy.index.controller;

import com.springbootstudy.dataSourceTest.mapper.User.UserMapper2;
import com.springbootstudy.demo.entity.User;
import com.springbootstudy.demo.mapper.User.UserMapper;
import com.springbootstudy.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Slf4j
public class indexController {

    @Autowired
    private UserService userService;

    @Autowired
    private com.springbootstudy.dataSourceTest.service.UserService userService2;

    @PostMapping("/addUserIntoTwoDataBase")
    @Transactional(transactionManager = "test1TransactionManager")
    public String addUserIntoTwoDataBase(){

        User user=new User();

        com.springbootstudy.dataSourceTest.entity.User user2=new com.springbootstudy.dataSourceTest.entity.User();

        user.setName("xiaoTwoTest");
        user.setLocation("shenzheng");

        user2.setName("xiaoTwoTest2");
        user2.setLocation("shenzheng2");

        /*实现分库存储数据*/
        userService2.saveorupdateUserB(user2);

        int i=1/0;
        userService.saveorupdateUserB(user);


        return "addUserIntoTwoDataBase test OK !";

    }



}
