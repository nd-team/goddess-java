package com.bjike.goddess.marketdevelopment.enums;

/**
 * 月份类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 月份类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public enum DateType {
    /**
     * 无
     */
    NONE(0),
    /**
     * 计划完成
     */
    PLAN(1),
    /**
     * 实际完成
     */
    ATCUAL(2),
    /**
     * 差异
     */
    DIFFERENCE(3);

    private int code;

    private DateType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
