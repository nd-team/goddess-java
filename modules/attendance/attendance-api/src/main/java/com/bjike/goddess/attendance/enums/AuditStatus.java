package com.bjike.goddess.attendance.enums;

/**
 * 加班审核状态
 * @Author: [tanghaixiang]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditStatus {
    /**
     * 未处理
     */
    NONE(0),
    /**
     * 通过
     */
    AGREE(1),
    /**
     * 不通过
     */
    REJECT(2);
    private int code;

    AuditStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
