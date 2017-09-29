package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款的身份状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款的身份状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendIndentityStatus {

    /**
     * 负责人
     */
    CHARGER(0),
    /**
     * 财务运营部
     */
    FINACER(1),
    /**
     * 总经办
     */
    MANAGER(2)
    ;

    private int code;

    LendIndentityStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }




}
