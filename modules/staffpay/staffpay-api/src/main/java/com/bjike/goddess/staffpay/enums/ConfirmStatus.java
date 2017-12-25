package com.bjike.goddess.staffpay.enums;

/**
 * 是否确认
 *
 * @Author: [xiazhili]
 * @Date: [2017-05-13 10:47]
 * @Description: [是否确认]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ConfirmStatus {
    /**
     * 是
     */
    YES(0),
    /**
     * 否
     */
    NO(1),;

    private int code;

    ConfirmStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
