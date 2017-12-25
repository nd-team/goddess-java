package com.bjike.goddess.voucher.vo;

/**
 * 柱状图数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 柱状图数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HistogramVO{

    /**
     * 月份
     */
    private String month;

    /**
     * 借方金额
     */
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    private Double loanMoney;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(Double borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }
}