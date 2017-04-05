package com.bjike.goddess.receivable.enums;

/**
 * 审核枚举
 * @Author: [xiazhili]
 * @Date: [17-3-29]
 * @Description: [审核枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum AuditStatus {
    NOT(0)//未审核、支付
    , PASS(1)//已审核、支付
    ;
    private int code;

    private AuditStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
