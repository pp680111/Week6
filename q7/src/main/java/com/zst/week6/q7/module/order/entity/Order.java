package com.zst.week6.q7.module.order.entity;

import lombok.Data;

@Data
public class Order {
    private String id;
    private String userId;
    private int status;
    private String price;
    private Long createTime;
    private Long updateTime;
}
