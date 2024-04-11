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
@Strategy(StrategyEnum.NONE)
public class NoneStrategyImpl implements PayStrategy {

    @Override
    public String handle(CommandPay commandPay) {
        throw new RuntimeException("");
    }
}
