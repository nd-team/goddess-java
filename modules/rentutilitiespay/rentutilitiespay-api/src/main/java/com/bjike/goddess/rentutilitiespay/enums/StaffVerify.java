package com.bjike.goddess.rentutilitiespay.enums;

/**
 * @Author: [xiazhili]
 * @Date: [17-4-17]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum StaffVerify {
    /**
     * 确认
     */
    CONFIRM(0),
    /**
     * 有误
     */
    ERROR(1),
    ;

    private int code;

    StaffVerify(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
