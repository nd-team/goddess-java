package com.bjike.goddess.managepromotion.enums;

/**
 * 审核状态
 *
 * @Author: [xiazhili]
 * @Date: [2017-05-23 08:50]
 * @Description: [审核状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditStatus {

    /**
     * 审核中
     */
    AUDIT(0),
    /**
     * 通过
     */
    PASS(1),
    /**
     * 不通过
     */
    NOPASS(2),
    ;

    private int code;

    AuditStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
