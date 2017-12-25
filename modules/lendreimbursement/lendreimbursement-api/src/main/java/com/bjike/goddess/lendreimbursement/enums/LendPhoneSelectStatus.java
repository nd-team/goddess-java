package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款数据的手机列表显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款数据的手机列表显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendPhoneSelectStatus {

    /**
     * 全部
     */
    ALL(0),
    /**
     * 待审核
     */
    WAITAUDIT(1),
    /**
     * 待付款
     */
    WAITPAY(2),
    /**
     * 已借款
     */
    HASLEND(3),
    /**
     * 待还款
     */
    WAITRETURN(4),
    /**
     * 已还款
     */
    HASRETURN(5),
    /**
     * 待解冻
     */
    WAITTHAW(6);

    private int code;

    LendPhoneSelectStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
