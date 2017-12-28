package com.bjike.goddess.voucher.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 提供给资金核对的部分数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 提供给资金核对的部分数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PartOptionVO extends BaseBO {

    /**
     * 一级科目名称
     */
    private String firstName;
    /**
     * 二级科目名称
     */
    private String secondName;
    /**
     * 三级科目名称
     */
    private String thirdName;

    /**
     * 贷方金额
     */
    private Double loanMoney;
    /**
     * 借方金额
     */
    private Double borrowMoney;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }
}