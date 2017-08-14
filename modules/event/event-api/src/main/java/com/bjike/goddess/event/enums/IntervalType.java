package com.bjike.goddess.event.enums;

/**
 * 间隔类型
 * @Author: [chenjunhao]
 * @Date: [2017-08-09 18:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum IntervalType {
    /**
     * 天
     */
    DAY(0),
    /**
     * 小时
     */
    HOUR(1),
    /**
     * 分钟
     */
    MINUTE(2)
    ;

    private int value;

    public int getValue() {
        return value;
    }

    IntervalType(int value) {
        this.value = value;
    }
}
