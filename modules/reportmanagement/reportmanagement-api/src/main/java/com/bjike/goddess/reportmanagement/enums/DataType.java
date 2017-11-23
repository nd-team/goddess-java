package com.bjike.goddess.reportmanagement.enums;

/**
 * 补充资料类型
 */
public enum DataType {

    /**
     * 将净利润调节为经营活动现金流量
     */
    PROFIT(0),

    /**
     * 不涉及现金收支的投资和筹资活动
     */
    NOTINVOLVE(1),

    /**
     * 现金及现金等价物净增加情况
     */
    CASH(2);

    private int code;

    DataType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
