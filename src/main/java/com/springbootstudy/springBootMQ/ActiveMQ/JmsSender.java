package com.springbootstudy.springBootMQ.ActiveMQ;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * 消息生产者
 */
@Component
@Configuration
@Slf4j
public class JmsSender {
    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendByQueue(String message) {
        log.info("JmsSender:sendByQueue 发送的队列消息{}",message);
        this.jmsTemplate.convertAndSend(queue, message);
    }

    public void sendByTopic(String message) {
        log.info("JmsSender:sendByTopic 发送的主题消息{}",message);
        this.jmsTemplate.convertAndSend(topic, message);
    }
}
