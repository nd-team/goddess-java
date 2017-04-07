package com.bjike.goddess.qualifications.enums;

/**
 * 办理状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-31 19:04]
 * @Description: [ 办理状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum HandleStatus {

    /**
     * 未办理
     */
    NONE(0)
    /**
     * 办理成功
     */
    , SUCCESS(1)
    /**
     * 办理失败
     */
    , FAIL(2);


    private int value;

    HandleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
