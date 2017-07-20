package com.bjike.goddess.moneyprepare.vo;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-11 16:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FundPrepareApplyVO {
    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    private String secondSubject;

    /**
     * x-3月准备金
     */
    private Double fund3;
    /**
     * x-2月准备金
     */
    private Double fund2;
    /**
     * x-1月准备金
     */
    private Double fund1;
    /**
     * x月准备金
     */
    private Double fund;

    /**
     * 当月资金准备的id
     */
    private String fundPrepareId;


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

    public Double getFund3() {
        return fund3;
    }

    public void setFund3(Double fund3) {
        this.fund3 = fund3;
    }

    public Double getFund2() {
        return fund2;
    }

    public void setFund2(Double fund2) {
        this.fund2 = fund2;
    }

    public Double getFund1() {
        return fund1;
    }

    public void setFund1(Double fund1) {
        this.fund1 = fund1;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public String getFundPrepareId() {
        return fundPrepareId;
    }

    public void setFundPrepareId(String fundPrepareId) {
        this.fundPrepareId = fundPrepareId;
    }
}
