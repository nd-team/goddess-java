package com.bjike.goddess.annual.enums;

/**
 * 审核类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 16:14:41 ]
 * @Description: [ 审核类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
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
     * 拒绝
     */
    , DENIED(2);

    private int code;

    private AuditType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
