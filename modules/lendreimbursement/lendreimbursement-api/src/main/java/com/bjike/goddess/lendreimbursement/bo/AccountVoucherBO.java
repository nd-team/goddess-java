package com.bjike.goddess.lendreimbursement.bo;

import com.bjike.goddess.lendreimbursement.enums.Words;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 记账凭证数据
 * @Author: [tanghaixiang]
 * @Date: [2017-04-07 17:43]
 * @Description: [记账凭证数据]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AccountVoucherBO implements Serializable {


    /**
     * 借款事由
     */
    private String borrowResion;

    /**
     * 会计记账科目
     */
    private String subject;

    /**
     * 借款金额
     */
    private Double borrowMoney;

    /**
     * 贷款金额
     */
    private Double loanMoney;

    /**
     * 凭记字
     */
    private Words words;

    /**
     * 制单人
     */
    private String ticketUser;

    /**
     * 日期
     */
    private String dates;

    /**
     * 期数
     */
    private String stage;

    /**
     * 单据数量
     */
    private Double ticketNum;

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public String getBorrowResion() {
        return borrowResion;
    }

    public void setBorrowResion(String borrowResion) {
        this.borrowResion = borrowResion;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getTicketUser() {
        return ticketUser;
    }

    public void setTicketUser(String ticketUser) {
        this.ticketUser = ticketUser;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Double getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Double ticketNum) {
        this.ticketNum = ticketNum;
    }
}
