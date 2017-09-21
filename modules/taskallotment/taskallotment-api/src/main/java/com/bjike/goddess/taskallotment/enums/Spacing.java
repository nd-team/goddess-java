package com.bjike.goddess.taskallotment.enums;

/**
 * 时间间隔
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-15 11:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Spacing {
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

    Spacing(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
