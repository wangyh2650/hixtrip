package com.hixtrip.sample.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hixtrip.sample.infra.db.dataobject.UserOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrderDO> {

}
