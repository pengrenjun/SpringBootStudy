package com.springAnnotationstudy.Config;

import com.springAnnotationstudy.Bean.Order;
import org.springframework.context.annotation.*;
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

    @Scope//在IOC容器创建时 默认创建的为单实例对象
    @Bean(value = "orderA") //默认value为方法名
    public Order orderA(){
        System.out.println("===============IOC容器创建  装载单实例orderA================");
        Order order=new Order(123,"小米手机",new Date(System.currentTimeMillis()));
        return order;
    }







    /**  String SCOPE_SINGLETON = "singleton"; 默认创建的单实例对象
         String SCOPE_PROTOTYPE = "prototype";*/
    @Scope(value = "prototype")//多实例的对象在IOC加载之后 加载Bean的时候创建的
    @Bean(value = "orderB")
    public  Order orderB(){
        System.out.println("====================创建多实例对象 orderB==================");
        Order order=new Order(789,"华为手机",new Date(System.currentTimeMillis()));
        return  order;

    }

    /** @lazy 懒加载(延迟加载)  对于单实例的对象可以在IOC容器创建之后 再实例化加载Bean */
    @Scope
    @Lazy
    @Bean(value = "orderC")
    public  Order orderC(){
        System.out.println("====================懒加载单实例Bean orderC==================");
        Order order=new Order(40967,"笔记本电脑",new Date(System.currentTimeMillis()));
        return  order;
    }
}
