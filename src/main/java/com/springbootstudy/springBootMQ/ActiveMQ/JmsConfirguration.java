package com.springbootstudy.springBootMQ.ActiveMQ;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * ActiveMQ 配置类 定义队列||主题
 */
@Configuration
@EnableJms
public class JmsConfirguration {
    public static final String QUEUE_NAME = "sample.queue";

    public static final String TOPIC_NAME = "sample.topic";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(TOPIC_NAME);
    }
}
