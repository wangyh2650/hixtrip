package com.hixtrip.sample.infra.db.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 订单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "user_order", autoResultMap = true)
@SuperBuilder(toBuilder = true)
public class UserOrderDO {

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 购买用户
     */
    private String userId;
    /**
     * 商店ID
     */
    private String shopId;
    /**
     * 订单类型 1普通购买 2秒杀 3 积分兑换等等
     */
    private Integer orderType;
    /**
     * 订单金额 不包含运费，单位：分
     */
    private Long orderMoney;
    /**
     * 运费，单位：分
     */
    private Long freightMoney;
    /**
     * 总支付金额，单位：分
     */
    private Long payMoney;
    /**
     * 优惠金额，单位：分
     */
    private Long discountMoney;
    /**
     * 订单状态：-1取消 0待支付 1待发货 2待收货 3待评价 4完成
     */
    private Integer orderStatus;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 发货时间
     */
    private LocalDateTime sendTime;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 修改人
     */
    private String updateBy;

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

    /**
     * 生成ID
     */
    public void generateId() {
        //todo 用户ID + 当前时间戳 + 5位随机数作为ID
        int r = new Random().nextInt(10000);
        this.id = "O_"+ this.userId + System.currentTimeMillis() + String.format("%05d", r);
    }
}
