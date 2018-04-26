package com.bjike.goddess.fundrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2018-04-20 17:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FundRecordCollectBO extends BaseBO{


    /**
     * 收入
     */
    private String recordDate;

    /**
     * 收入
     */
    private Double income;

    /**
     * 支出
     */
    private Double expenditure;

    /**
     * 上月余额
     */
    private Double lastAmount;

    /**
     * 本月余额
     */
    private Double thisAmount;

    /**
     * 本月发生额
     */
    private Double incurredAmount;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
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

    public Double getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Double lastAmount) {
        this.lastAmount = lastAmount;
    }

    public Double getThisAmount() {
        return thisAmount;
    }

    public void setThisAmount(Double thisAmount) {
        this.thisAmount = thisAmount;
    }

    public Double getIncurredAmount() {
        return incurredAmount;
    }

    public void setIncurredAmount(Double incurredAmount) {
        this.incurredAmount = incurredAmount;
    }
}
