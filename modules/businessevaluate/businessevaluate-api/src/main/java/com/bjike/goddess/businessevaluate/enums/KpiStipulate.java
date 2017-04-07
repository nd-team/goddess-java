package com.bjike.goddess.businessevaluate.enums;

/**
 * 指标规定
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午2:48]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum KpiStipulate {
    /**
     * 仅允出现一次
     */
    ONLYONE(0),
    /**
     * 禁止
     */
    FORBID(1);

    private int code;

    KpiStipulate(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
