package com.bjike.goddess.lendreimbursement.enums;

/**
 * 借款的手机详情按钮显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [借款的手机详情按钮显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum LendPhoneShowStatus {


    /**
     * 待审核中的审核
     */
    WAITAUDIT(0),
    /**
     * 待付款的付款
     */
    WAITPAY(1),
    /**
     * 确认收款
     */
    SURERECEIVE(2),
    /**
     * 还款待核对
     */
    WAITRETURNCHECK(3),
    /**
     * 还款已核对
     */
    RETURNCHECK(4),
    /**
     * 待解冻
     */
    WAITTHAW(5),
    /**
     * 有误编辑
     */
    EDITERROR(6);

    private int code;

    LendPhoneShowStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
