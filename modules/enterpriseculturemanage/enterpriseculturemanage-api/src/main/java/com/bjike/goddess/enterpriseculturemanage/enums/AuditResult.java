package com.bjike.goddess.enterpriseculturemanage.enums;

/**
 * 审核结果
 *
 * @Author: [Jason]
 * @Date: [17-3-31 下午5:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AuditResult {

    /**
     * 未处理
     */
    NOTDEAL(0),
    /**
     * 通过
     */
    PASS(1),
    /**
     * 不通过
     */
    REFUSE(2);

    private int code;

    AuditResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
