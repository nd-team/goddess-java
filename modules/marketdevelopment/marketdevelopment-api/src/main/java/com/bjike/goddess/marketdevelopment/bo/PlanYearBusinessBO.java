package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.marketdevelopment.enums.Status;

import java.util.List;

/**
 * 年计划(业务方向)业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:25 ]
 * @Description: [ 年计划(业务方向)业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanYearBusinessBO extends BaseBO {

    /**
     * 年份id
     */
    private String yearId;

    /**
     * 业务方向分类
     */
    private String businessType;

    /**
     * 工作量权重(%)
     */
    private Double workWeight;

    /**
     * 各业务类型计划金额（万元）
     */
    private Double planMoney;

    /**
     * 各业务类型实际金额（万元）
     */
    private Double actualMoney;

    /**
     * 状态
     */
    private Status status;

    List<PlanYearSubjectBO> planYearSubjectVOs;


    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getWorkWeight() {
        return workWeight;
    }

    public void setWorkWeight(Double workWeight) {
        this.workWeight = workWeight;
    }

    public Double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Double planMoney) {
        this.planMoney = planMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<PlanYearSubjectBO> getPlanYearSubjectVOs() {
        return planYearSubjectVOs;
    }

    public void setPlanYearSubjectVOs(List<PlanYearSubjectBO> planYearSubjectVOs) {
        this.planYearSubjectVOs = planYearSubjectVOs;
    }
}