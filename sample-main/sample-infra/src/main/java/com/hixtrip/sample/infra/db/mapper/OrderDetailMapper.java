package com.hixtrip.sample.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hixtrip.sample.infra.db.dataobject.OrderDetailDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品明细
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {

}
