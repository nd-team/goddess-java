package com.bjike.goddess.attainment.enums;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-06 14:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditType {
    /**
     * 未处理
     */
    NONE(0),
    /**
     * 通过
     */
    ALLOWED(1)
    /**
     * 未通过
     */
    , DENIED(2);


    private int value;

    AuditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
