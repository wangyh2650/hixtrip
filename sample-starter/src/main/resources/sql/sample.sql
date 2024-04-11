#todo 你的建表语句,包含索引
# 按下单日期分区建表, 通过job定期维护分区表
# 排行榜设计预统计表,每天统计前一天.如:
# 按用户分别统计已付款/已退款 订单量
# 按商家分别统计已付款/已退款 订单量
CREATE TABLE `user_order` (
                              `id` varchar(64) NOT NULL COMMENT '订单号',
                              `user_id` varchar(64) DEFAULT NULL COMMENT '购买用户ID',
                              `shop_id` varchar(64) DEFAULT NULL COMMENT '商家ID',
                              `order_type` int(11) DEFAULT '1' COMMENT '订单类型 1普通购买 2秒杀 3 积分兑换等等',
                              `order_money` bigint(20) DEFAULT '0' COMMENT '订单金额 不包含运费，单位：分',
                              `freight_money` bigint(20) DEFAULT '0' COMMENT '运费，单位：分',
                              `pay_money` bigint(20) DEFAULT '0' COMMENT '总支付金额，单位：分',
                              `discount_money` bigint(20) DEFAULT '0' COMMENT '优惠金额，单位：分',
                              `order_status` int(11) DEFAULT '0' COMMENT '订单状态：-1取消 0待支付 1待发货 2待收货 3待评价 4完成',
                              `pay_time` datetime(3) DEFAULT NULL COMMENT '支付时间',
                              `send_time` datetime(3) DEFAULT NULL COMMENT '发货时间',
                              `finish_time` datetime(3) DEFAULT NULL COMMENT '完成时间',
                              `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                              `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
                              `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                              `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
                              KEY `idx_id` (`id`),
                              KEY `idx_user_id` (`user_id`),
                              KEY `idx_shop_id` (`shop_id`),
                              KEY `idx_order_status` (`order_status`),
                              KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户订单表'
PARTITION BY RANGE (YEAR(create_time) * 100 + MONTH(create_time)) (
    PARTITION user_order_202404 VALUES LESS THAN (202404),
    PARTITION user_order_202405 VALUES LESS THAN (202405),
    PARTITION user_order_202406 VALUES LESS THAN (202406)
);

CREATE TABLE `order_detail` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `order_id` varchar(64) DEFAULT NULL COMMENT '订单号',
                                `product_id` varchar(64) DEFAULT NULL COMMENT '商品ID',
                                `sku_id` varchar(64) DEFAULT NULL COMMENT '商品规格ID',
                                `product_info` text COMMENT '购买时的商品信息，商品快照',
                                `amount` int(11) DEFAULT NULL COMMENT '购买数量',
                                `pay_money` bigint(20) DEFAULT NULL COMMENT '支付金额，单位：分',
                                `discount_money` bigint(20) DEFAULT NULL COMMENT '优惠金额，单位：分',
                                `after_sales_status` int(11) DEFAULT NULL COMMENT '售后状态，-2售后拒绝；-1已取消；0正常；1售后中；2售后完成',
                                `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                                `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
                                KEY `idx_id` (`id`),
                                KEY `idx_order_id` (`order_id`),
                                KEY `idx_product_id` (`product_id`),
                                KEY `idx_sku_id` (`sku_id`),
                                KEY `idx_after_sales_status` (`after_sales_status`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='订单商品详细表'
PARTITION BY RANGE (YEAR(create_time) * 100 + MONTH(create_time)) (
    PARTITION order_detail_202404 VALUES LESS THAN (202404),
    PARTITION order_detail_202405 VALUES LESS THAN (202405),
    PARTITION order_detail_202406 VALUES LESS THAN (202406)
);

