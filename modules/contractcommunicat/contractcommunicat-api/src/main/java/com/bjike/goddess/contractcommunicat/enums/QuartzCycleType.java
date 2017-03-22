package com.bjike.goddess.contractcommunicat.enums;

/**
 * 汇总发送定时器时间
 *
 * @Author: [Jason]
 * @Date: [17-3-20 上午10:01]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum QuartzCycleType {
    /**
     * 年
     */
    YEAR(0),
    /**
     * 季度
     */
    QUARTER(1),

    /**
     * 月
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
     * 时
     */
    HOUR(5),
    /**
     * 分
     */
    MINUTE(6);


    private int value;

    private QuartzCycleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
