package com.bjike.goddess.materialtransfer.type;

/**
 * 审核状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-04-28 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditState {

    /**
     * 未审核
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

    AuditState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
