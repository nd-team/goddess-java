package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-11 18:29]
 * @Description: [报销状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimStatus {

    /**
     * 未处理
     */
    NONE(0),
    /**
     * 分析：审核通过
     */
    PASS(1),
    /**
     * 分析：审核不通过
     */
    NOTPASS(2),
    /**
     * 分析：申请冻结
     */
    CONGEL(3),
    /**
     * 负责人确认冻结
     */
    CHARGECONGEL(4)
    ;

    private int code;

    ReimStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
