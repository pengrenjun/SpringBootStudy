package com.springbootstudy.demo.controller.templateController;

import com.springbootstudy.demo.entity.User;
import com.springbootstudy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 使用Freemarker模板引擎渲染web视图
 * @NOTE :         返回视图的路径 由return返回的字符串决定
 * @Author：pengrj
 * @Date : 2018/8/26 0026 20:46
 * @version:1.0
 */

@Controller
@RequestMapping("/freeMarkerTemplates")
public class FreeMarkerController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userInfoA")
    public String  getUserByIdA(@RequestParam("id") Integer id,Map<String,User> userMap){

        User user=userService.getUserById(id);
        userMap.put("user",user);

        return "freeMarkerTemplates/userInfo";
    }

    @RequestMapping("/userInfoB")
    public String  getUserByIdB(@RequestParam("id") Integer id,Map<String,User> userMap){

        User user=userService.getUserById(id);
        userMap.put("user",user);

        return "userInfo";
    }

    @RequestMapping("/getAllUsers")
    public String  getAllUsers(Map<String,List<User>> userMap){

        List<User> userList=userService.getAllUsers();
        userMap.put("userList",userList);
        return "freeMarkerTemplates/userInfoList";
    }
}
