package com.bjike.goddess.balancecard.enums;

/**
 * 分解得来状态
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 16:47]
 * @Description: [分解得来状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SeperateComeStatus {

    /**
     * 年度分解
     */
    YEAR(0),
    /**
     * 部门年度分解
     */
    DEPARTYEAR(1),
    /**
     * 部门月度分解
     */
    DEPARTMONTH(2),
    /**
     * 手填
     */
    FILL(2)
    ;

    private int code;

    SeperateComeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
