package com.bjike.goddess.taskallotment.enums;

/**
 * 汇总时间类型
 * @Author: [chenjunhao]
 * @Date: [2017-11-04 11:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DataType {
    /**
     * 今日任务汇总
     */
    TODAY(0),
    /**
     * 昨日任务汇总
     */
    YESTERDAY(1),
    /**
     * 本周任务汇总
     */
    THISWEEK(2),
    /**
     * 本月任务汇总
     */
    THISMONTH(3),
    /**
     * 本季度年任务汇总
     */
    THISQUARTER(4),
    /**
     * 本年任务汇总
     */
    THISYEAR(5);
    private int code;

    DataType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
