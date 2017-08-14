package com.bjike.goddess.event.enums;

/**
 * 状态
 * @Author: [chenjunhao]
 * @Date: [2017-08-09 18:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum Status {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 冻结
     */
    FREEZE(1);

    private int value;

    public int getValue() {
        return value;
    }

    Status(int value) {
        this.value = value;
    }
}
