package com.bjike.goddess.businessevaluate.enums;

/**
 * 能力成长类型
 *
 * @Author: [Jason]
 * @Date: [17-3-29 上午9:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GroupUpType {
    /**
     * 成本
     */
    COST(0),
    /**
     * 事项合作
     */
    FEE(1),
    /**
     * 中介合作
     */
    profit(2);

    private int code;

    GroupUpType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
