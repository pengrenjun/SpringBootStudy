package com.springAnnotationstudy.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @Description:    订单类
 * @Author：pengrj
 * @Date : 2018/9/18 0018 21:07
 * @version:1.0
 */
@Data
@ToString
@AllArgsConstructor
public class Order {

    private Integer orderId;

    private String orderName;

    private Date orderDate;
}
