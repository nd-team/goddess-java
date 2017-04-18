package com.bjike.goddess.dimission.enums;

/**
 * 确认类型
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:06]
 * @Description: [ 确认类型 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ConfirmationType {
    /**
     * 未确认
     */
    NONE(0),
    /**
     * 确认
     */
    AFFIRM(1),
    /**
     * 否认
     */
    DENY(2);


    private int value;

    public int getValue() {
        return value;
    }

    ConfirmationType(int value) {
        this.value = value;
    }
}
