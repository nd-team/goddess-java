package com.bjike.goddess.voucher.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

public class VoucherExportToExport {
    /**
     * 会计科目
     */
    @ExcelHeader(name = "会计科目")
    private String accountantCourse;
    /**
     * 会计名称
     */
    @ExcelHeader(name = "会计名称")
    private String CourseName;
    /**
     * 借方金额
     */
    @ExcelHeader(name = "借方金额")
    private Double borrowMoney;
    /**
     * 贷方金额
     */
    @ExcelHeader(name = "贷方金额")
    private Double loanMoney;
    /**
     * 借方金额总数
     */
    @ExcelHeader(name = "借方金额总数")
    private Double borrowMoneyCount;
    /**
     * 贷方金额总数
     */
    @ExcelHeader(name = "贷方金额总数")
    private Double loanMoneyCount;

    public String getAccountantCourse() {
        return accountantCourse;
    }

    public void setAccountantCourse(String accountantCourse) {
        this.accountantCourse = accountantCourse;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
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

    public Double getBorrowMoneyCount() {
        return borrowMoneyCount;
    }

    public void setBorrowMoneyCount(Double borrowMoneyCount) {
        this.borrowMoneyCount = borrowMoneyCount;
    }

    public Double getLoanMoneyCount() {
        return loanMoneyCount;
    }

    public void setLoanMoneyCount(Double loanMoneyCount) {
        this.loanMoneyCount = loanMoneyCount;
    }
}
