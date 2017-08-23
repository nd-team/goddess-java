package com.bjike.goddess.supplier.enums;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-14 11:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimeType {
    /**
     * 分钟
     */
    MINUTE(0),
    /**
     * 小时
     */
    HOUR(1),
    /**
     * 天
     */
    DAY(2),
    /**
     * 周
     */
    WEEK(3),
    /**
     * 月
     */
    MONTH(4),
    /**
     * 季度
     */
    QUARTER(5),
    /**
     * 年
     */
    YEAR(6);


    private int value;

    TimeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
