package com.springbootstudy.springtask.springbootQuartz.multiTask;

import org.quartz.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description:  多个任务job启动器
 * @Author：pengrj
 * @Date : 2018/9/18 0018 16:26
 * @version:1.0
 */
@Controller
@RequestMapping("/startmultitask")
public class MultiTaskStartController {

    @Resource(name="multitaskScheduler")
    private Scheduler scheduler2;

    @ResponseBody
    @RequestMapping("/multitaskA")
    public String multitaskA() throws SchedulerException {
        scheduleJobA(scheduler2);

        return "multitaskA开始执行";

    }

    @ResponseBody
    @RequestMapping("/multitaskB")
    public String multitaskB() throws SchedulerException {
        scheduleJobB(scheduler2);

        return "multitaskB开始执行";

    }


    //具体的定时任务

    private void scheduleJobA(Scheduler scheduler) throws SchedulerException{
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJobA.class) .withIdentity("jobA", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        // 每3s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("triggerA", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    private void scheduleJobB(Scheduler scheduler) throws SchedulerException{
        //配置定时任务对应的Job，这里执行的是ScheduledJob2类中定时的方法
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJobB.class) .withIdentity("jobB", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        // 每6s执行一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("triggerB", "group1") .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
