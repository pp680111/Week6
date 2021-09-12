package com.zst.week6.q7.module.orderitem.entity;

import lombok.Data;

@Data
public class OrderItem {
    private String id;
    private String orderId;
    private String itemId;
    private String price;
    private Integer num;
}
