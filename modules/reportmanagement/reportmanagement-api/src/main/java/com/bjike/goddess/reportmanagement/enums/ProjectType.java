package com.bjike.goddess.reportmanagement.enums;

/**
 * 项目类型
 */
public enum ProjectType {

    /**
     * 经营活动产生的现金流量
     */
    MANAGEMENT(0),

    /**
     * 投资活动产生的现金流量
     */
    INVESTMENT(1),

    /**
     * 筹资活动产生的现金流量
     */
    FINANCING(2),
    /**
     * 汇率变动对现金的影响
     */
    RATE(3),

    /**
     * 现金及现金等价物净增加额
     */
    CASH(4);


    private int code;

    ProjectType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
