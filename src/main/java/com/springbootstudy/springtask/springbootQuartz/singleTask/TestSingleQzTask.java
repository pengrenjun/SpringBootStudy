package com.springbootstudy.springtask.springbootQuartz.singleTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @Description:   建立的Quartz的单任务
 * @Author：pengrj
 * @Date : 2018/9/18 0018 11:31
 * @version:1.0
 */
@Component
@EnableScheduling//这个注解一定要加
public class TestSingleQzTask {

    public void excuteSingleQzTask(){
        System.out.println("Quartz的单任务定时执行中>>>>>>>"+System.currentTimeMillis());
    }
}
