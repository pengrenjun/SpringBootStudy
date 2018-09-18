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
