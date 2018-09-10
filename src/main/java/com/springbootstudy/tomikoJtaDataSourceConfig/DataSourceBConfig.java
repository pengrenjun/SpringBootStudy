package com.springbootstudy.tomikoJtaDataSourceConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.springbootstudy.tomikoJtaDataSourceConfig.DbConfigEntity.DbConfigB;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: Springboot+atomikos+jta实现分布式事务统一管理配置的数据源
 * @Author：pengrj
 * @Date : 2018/9/10 0010 20:40
 * @version:1.0
 */
@Configuration
@MapperScan(basePackages="com.springbootstudy.tomikosJta2",sqlSessionFactoryRef="test2SqlSessionFactory")
public class DataSourceBConfig {

    //配置数据源
    @Bean(name="test2Datasource")
    public DataSource testDatasource(DbConfigB config2) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config2.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config2.getPassword());
        mysqlXADataSource.setUser(config2.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test2Datasource");

        atomikosDataSourceBean.setMinPoolSize(config2.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config2.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config2.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config2.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config2.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config2.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config2.getMaxIdleTime());
        return atomikosDataSourceBean;
    }
    @Bean(name="test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //设置Mapper的Xml文件的位置
      bean.setMapperLocations(new PathMatchingResourcePatternResolver().
              getResources("classpath:mapperXml/tomikosJta/*.xml"));
        return bean.getObject();
    }

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
