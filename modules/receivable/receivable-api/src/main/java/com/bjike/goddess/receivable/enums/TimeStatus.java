package com.bjike.goddess.receivable.enums;

/**
 * Created by ike on 17-6-23.
 */
public enum TimeStatus {

    /**
     * 月份
     */
    MONTH(1),
    /**
     * 季度
     */
    QUARTER(2),
    /**
     * 年份
     */
    YEAR(3),
    ;
    private int code;

    private TimeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
