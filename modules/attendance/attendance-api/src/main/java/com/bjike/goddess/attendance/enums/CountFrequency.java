package com.bjike.goddess.attendance.enums;

/**
 * 汇总频率
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-19 17:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CountFrequency {
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
    DAY(4);
    private int code;

    CountFrequency(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
