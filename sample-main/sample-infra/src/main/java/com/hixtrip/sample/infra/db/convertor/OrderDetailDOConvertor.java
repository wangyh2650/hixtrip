package com.hixtrip.sample.infra.db.convertor;

import com.hixtrip.sample.domain.order.model.OrderDetail;
import com.hixtrip.sample.infra.db.dataobject.OrderDetailDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * DO对像 -> 领域对象转换器
 * todo 自由实现
 */
@Mapper
public interface OrderDetailDOConvertor {
    OrderDetailDOConvertor INSTANCE = Mappers.getMapper(OrderDetailDOConvertor.class);

    @Mappings({
            @Mapping(source = "payMoney", target = "money"),
            @Mapping(source = "orderStatus", target = "payStatus")
    })
    List<OrderDetail> toDomain(List<OrderDetailDO> orderDetails);

    List<OrderDetailDO> fromDomain(List<OrderDetail> orderDetails);
}
