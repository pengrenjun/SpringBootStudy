package com.springbootstudy.springtask;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:   定时任务：Timer的使用
 * @Author：pengrj
 * @Date : 2018/9/14 0014 20:35
 * @version:1.0
 */
public class TestTimer {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task  run:"+ System.currentTimeMillis());
            }
        };
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);
    }


}
