package com.bjike.goddess.moneyprepare.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金准备业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundPrepareApplyBO extends BaseBO {


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
    private String id;


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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}