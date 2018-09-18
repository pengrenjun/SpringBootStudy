package com.springbootstudy.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:   springTask定时任务
 * @Author：pengrj
 * @Date : 2018/9/14 0014 21:12
 * @version:1.0
 */

/*项目启动时就执行定时任务 @Component*/
@Slf4j
@Async /*每一个任务都是在不同的线程中 线程分配执行的配置类参照 AsyncConfig*/
public class TestSpringTask {
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }

}
