package com.bjike.goddess.contacts.enums;

/**
 * 客户性别别枚举
 * @Author: [tanghaixiang]
 * @Date: [2017-03-15 16:47]
 * @Description: [客户性别别枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CustomerSex {
    /**
     * 无
     */
    NONE(0),
    /**
     * 男
     */
    MAN(1),
    /**
     * 女
     */
    WOMAN(2),
    ;

    private int code;

    CustomerSex(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
