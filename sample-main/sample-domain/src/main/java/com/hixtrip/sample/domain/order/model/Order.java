package com.hixtrip.sample.domain.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class Order {

    /**
     * 订单号
     */
    private String id;


    /**
     * 购买人
     */
    private String userId;


    /**
     * SkuId
     */
    private String skuId;

    /**
     * 购买数量
     */
    private Integer amount;

    /**
     * 购买金额
     */
    private BigDecimal money;

    /**
     * 购买时间
     */
    private LocalDateTime payTime;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Long delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private List<OrderDetail> orderDetails = new ArrayList<>();

    /**
     * 添加购买商品
     * @param skuId
     * @param amount
     * @param price
     */
    public void addProduct(String skuId, Integer amount, BigDecimal price) {
        // todo 库存校验 并减库存  调用
        //调用 InventoryDomainService.changeInventory 减调占用库存，库存不足返回false
        this.skuId = skuId;
        BigDecimal money = price.multiply(BigDecimal.valueOf(amount));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setSkuId(skuId);
        orderDetail.setAmount(amount);
        orderDetail.setPayMoney(money.longValue());
        orderDetails.add(orderDetail);
        this.amount = orderDetails.stream().mapToInt(OrderDetail::getAmount).sum();
    }

    /**
     * 订单价格计算
     */
    public void price() {
        this.money = BigDecimal.valueOf(orderDetails.stream().mapToLong(OrderDetail::getPayMoney).sum());
        // todo 优惠 邮费计算
    }
}
