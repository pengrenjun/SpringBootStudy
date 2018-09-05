package com.springbootstudy.dataSourceTest.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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

    public static void main(String[] args) {
        User user=new User();
    }



}
