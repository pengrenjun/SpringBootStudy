package com.springAnnotationstudy.Test;

import com.springAnnotationstudy.Bean.Order;
import com.springAnnotationstudy.Config.OrderConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:     配置类信息获取测试
 * @Author：pengrj
 * @Date : 2018/9/18 0018 21:19
 * @version:1.0
 */
public class ConfigurationTest {

    /**测试组件扫描ComponentScan注解*/
    @Test
    public void  testComponntScanAnnotation(){

        //从spring的IOC容器中获取注解配置类
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(OrderConfig.class);

        String[] strings=applicationContext.getBeanDefinitionNames();

        for (String str:strings){
            System.out.println("OrderConfig配置类里面的组件名称：>>>>>>"+str);
        }

    }

    /**测试@Scope组件*/
    @Test
    public void testScopeAnnotation(){

        //从spring的IOC容器中获取注解配置类
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(OrderConfig.class);

        //创建一个多实例的Bean Order
        Order orderA=(Order)applicationContext.getBean("orderB");
        Order orderB=(Order)applicationContext.getBean("orderB");

        System.out.println(orderA==orderB);

        //获取单实例对象
        Order order1=(Order)applicationContext.getBean("orderA");
        order1.setOrderName("小米测试机");
        Order order2=(Order)applicationContext.getBean("orderA");
        System.out.println(order2.getOrderName());
        System.out.println(order1==order2);


    }

    /**测试@lazy 注解的作用*/
    @Test
    public void testLazyAnnotation(){

        //从spring的IOC容器中获取注解配置类
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(OrderConfig.class);

        Order orderC1=(Order)applicationContext.getBean("orderC");
        Order orderC2=(Order)applicationContext.getBean("orderC");
    }

    public static void main(String[] args) {

        //从容器中获取配置类
        ApplicationContext applicationContext= new AnnotationConfigApplicationContext(OrderConfig.class);

        //根据Bean的类型 获取配置类的中的所有Bean的名称id
        String[] strings=applicationContext.getBeanNamesForType(Order.class);

        for (String str:strings){
            System.out.println("bean id:>>>>>>"+str);
            System.out.println(applicationContext.getBean(str).toString());
        }

    }




}
