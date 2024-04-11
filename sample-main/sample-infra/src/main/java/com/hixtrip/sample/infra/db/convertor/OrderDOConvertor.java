package com.hixtrip.sample.infra.db.convertor;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.infra.db.dataobject.UserOrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * DO对像 -> 领域对象转换器
 * todo 自由实现
 */
@Mapper
public interface OrderDOConvertor {
    OrderDOConvertor INSTANCE = Mappers.getMapper(OrderDOConvertor.class);

    @Mappings({
            @Mapping(source = "payMoney", target = "money"),
            @Mapping(source = "orderStatus", target = "payStatus")
    })
    Order toDomain(UserOrderDO userOrderDO);

    @Mappings({
            @Mapping(source = "money", target = "payMoney"),
            @Mapping(source = "payStatus", target = "orderStatus")
    })
    UserOrderDO fromDomain(Order order);
}
