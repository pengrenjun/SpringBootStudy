package com.springbootstudy.springtask.springbootQuartz.singleTask;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * @Description:      Quart单任务执行配置管理控制器
 * @Author：pengrj
 * @Date : 2018/9/18 0018 14:41
 * @version:1.0
 */
@Controller
@RequestMapping("/singletaskconfig")
@Slf4j
public class SingleTaskQuartManageController {
    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;
    //定义cron模板
    private static  String CRON_STR = "0/{0} * * * * ?";


    @ResponseBody
    @PostMapping("/setSeconds")
    public String quartzTest(@RequestParam(value = "prod" ) Integer prod) throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        // 当前Trigger使用的
        String currentCron = trigger.getCronExpression();
        System.err.println("当前trigger使用的-"+currentCron);
        log.info("当前trigger使用的-"+currentCron);
        String cron=MessageFormat.format(CRON_STR, prod.toString());
        //设置执行的时间间隔 需要存入数据库中 系统启动之后时间从数据库中读取
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // 按新的cronExpression表达式重新构建trigger
        trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                .withSchedule(scheduleBuilder).build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
        return  MessageFormat.format("单任务：{0},重新设定的执行间隔时间{1}", jobDetail.getKey(),prod);
    }




}
