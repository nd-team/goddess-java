package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [zhuangkaiqin]
 * @Date: [20171102 14:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SubjectCollectBO extends BaseBO {

//    /**
//     * 一级科目
//     */
//    private String firstSubject;
//
//    /**
//     * 地区
//     */
//    private String area;
//
//    /**
//     * 项目名称
//     */
//    private String projectName;
//
//    /**
//     * 项目组/部门
//     */
//    private String projectGroup;

    /**
     * 年初数
     */
    private Double beginAmount;

    /**
     * 年末数
     */
    private Double endAmount;

    /**
     * 本年累计数
     */
    private Double yearAmount;

    /**
     * 本期发生额
     */
    private Double currentAmount;

//    /**
//     * 期初贷方余额
//     */
//    private Double beginningCreditAmount;
//
//    /**
//     * 本期借方发生额
//     */
//    private Double issueDebitAmount;
//
//    /**
//     * 本期贷方发生额
//     */
//    private Double issueCreditAmount;
//
//    /**
//     * 期末借方余额
//     */
//    private Double endDebitAmount;
//
//    /**
//     * 期末贷方余额
//     */
//    private Double endCreditAmount;


    public Double getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(Double yearAmount) {
        this.yearAmount = yearAmount;
    }

    public Double getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(Double beginAmount) {
        this.beginAmount = beginAmount;
    }

    public Double getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Double endAmount) {
        this.endAmount = endAmount;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }
}
