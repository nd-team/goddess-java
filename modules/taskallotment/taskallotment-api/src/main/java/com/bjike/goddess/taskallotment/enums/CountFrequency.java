package com.bjike.goddess.taskallotment.enums;

/**
 * 汇总频率
 * @Author: [chenjunhao]
 * @Date: [2017-09-28 09:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  CountFrequency {
    /**
     * 天
     */
    DAY(0),
    /**
     * 周
     */
    WEEK(1),
    /**
     * 月
     */
    MONTH(2),
    /**
     * 季度
     */
    SEASON(3),
    /**
     * 年
     */
    YEAR(4);

    private int code;

    CountFrequency(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
