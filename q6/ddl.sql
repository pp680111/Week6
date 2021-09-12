CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `t_item` (
  `id` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `price` varchar(20) NOT NULL COMMENT '价格',
  `status` int(11) NOT NULL COMMENT '状态',
  `stockNum` int(11) NOT NULL COMMENT '库存量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

CREATE TABLE `t_order` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) NOT NULL COMMENT '订单所属用户id',
  `status` int(11) NOT NULL COMMENT '订单状态',
  `price` varchar(50) NOT NULL COMMENT '总价',
  `create_time` bigint COMMENT '创建时间',
  `update_time` bigint COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

CREATE TABLE `t_order_item` (
  `id` varchar(40) NOT NULL,
  `order_id` varchar(40) NOT NULL COMMENT '订单id',
  `item_id` varchar(40) NOT NULL COMMENT '商品id',
  `price` varchar(20) NOT NULL COMMENT '价格',
  `num` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品关联表';