package com.bjike.goddess.user.enums;


/**
 * 登录类型
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LoginType {
    /**
     * app移动端
     */
    MOBILE(1),
    /**
     * pc端
     */
    PC(2),
    /**
     * 未识别
     */
    NONE(0);

    private int code;

    LoginType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
