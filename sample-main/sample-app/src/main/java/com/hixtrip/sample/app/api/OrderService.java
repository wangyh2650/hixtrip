package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.vo.OrderVo;

/**
 * 订单的service层
 */
public interface OrderService {

    /**
     * 创建订单
     * @param commandOderCreateDTO
     * @return
     */
    OrderVo createOrder(CommandOderCreateDTO commandOderCreateDTO);

    /**
     * 支付回调
     * @param commandPayDTO
     * @return todo 根据实际情况返回
     */
    String payCallback(CommandPayDTO commandPayDTO);
}
