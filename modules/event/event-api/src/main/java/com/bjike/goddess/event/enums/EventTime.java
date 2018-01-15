package com.bjike.goddess.event.enums;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-12-29 14:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum EventTime {

    /**
     * 提前15分钟
     */
    MINUTES_15(0),
    /**
     * 提前30分钟
     */
    MINUTES_30(1),
    /**
     * 提前60分钟
     */
    MINUTES_60(2);

    private int value;

    public int getValue() {
        return value;
    }

    EventTime(int value) {
        this.value = value;
    }

}
