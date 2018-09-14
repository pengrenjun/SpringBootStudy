package com.springbootstudy.demo;

import com.springbootstudy.springBootMQ.ActiveMQ.JmsSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

    @Autowired
    private JmsSender sender;
	@Test
    /**
     * 测试ActiveMQ消息发送
     */
	public  void testJmsSendByQueue(){

        for (int i = 1; i < 6; i++) {
            this.sender.sendByQueue("hello activemq queue " + i);
        }
    }

}
