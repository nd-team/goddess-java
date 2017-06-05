package com.bjike.goddess.marketactivitymanage.type;

/**
 * 审核枚举类型
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-20 09:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditType {
    /**
     * 未通过
     */
    NONE(0),
    /**
     * 通过
     */
    ALLOWED(1),
    /**
     * 拒绝
     */
    DENIED(2);

    private int value;

    AuditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
