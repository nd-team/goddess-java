package com.bjike.goddess.task.enums;

/**
 * 汇总间隔类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-25 16:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DateType {

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
     * 季
     */
    QUARTER(3),
    /**
     * 年
     */
    YEAR(4);

    DateType(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
