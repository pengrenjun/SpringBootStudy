package com.springbootstudy.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 用户表
 * @Author：pengrj
 * @Date : 2018/7/22 0022 13:35
 * @version:1.0
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @JSONField(format = "yyyy -MM -dd ")
    private Date birthday;

    private String location;

    @JSONField(serialize = false)
    private String comments;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
