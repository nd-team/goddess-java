package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销数据的手机列表显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销数据的手机列表显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimPhoneSelectStatus {

    /**
     * 全部
     */
    ALL(0),
    /**
     * 待审核
     */
    WAITAUDIT(1),
    /**
     * 待分析
     */
    WAITANALISIS(2),
    /**
     * 待核对
     */
    WAITCHECK(3),
    /**
     * 已报销
     */
    HASREIM(4),
    /**
     * 待解冻
     */
    WAITTHAW(5);

    private int code;

    ReimPhoneSelectStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
