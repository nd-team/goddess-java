package com.bjike.goddess.user.common.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [性别]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum SexType {
    NONE(0),//无
    MAN(1),//男
    WOMAN(2),//女
    ;

    private int code;

    SexType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
