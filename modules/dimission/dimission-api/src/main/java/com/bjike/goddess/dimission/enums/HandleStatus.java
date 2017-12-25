package com.bjike.goddess.dimission.enums;

/**
 * 离职办理状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:04]
 * @Description: [ 离职办理状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum HandleStatus {

    /**
     * 预备离职
     */
    PREPARE(0),
    /**
     * 未离职
     */
    NOT(1),
    /**
     * 确认离职
     */
    AFFIRM(2);

    private int value;

    public int getValue() {
        return value;
    }

    HandleStatus(int value) {
        this.value = value;
    }

}
