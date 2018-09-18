package com.springbootstudy.springtask;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Description:   SpringBoot整合Quartz实现定时任务
 * @Author：pengrj
 * @Date : 2018/9/17 0017 21:15
 * @version:1.0
 */
public class TestQuartz extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("quartz task:>>>>>>>>>>>>> "+System.currentTimeMillis());
    }
}
