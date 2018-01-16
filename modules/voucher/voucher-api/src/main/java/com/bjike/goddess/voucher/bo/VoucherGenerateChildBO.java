package com.bjike.goddess.voucher.bo;

import java.io.Serializable;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-15 10:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VoucherGenerateChildBO implements Serializable{

    /**
     * id
     */
    private String id;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * 三级科目
     */
    private String thirdSubject;

    /**
     * 借方金额
     */
    private Double borrowMoney;

    /**
     * 贷方金额
     */
    private Double loanMoney;

    public VoucherGenerateChildBO() {
    }

    public VoucherGenerateChildBO(String firstSubject, String secondSubject, String thirdSubject, Double borrowMoney, Double loanMoney, String id) {
        this.firstSubject = firstSubject;
        this.secondSubject = secondSubject;
        this.thirdSubject = thirdSubject;
        this.borrowMoney = borrowMoney;
        this.loanMoney = loanMoney;
        this.id = id;
    }

    @Override
    public String toString() {
        return "VoucherGenerateChildTO{" +
                "firstSubject='" + firstSubject + '\'' +
                ", secondSubject='" + secondSubject + '\'' +
                ", thirdSubject='" + thirdSubject + '\'' +
                ", borrowMoney=" + borrowMoney +
                ", loanMoney=" + loanMoney +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
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
