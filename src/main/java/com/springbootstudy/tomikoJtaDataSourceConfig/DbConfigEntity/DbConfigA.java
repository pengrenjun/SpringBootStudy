package com.springbootstudy.tomikoJtaDataSourceConfig.DbConfigEntity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置信息的实体映射
 * @Author：pengrj
 * @Date : 2018/9/10 0010 20:31
 * @version:1.0
 */
@ConfigurationProperties(prefix = "mysql.datasource.test1")
@Data

/*扫描装载配置的Bean @Configuration -> @EnableConfigurationProperties(value = { DbConfigA.class, DbConfigB.class })*/
public class DbConfigA {

    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
}
