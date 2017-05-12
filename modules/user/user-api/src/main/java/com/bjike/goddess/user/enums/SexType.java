package com.bjike.goddess.user.enums;


/**
 * 性别
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SexType {
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
    WOMAN(2);

    private int code;

    SexType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
