package com.bjike.goddess.recruit.type;

/**
 * 审核类型
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
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
