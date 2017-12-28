package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款的还款核对流程状态
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款的还款核对流程状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendRetunStatus {

    /**
     * 未处理
     */
    NONE(0),
    /**
     * 还款中的待核对(审核人(显示去核对)和非审核人(显示去还款))
     */
    WAITRETURNCHECK(1),
    /**
     * 审核通过
     */
    PASS(2),
    /**
     * 审核不通过
     */
    NOTPASS(3)
    ;

    private int code;

    LendRetunStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }




}
