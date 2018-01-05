package com.bjike.goddess.courier.enums;

/**
 * @Author: [yewenbo]
 * @Date: [2017-04-28 10:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Range {
    /**
     * 同城
     */
    LOCAL(0),
    /**
     * 省内
     */
    PROVINCE(1),
    /**
     * 省外
     */
    OUTSIDE(2);

    private int code;

    Range(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
