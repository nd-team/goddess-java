package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

public class VoucherTotalGoldBO extends BaseBO {
    /**
     * 借方金额总数
     */
    private Double borrowMoneyCount;
    /**
     * 贷方金额总数
     */
    private Double loanMoneyCount;

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
