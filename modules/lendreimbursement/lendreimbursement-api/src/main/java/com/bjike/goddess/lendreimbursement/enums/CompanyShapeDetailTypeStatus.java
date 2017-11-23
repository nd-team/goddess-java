package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销公司详细图筛选状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销公司详细图筛选状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CompanyShapeDetailTypeStatus {
    /**
     * 申报报销
     */
    REIMRECORD(0),
    /**
     * 报销已支付
     */
    REIMHASPAY(1),
    /**
     * 申请借款
     */
    LENDRECORD(2),
    /**
     * 借款已支付(已还款核对和帐务核对的借款)
     */
    LENDHASCHECK(2);

    private int code;

    CompanyShapeDetailTypeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
