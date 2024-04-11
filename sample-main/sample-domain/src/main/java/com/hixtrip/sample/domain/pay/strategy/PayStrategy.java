package com.hixtrip.sample.domain.pay.strategy;

import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 * @author Jadan
 * @date 2024/4/10
 */
public interface PayStrategy {

    /**
     * 支付状态回调处理
     * @param commandPay
     * @return
     */
    String handle(CommandPay commandPay);
}
