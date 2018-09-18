package com.springAnnotationstudy.Config;

import com.springAnnotationstudy.Bean.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Date;

/**
 * @Description:   订单类的配置类，采用注解方式进行实例化
 * @Author：pengrj
 * @Date : 2018/9/18 0018 21:12
 * @version:1.0
 */
@Configuration //告诉Spring容器这是一个配置类 需要进行加载

/**value 定义了扫包的范围  Filter[] includeFilters() 定义包含的扫描条件 */
@ComponentScan(value="com.springAnnotationstudy",
  includeFilters ={@ComponentScan.Filter
    (type = FilterType.ANNOTATION,classes = {RestController.class, Service.class, Repository.class})}
)
public class OrderConfig {

    @Bean(value = "orderA") //默认value为方法名
    public Order orderA(){
        Order order=new Order(123,"小米手机",new Date(System.currentTimeMillis()));
        return order;
    }
}
