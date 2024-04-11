package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.infra.db.convertor.OrderDOConvertor;
import com.hixtrip.sample.infra.db.convertor.OrderDetailDOConvertor;
import com.hixtrip.sample.infra.db.dataobject.OrderDetailDO;
import com.hixtrip.sample.infra.db.dataobject.UserOrderDO;
import com.hixtrip.sample.infra.db.mapper.OrderDetailMapper;
import com.hixtrip.sample.infra.db.mapper.UserOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jadan
 * @date 2024/4/10
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(Order order) {
        UserOrderDO userOrderDO = OrderDOConvertor.INSTANCE.fromDomain(order);
        userOrderDO.generateId();
        // todo 创建时间 临时通过手动赋值
        userOrderDO.setCreateTime(LocalDateTime.now());
        order.setId(userOrderDO.getId());
        userOrderMapper.insert(userOrderDO);
        List<OrderDetailDO> orderDetails = OrderDetailDOConvertor.INSTANCE.fromDomain(order.getOrderDetails());
        // todo 用批量保存方法
        for (OrderDetailDO orderDetail : orderDetails) {
            orderDetail.setOrderId(userOrderDO.getId());
            orderDetail.setCreateTime(LocalDateTime.now());
            orderDetailMapper.insert(orderDetail);
        }
        return true;
    }
}
