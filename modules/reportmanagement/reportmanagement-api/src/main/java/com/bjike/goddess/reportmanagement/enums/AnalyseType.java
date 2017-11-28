package com.bjike.goddess.reportmanagement.enums;

/**
 * 现金流量表分析类型
 */
public enum AnalyseType {

    /**
     * 经营活动产生的现金流量分析
     */
    MANAGEMENT(0),
    /**
     * 投资活动产生的现金流量分析
     */
    INVESTMENT(1),
    /**
     * 筹资活动产生的现金流量分析
     */
    FINANCING(2),
    /**
     * 现金流量构成分析
     */
    CASH(3);

    private int code;

    AnalyseType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
