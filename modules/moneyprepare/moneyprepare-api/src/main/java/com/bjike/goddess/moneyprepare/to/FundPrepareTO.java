package com.bjike.goddess.moneyprepare.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 资金准备
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundPrepareTO extends BaseTO {
    public interface TestAdd{}

    /**
     * 时间
     */
    @NotNull(groups = {FundPrepareTO.TestAdd.class} , message = "时间不能为空,格式为yyyy-MM-dd")
    private String time;

    /**
     * 一级科目
     */
    private String firstSubject;

    /**
     * 二级科目
     */
    @NotNull(groups = {FundPrepareTO.TestAdd.class} , message = "二级科目不能为空")
    private String secondSubject;

    /**
     * 准备金
     */
    @NotNull(groups = {FundPrepareTO.TestAdd.class} , message = "准备金不能为空")
    private Double fund;



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

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}