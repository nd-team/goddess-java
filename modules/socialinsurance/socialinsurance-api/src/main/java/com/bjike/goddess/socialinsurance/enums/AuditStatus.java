package com.bjike.goddess.socialinsurance.enums;

/**
 * 审核状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-17 18:01]
 * @Description: [审核状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditStatus {

    /**
     * 未审核
     */
    NONE(0),
    /**
     * 已审核
     */
    CHECK(1);

    private int code;

    AuditStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
