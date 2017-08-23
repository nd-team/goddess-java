package com.bjike.goddess.receivable.enums;

/**
 * Created by ike on 17-6-23.
 */
public enum TimeStatus {

    /**
     * 月份
     */
    MONTH(0),
    /**
     * 季度
     */
    QUARTER(1),
    /**
     * 年份
     */
    YEAR(2),
    ;
    private int code;

    private TimeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
