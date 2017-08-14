package com.bjike.goddess.event.enums;

/**
 * 事件处理状态
 * @Author: [chenjunhao]
 * @Date: [2017-08-09 16:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum EventStatus {
    /**
     * 已处理
     */
    HAVEDEAL(0),
    /**
     * 未查看未处理
     */
    NOSEENODEAL(1),
    /**
     * 查看未处理（待处理）
     */
    SEENODEAL(2),
    /**
     * 逾期未处理
     */
    NODEAL(3)
    ;

    private int value;

    public int getValue() {
        return value;
    }

    EventStatus(int value) {
        this.value = value;
    }
}
