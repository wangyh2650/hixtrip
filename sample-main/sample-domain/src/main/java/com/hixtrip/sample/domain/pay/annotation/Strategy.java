package com.hixtrip.sample.domain.pay.annotation;


import com.hixtrip.sample.domain.pay.enums.StrategyEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jadan
 * @date 2024/4/10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Strategy {

    StrategyEnum value();
}
