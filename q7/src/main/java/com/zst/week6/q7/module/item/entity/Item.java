package com.zst.week6.q7.module.item.entity;

import lombok.Data;

@Data
public class Item {
    private String id;
    private String name;
    private String price;
    private Integer status;
    private Integer stockNum;
}
