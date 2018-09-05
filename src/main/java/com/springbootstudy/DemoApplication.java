package com.springbootstudy;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
/*@MapperScan("com.springbootstudy.demo.mapper.*")*/
public class DemoApplication {

	/**
	 * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
	 * springboot默认采用jackson进行数据解析
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		//3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);
        /*解决返回的JSON数据乱码的问题*/
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);

		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}


	/*注册两个事务管理器*/
	@Bean(name = "transactionalManager1")
    public PlatformTransactionManager transactionalManager1(DataSource dataSource){
	    return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "transactionalManager2")
    public PlatformTransactionManager transactionalManager2(EntityManagerFactory  factory){
        return  new JpaTransactionManager(factory);
    }

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory  factory){
		return  new JpaTransactionManager(factory);
	}





    /*@Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory  factory){
        return  new JpaTransactionManager(factory);
    }
*/
    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
