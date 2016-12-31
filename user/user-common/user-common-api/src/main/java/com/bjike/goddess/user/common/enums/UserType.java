package com.bjike.goddess.user.common.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户类型]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum UserType {
    CUSTOMER(0),//用户
    ADMIN(1),//管理员
    ;

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
