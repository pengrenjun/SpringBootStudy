package com.springbootstudy.springBootMQ.ActiveMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */

@Component
public class JmsReceiver {
    /*@JmsListener注解能够定义对消息主题的监听，并通过destination参数决定监听来源。*/
    @JmsListener(destination = JmsConfirguration.QUEUE_NAME)
    public void receiveByQueue(String message) {
        System.out.println("接收队列消息:" + message);
    }
    @JmsListener(destination = JmsConfirguration.TOPIC_NAME)
    public void receiveByTopic(String message) {
        System.out.println("接收主题消息:" + message);
    }

}
