package com.bjike.goddess.marketactivitymanage.type;

/**
 * 周期类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-21 15:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CycleType {

    /**
     * 年
     */
    YEAR(0),
    /**
     * 月
     */
    MONTH(1),
    /**
     * 周
     */
    WEEK(2),
    /**
     * 日
     */
    DAY(3),
    /**
     * 小时
     */
    HOUR(4),
    /**
     * 分钟
     */
    MINUTE(5),
    /**
     * 季度
     */
    QUARTER(7);


    private int value;

    private CycleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
