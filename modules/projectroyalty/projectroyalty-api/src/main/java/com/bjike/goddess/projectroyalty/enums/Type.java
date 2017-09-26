package com.bjike.goddess.projectroyalty.enums;

/**
 * @Author: [zhuangkaiqin]
 * @Date: [2017-06-08 09:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Type {
    /**
     * 目标
     */
    AIM(0),
    /**
     * 计划
     */
    PLAN(1),
    /**
     * 差异
     */
    DIFFERENCE(2),
    /**
     * 实际
     */
    ACTUAL(3);
    private int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
