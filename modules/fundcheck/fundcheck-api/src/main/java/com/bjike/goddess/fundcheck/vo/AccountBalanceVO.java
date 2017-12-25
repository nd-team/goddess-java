package com.bjike.goddess.fundcheck.vo;

/**
 * 账上余额表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountBalanceVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期
     */
    private String date;

    /**
     * 期初余额
     */
    private Double beginningBalance;
    /**
     * 账务收入
     */
    private Double accountIncome;
    /**
     * 账务支出
     */
    private Double accountSpend;

    /**
     * 账上余额
     */
    private Double accountBalance;

    /**
     * 资金差异(期初余额+账上支出-财务支出)
     */
    private Double fundsDifference;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(Double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public Double getAccountIncome() {
        return accountIncome;
    }

    public void setAccountIncome(Double accountIncome) {
        this.accountIncome = accountIncome;
    }

    public Double getAccountSpend() {
        return accountSpend;
    }

    public void setAccountSpend(Double accountSpend) {
        this.accountSpend = accountSpend;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getFundsDifference() {
        return fundsDifference;
    }

    public void setFundsDifference(Double fundsDifference) {
        this.fundsDifference = fundsDifference;
    }
}