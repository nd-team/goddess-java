package com.bjike.goddess.user.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [登录类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LoginType {
    MOBILE(1),//app
    PC(2),//pc
    NONE(0),//未识别
    ;

    private int code;

    LoginType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
