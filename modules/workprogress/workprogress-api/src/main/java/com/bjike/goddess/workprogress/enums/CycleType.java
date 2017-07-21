package com.bjike.goddess.regionalprogresscollect.enums;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-17 15:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CycleType {
    /**
     * 第一周
     */
    ONE(1),
    /**
     * 第二周
     */
    TWO(2),
    /**
     * 第三周
     */
    THREE(3),
    /**
     * 第四周
     */
    FOUR(4);

    private int value;

    CycleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
