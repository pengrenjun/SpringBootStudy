package com.springbootstudy.tomikoJtaDataSourceConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.springbootstudy.tomikoJtaDataSourceConfig.DbConfigEntity.DbConfigA;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages="com.springbootstudy.tomikosJta1",sqlSessionFactoryRef="test1SqlSessionFactory")
public class DataSourceAConfig {
    //配置数据源
    @Primary
    @Bean(name="test1Datasource")
    public DataSource testDatasource(DbConfigA config1) throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl(config1.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config1.getPassword());
        mysqlXADataSource.setUser(config1.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test1Datasource");

        atomikosDataSourceBean.setMinPoolSize(config1.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(config1.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(config1.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(config1.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(config1.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(config1.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(config1.getMaxIdleTime());
        return atomikosDataSourceBean;
    }
    @Primary
    @Bean(name="test1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1Datasource")DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
         //设置Mapper的Xml文件的位置
          bean.setMapperLocations(new PathMatchingResourcePatternResolver().
          getResources("classpath:mapperXml/tomikosJta/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name="test1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory")
                                                             SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
