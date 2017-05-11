package com.bjike.goddess.fundrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金流水业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthCollectBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 上月余额
     */
    private Double lastBalance;

    /**
     * 本月余额
     */
    private Double currentBalance;

    /**
     * 收入
     */
    private Double income;

    /**
     * 支出
     */
    private Double expenditure;

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

    public Double getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(Double lastBalance) {
        this.lastBalance = lastBalance;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }
}