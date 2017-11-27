package com.bjike.goddess.reportmanagement.enums;

/**
 * 现金流量表分析类型
 */
public enum AnalyseType {

    /**
     * 流动资产
     */
    AFLOW(0),
    /**
     * 长期资产
     */
    BLONG(1),
    /**
     * 固定资产
     */
    CFIX(2),
    /**
     * 无形资产及其他资产
     */
    DINVISIBLE(3),
    /**
     * 递延税款
     */
    ETAX(4);
    private int code;

    AnalyseType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
