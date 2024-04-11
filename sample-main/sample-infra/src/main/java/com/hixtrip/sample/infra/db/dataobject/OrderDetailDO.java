package com.hixtrip.sample.infra.db.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 订单详情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order_detail", autoResultMap = true)
@SuperBuilder(toBuilder = true)
public class OrderDetailDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品规格ID
     */
    private String skuId;
    /**
     * 购买时的商品信息，商品快照
     */
    private String productInfo;
    /**
     * 购买数量
     */
    private Integer amount;
    /**
     * 支付金额，单位：分
     */
    private Long payMoney;
    /**
     * 优惠金额，单位：分
     */
    private Long discountMoney;
    /**
     * 售后状态，-2售后拒绝；-1已取消；0正常；1售后中；2售后完成
     */
    private Integer afterSalesStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
