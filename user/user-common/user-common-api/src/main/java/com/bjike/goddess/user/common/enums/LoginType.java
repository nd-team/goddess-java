package com.bjike.goddess.user.common.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [登录类型]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum LoginType {
    NONE(0),//未识别
    APP(1),//app
    PC(2),//pc
    ;

    private int code;

    LoginType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
