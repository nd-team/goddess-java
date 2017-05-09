package com.bjike.goddess.devicerepair.type;

/**
 * 审核状态
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-03 16:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditState {

    /**
     * 未审核
     */
    UNAUDITED(0),
    /**
     * 已审核
     */
    AUDITED(1),
    /**
     * 审核不通过
     */
    DENY(2);

    private int value;

    AuditState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
