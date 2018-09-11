package com.springbootstudy.index.controller;

import com.springbootstudy.dataSourceTest.mapper.User.UserMapper2;
import com.springbootstudy.demo.entity.User;
import com.springbootstudy.demo.mapper.User.UserMapper;
import com.springbootstudy.demo.service.UserService;
import com.springbootstudy.tomikosJta1.service.UserServiceA;
import com.springbootstudy.tomikosJta2.service.UserServiceB;
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


    //Springboot+atomikos+jta测试建立的service
    @Autowired
    private UserServiceA serviceA;
    @Autowired
    private UserServiceB serviceB;

    /**
     * @methodDesc:这个方法里面涉及到了对两个数据库的跨库操作,而事务管理只是指定了数据源1的事务管理器，
     *  当数据源2写入数据之后是无法通过数据源1的事物管理器对数据源2进行事务回滚处理的
     */
    @PostMapping("/addUserIntoTwoDataBase")
    @Transactional(transactionManager = "test1TransactionManager",rollbackFor = Exception.class)
    public String addUserIntoTwoDataBase(){

        User user=new User();

        com.springbootstudy.dataSourceTest.entity.User user2=new com.springbootstudy.dataSourceTest.entity.User();

        user.setName("xiaoTwoTest");
        user.setLocation("shenzheng");

        user2.setName("xiaoTwoTest2");
        user2.setLocation("shenzheng2");

        /*实现分库存储数据*/
        userService2.saveorupdateUserB(user2);
        try {
            int i=1/0;
        } catch (Exception e) {
            throw  new RuntimeException("多数据事务回滚测试");
        }

        userService.saveorupdateUserB(user);


        return "addUserIntoTwoDataBase test OK !";

    }


    /**
     * 基于Springboot+atomikos+jta的事务分布式管理测试
     * @return
     */
    @PostMapping("/addUserIntoTwoDataBaseJta")
    @Transactional(transactionManager = "transactionalManager1")
    public String addUserIntoTwoDataBaseJta(){

        com.springbootstudy.tomikosJta1.entity.User user=new com.springbootstudy.tomikosJta1.entity.User();
        com.springbootstudy.tomikosJta2.entity.User user2=new com.springbootstudy.tomikosJta2.entity.User();

        user.setName("xiaoTwoTest");
        user.setLocation("shenzheng");

        user2.setName("xiaoTwoTest2");
        user2.setLocation("shenzheng2");

        /*实现分库存储数据*/
        serviceA.saveorupdateUserB(user);


        try {
            int i=1/0;
        } catch (Exception e) {
            throw new RuntimeException("多数据源回滚测测试");
        }

        serviceB.saveorupdateUserB(user2);

        return "addUserIntoTwoDataBaseJta test OK !";

    }



}
