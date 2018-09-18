package com.springbootstudy.springtask.springbootQuartz.multiTask;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:    多任务job的Quartz配置,开启任务的自动启动
 * @Author：pengrj
 * @Date : 2018/9/18 0018 16:14
 * @version:1.0
 */
@Configuration
public class MultiTaskQuartzConfig {

    //具体的定时任务

    @Bean(name="jobA")
    public JobDetail scheduleJobA(Scheduler scheduler) throws SchedulerException {
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJobA.class) .withIdentity("jobA", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/49 * * * * ?");
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("triggerA", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        return  jobDetail;
    }

    @Bean(name="jobB")
    public JobDetail scheduleJobB(Scheduler scheduler) throws SchedulerException{
        //配置定时任务对应的Job，这里执行的是ScheduledJob2类中定时的方法
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJobB.class) .withIdentity("jobB", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/59 * * * * ?");
        // 每6s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("triggerB", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        return  jobDetail;
    }

}
