package com.hixtrip.sample.domain.pay.strategy.impl;

import com.hixtrip.sample.domain.pay.annotation.Strategy;
import com.hixtrip.sample.domain.pay.enums.StrategyEnum;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.domain.pay.strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Jadan
 * @date 2024/4/10
 */
@Component
@Strategy(StrategyEnum.SUCCESS)
public class PaySuccessStrategyImpl implements PayStrategy {

    @Override
    public String handle(CommandPay commandPay) {
        // todo 成功修改订单状态，支付信息等

        // todo 缓存订单支付状态
        return "success";
    }
}
