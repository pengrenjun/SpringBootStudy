package com.springbootstudy.springtask;

import java.util.concurrent.*;

/**
 * @Description: ScheduledExecutorService定时任务的使用
 * @Author：pengrj
 * @Date : 2018/9/14 0014 20:43
 * @version:1.0
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
        service.scheduleAtFixedRate(
                ()->System.out.println
                        ("task ScheduledExecutorService "+System.currentTimeMillis()),
                0, 3, TimeUnit.SECONDS);
    }


}
