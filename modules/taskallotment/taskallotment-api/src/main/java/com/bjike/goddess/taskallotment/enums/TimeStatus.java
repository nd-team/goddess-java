package com.bjike.goddess.taskallotment.enums;

/**
 * 汇总日期类型
 * @Author: [tanghaixiang]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum TimeStatus {
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
     * 季度
     */
    QUART(3);
    private int code;

    TimeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }



}
