package com.bjike.goddess.lendreimbursement.enums;

/**
 * 报销数据的手机按钮显示状态
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-04-06 10:47]
 * @Description: [报销数据的手机按钮显示状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ReimPhoneShowStatus {

    /**
     * 去寄件
     */
    GOSEND(0),
    /**
     * 已寄件的详情
     */
    SENDDETAIL(1),
    /**
     * 去审核
     */
    GOAUDIT(2),
    /**
     * 去分析
     */
    GOANALISIS(3),
    /**
     * 核对有误和去付款
     */
    GOCHECK(4),
    /**
     * 待解冻重新编辑
     */
    WAITTHAWEDIT(5),
    /**
     * 其他
     */
    NONE(6),
    /**
     * 冻结审核
     */
    CONGELAUDIT(7);

    private int code;

    ReimPhoneShowStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
