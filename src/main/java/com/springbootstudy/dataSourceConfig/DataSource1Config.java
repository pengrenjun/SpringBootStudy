package com.springbootstudy.dataSourceConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description:    demo包数据源的配置
 * @Author：pengrj
 * @Date : 2018/9/5 0005 21:35
 * @version:1.0
 */

//DataSource01
@Configuration // 注册到springboot容器中
@MapperScan(basePackages = "com.springbootstudy.demo.mapper", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class DataSource1Config {

    /**
     *
     * @methodDesc: 功能描述:(配置springstudy数据库)
     */
    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource1")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @methodDesc: 功能描述:(test1 sql会话工厂)
     */
    @Bean(name = "test1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
         new
                 PathMatchingResourcePatternResolver().getResources("classpath:mapperXml/demo/*.xml"));
        return bean.getObject();
    }

    /**
     *
     * @methodDesc: 功能描述:(test1 事物管理)
     */
    @Bean(name = "test1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
