package com.bjike.goddess.voucher.vo;

public class VoucherSummaryVO {
    /**
     * 会计科目
     */
    private String accountantCourse;
    /**
     * 会计名称
     */
    private String courseName;
    /**
     * 借方金额
     */
    private Double borrowMoney;
    /**
     * 贷方金额
     */
    private Double loanMoney;
    /**
     * 类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountantCourse() {
        return accountantCourse;
    }

    public void setAccountantCourse(String accountantCourse) {
        this.accountantCourse = accountantCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
