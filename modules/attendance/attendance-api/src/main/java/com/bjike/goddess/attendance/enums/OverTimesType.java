package com.bjike.goddess.attendance.enums;

/**
 * 加班汇总时间类型
 * @Author: [tanghaixiang]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum OverTimesType {
    /**
     * 周
     */
    WEEK(0),
    /**
     * 季度
     */
    QUART(1);
    private int code;

    OverTimesType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
