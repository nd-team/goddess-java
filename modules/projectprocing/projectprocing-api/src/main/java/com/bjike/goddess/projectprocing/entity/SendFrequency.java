package com.bjike.goddess.projectprocing.entity;

public enum  SendFrequency {
//    (每天/每周/每月/每年
    /**
     *
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
