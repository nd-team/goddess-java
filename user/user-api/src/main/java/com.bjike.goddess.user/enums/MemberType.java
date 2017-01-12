package com.bjike.goddess.user.enums;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [会员类型]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MemberType {
    REGISTERED(0),//注册会员
    BRONZE(1),//铜牌会员
    GOLD(2),//金牌会员
    ;

    private int code;

    MemberType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
