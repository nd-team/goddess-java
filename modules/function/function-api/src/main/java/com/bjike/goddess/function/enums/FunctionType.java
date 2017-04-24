package com.bjike.goddess.function.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-22 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FunctionType {
    /**
     * 资金
     */
    CAPITAL(0),
    /**
     * 娱乐
     */
    AMUSEMENT(1),
    /**
     * 财富
     */
    WEALTH(2),
    /**
     * 三方
     */
    THIRDPARTY(3);
    private int code;

    FunctionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
