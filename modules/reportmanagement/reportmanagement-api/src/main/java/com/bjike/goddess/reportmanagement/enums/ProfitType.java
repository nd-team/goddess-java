package com.bjike.goddess.reportmanagement.enums;

/**
 * 利润类型
 * @Author: [chenjunhao]
 * @Date: [2017-06-29 16:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProfitType {
    /**
     * 营业收入
     */
    AINCOME(0),
    /**
     * 营业利润
     */
    BPROFIT(1),
    /**
     * 利润总额
     */
    CSUM(2),
    /**
     * 净利润
     */
    DNETPROFIT(3),
    /**
     * 可供分配的利润
     */
    DISTRIBUTABLEPROFITS(4),
    /**
     * 可供投资者分配的利润
     */
    PROFITSACAILABLE(5),
    /**
     * 以前年度损益调整
     */
    PREVIOUSANNUAL(6),
    /**
     * 未分配利润
     */
    UNDISTRIBUTED(7);
    private int code;

    ProfitType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
