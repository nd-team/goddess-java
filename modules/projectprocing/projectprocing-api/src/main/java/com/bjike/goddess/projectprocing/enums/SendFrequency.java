package com.bjike.goddess.projectprocing.enums;

public enum  SendFrequency {
    /**
     * 每天
     */
    EVERYDAY(0),
    /**
     * 每周
     */
    EVERYWEEK(1),
    /**
     * 每月
     */
    EVERYMONTH(2),
    /**
     * 每年
     */
    EVERYYEAR(3);

    private int code;

    SendFrequency(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
