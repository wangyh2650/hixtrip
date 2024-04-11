package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.convertor.CommandPayConvertor;
import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.vo.OrderVo;
import com.hixtrip.sample.domain.commodity.CommodityDomainService;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.PayDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDomainService orderDomainService;
    @Autowired
    private CommodityDomainService commodityDomainService;
    @Autowired
    private InventoryDomainService inventoryDomainService;
    @Autowired
    private PayDomainService payDomainService;

    @Override
    public OrderVo createOrder(CommandOderCreateDTO commandOderCreateDTO) {
        Order order = new Order();
        order.setUserId(commandOderCreateDTO.getUserId());
        BigDecimal price = commodityDomainService.getSkuPrice(commandOderCreateDTO.getSkuId());
        order.addProduct(commandOderCreateDTO.getSkuId(), commandOderCreateDTO.getAmount(), price);
        order.price();
        orderDomainService.createOrder(order);
        return OrderConvertor.INSTANCE.domianToVo(order);
    }

    @Override
    public String payCallback(CommandPayDTO commandPayDTO) {
        CommandPay commandPay = CommandPayConvertor.INSTANCE.dtoToDomain(commandPayDTO);
        payDomainService.payRecord(commandPay);
        return "success";
    }
}
