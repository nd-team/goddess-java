package com.bjike.goddess.recruit.type;

/**
 * 审核类型
 *
 * @Author: [sunfengtao]
 * @Date: [17-3-9 下午4:10]
 * @Package:[ com.bjike.goddess.recruit.type ]
 * @Description: []
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
    DENIED(2)
    ;

    private int value;

    AuditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
