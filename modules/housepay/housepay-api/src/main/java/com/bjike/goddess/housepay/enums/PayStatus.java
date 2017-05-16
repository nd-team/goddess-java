package com.bjike.goddess.housepay.enums;

/**
 * 是否付款
 *
 * @Author: [xiazhili]
 * @Date: [2017-05-13 10:47]
 * @Description: [是否付款]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PayStatus {
    /**
     * 是
     */
    IS(0),
    /**
     * 否
     */
    NO(1),;

    private int code;

    PayStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
