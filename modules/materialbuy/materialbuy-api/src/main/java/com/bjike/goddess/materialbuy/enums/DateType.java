package com.bjike.goddess.materialbuy.enums;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-03 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DateType {

    YEAR(1),
    /**
     * 月份
     */
    MONTH(2),
    /**
     * 周
     */
    WEEK(3),
    /**
     * 日
     */
    DAY(4),
    /**
     * 季度
     */
    QUARTER(5);

    private int value;

    public int getValue() {
        return value;
    }

    DateType(int value) {
        this.value = value;
    }

}
