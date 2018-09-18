package com.springbootstudy.springtask.springbootQuartz.multiTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: quartz多任务需要用到Job类，将定时的逻辑写在Job类中
 * @Author：pengrj
 * @Date : 2018/9/18 0018 16:03
 * @version:1.0
 */
public class ScheduledJobA implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz多任务 第一个任务正在执行中>>>"+System.currentTimeMillis());
    }
}
