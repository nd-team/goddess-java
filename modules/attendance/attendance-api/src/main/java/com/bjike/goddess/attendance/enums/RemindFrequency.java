package com.bjike.goddess.attendance.enums;

/**
 * 提醒频率
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-19 17:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RemindFrequency {
    /**
     * 年
     */
    YEAR(0),
    /**
     * 季度
     */
    SEASON(1),
    /**
     * 月
     */
    MONTH(2),
    /**
     * 周
     */
    WEEK(3),
    /**
     * 天
     */
    DAY(4),
    /**
     * 时
     */
    HOUR(5),
    /**
     * 分
     */
    MIN(6);
    private int code;

    RemindFrequency(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
