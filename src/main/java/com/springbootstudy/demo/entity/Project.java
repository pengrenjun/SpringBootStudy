package com.springbootstudy.demo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 项目描述
 * @Author：pengrj
 * @Date : 2018/7/22 0022 11:10
 * @version:1.0
 */
@Component
@ConfigurationProperties(prefix = "project")
public class Project {

  private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
