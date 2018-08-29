package com.springbootstudy.demo.controller.templateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Date;
import java.util.Map;

/**
 * @Description:    登录模板Template 界面 访问controller 注解需要使用@Controller
 * @Author：pengrj
 * @Date : 2018/7/26 0026 22:00
 * @version:1.0
 */
@Controller
@RequestMapping("/templates")
public class LogInController {

    @RequestMapping("/hello")
    public String logIn(Map<String,Object> map){
        map.put("nowDate", new Date());
        return "hello";
    }


}
