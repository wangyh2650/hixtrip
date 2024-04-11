package com.hixtrip.sample.domain.pay.enums;

import lombok.Getter;

/**
 * @author Jadan
 * @date 2024/4/10
 */
@Getter
public enum StrategyEnum {

    FAIL("0"),

    SUCCESS("1"),

    REPEAT("2"),

    NONE("");

    StrategyEnum(String status) {
        this.status = status;
    }

    private String status;

    public static StrategyEnum getByStatus(String status) {
        StrategyEnum[] enums = values();
        for (StrategyEnum anEnum : enums) {
            if (anEnum.getStatus().equals(status)) {
                return anEnum;
            }
        }
        return NONE;
    }
}
