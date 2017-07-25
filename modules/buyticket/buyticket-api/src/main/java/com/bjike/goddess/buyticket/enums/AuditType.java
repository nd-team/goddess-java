package com.bjike.goddess.buyticket.enums;

/**
 * 审核人类型枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [审核人类型枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
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


    private int code;

    AuditType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
