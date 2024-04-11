package com.hixtrip.sample.domain.pay;

import com.hixtrip.sample.domain.pay.annotation.Strategy;
import com.hixtrip.sample.domain.pay.enums.StrategyEnum;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.domain.pay.strategy.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 支付领域服务
 * todo 不需要具体实现, 直接调用即可
 */
@Component
public class PayDomainService {

    @Autowired
    private List<PayStrategy> payStrategies;

    /**
     * 记录支付回调结果
     * 【高级要求】至少有一个功能点能体现充血模型的使用。
     */
    public void payRecord(CommandPay commandPay) {
        String status = commandPay.getPayStatus();
        // todo 付款成功 从redis缓存中获取 支付状态。已存在则重复支付 status=2
        StrategyEnum strategyEnum = StrategyEnum.getByStatus(status);
        PayStrategy payStrategy = payStrategies.stream()
                .filter(s -> s.getClass().getAnnotation(Strategy.class).value().equals(strategyEnum))
                .findFirst().get();
        payStrategy.handle(commandPay);
        //无需实现，直接调用即可
    }
}
