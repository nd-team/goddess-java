package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-26 14:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PredictPayTO extends BaseTO{
    /**
     * 预计付款时间
     */
    private String expectPayDate;

    /**
     * 付款计划
     */
    private String paymentSchedule;

    /**
     * 核对意见
     */
    private String moneyModuleIdea;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 问题描述
     */
    private String problemDes;


    public String getExpectPayDate() {
        return expectPayDate;
    }

    public void setExpectPayDate(String expectPayDate) {
        this.expectPayDate = expectPayDate;
    }

    public String getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(String paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public String getMoneyModuleIdea() {
        return moneyModuleIdea;
    }

    public void setMoneyModuleIdea(String moneyModuleIdea) {
        this.moneyModuleIdea = moneyModuleIdea;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDes() {
        return problemDes;
    }

    public void setProblemDes(String problemDes) {
        this.problemDes = problemDes;
    }
}
