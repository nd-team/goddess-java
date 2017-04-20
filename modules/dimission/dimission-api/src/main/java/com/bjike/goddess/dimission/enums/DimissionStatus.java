package com.bjike.goddess.dimission.enums;

/**
 * 离职状态
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 14:58]
 * @Description: [ 离职状态 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum DimissionStatus {
    /**
     * 申请离职
     */
    APPLY(0),
    /**
     * 离职失败
     */
    FAIL(1),
    /**
     * 成功离职
     */
    SUCCESS(2);

    private int value;

    public int getValue() {
        return value;
    }

    DimissionStatus(int value) {
        this.value = value;
    }
}
