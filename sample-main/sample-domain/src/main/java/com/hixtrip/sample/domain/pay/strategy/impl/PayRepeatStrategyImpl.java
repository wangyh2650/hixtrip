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
@Strategy(StrategyEnum.REPEAT)
public class PayRepeatStrategyImpl implements PayStrategy {

    @Override
    public String handle(CommandPay commandPay) {
        // todo 支付成功且 重复支付的 发起退款
        return "repeat";
    }
}
