package com.springbootstudy.demo.controller;

import com.springbootstudy.demo.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:   项目启动描述
 * @Author：pengrj
 * @Date : 2018/7/22 0022 11:14
 * @version:1.0
 */

@RestController
public class ProjectDescriptionController  {

    @Autowired
    private Project project;


    @RequestMapping(value = "/project", method= RequestMethod.GET)
    public String getProjectDescription(){
        return project.getContent();
    }

    @GetMapping(value="/projectA")
    public String getA (){
        return project.getContent();
    }

    @RequestMapping(value = {"/projectB/{id}","/projectC/{id}"},method = RequestMethod.GET)
    public String getC(@PathVariable("id") Integer id){
        return  project.getContent()+"id:"+id;
    }

    @RequestMapping(value = "/projectE",method = RequestMethod.GET)
    public String getD(@RequestParam(value = "id" ,required = false,defaultValue = "0") Integer projectId){
        return  project.getContent()+"id:"+projectId;
    }



    /*
      * @Author:  pengrj
      * @Description: get请求，用对象接受参数
      * @param   * @param projectTest
      * @Return java.lang.String
      * @Date: Created in 2018/7/22 0022 13:11
      */
    @RequestMapping(value = "/projectF")
    public String getF(@ModelAttribute Project projectTest){
        return projectTest.getContent();
    }





}
