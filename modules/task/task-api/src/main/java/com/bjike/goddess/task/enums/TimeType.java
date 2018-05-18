package com.bjike.goddess.task.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 16:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimeType {
    /**
     * 分钟
     */
    MINTUE(0),
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
    MONTH(4);

    private int code;

    TimeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
