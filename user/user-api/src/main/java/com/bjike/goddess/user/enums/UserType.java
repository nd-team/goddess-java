package com.bjike.goddess.user.enums;


/**
 * 用户类型
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum UserType {
    /**
     * 用户
     */
    CUSTOMER(0),
    /**
     * 管理员
     */
    ADMIN(1),;

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
