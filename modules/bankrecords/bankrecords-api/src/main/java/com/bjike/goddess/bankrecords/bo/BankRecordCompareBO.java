package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 分析对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordCompareBO extends BaseBO {
    /**
     *日期
     */
    private String years;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;

    /**
     * 银行流水余额
     */
    private Double bankBalance;

    /**
     * 资金流水余额
     */
    private Double balanceSubtract;

    /**
     * 差额
     */
    private Double fundBalance;

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Double getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(Double fundBalance) {
        this.fundBalance = fundBalance;
    }

    public Double getBalanceSubtract() {
        return balanceSubtract;
    }

    public void setBalanceSubtract(Double balanceSubtract) {
        this.balanceSubtract = balanceSubtract;
    }
}