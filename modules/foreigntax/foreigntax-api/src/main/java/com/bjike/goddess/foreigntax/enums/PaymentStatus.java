package com.bjike.goddess.foreigntax.enums;

/**
 * 缴税状态枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-19]
 * @Description: [缴税状态枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum PaymentStatus {
    /**
     * 待缴税
     */
    DIDPAY(0),
    /**
     * 已缴税
     */
    DUTYPAID(1),;

    private int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
