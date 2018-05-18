package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.marketdevelopment.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 年计划(业务方向)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:25 ]
 * @Description: [ 年计划(业务方向) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_planyearbusiness")
public class PlanYearBusiness extends BaseEntity {

    /**
     * 年份id
     */
    @Column(name = "yearId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '年份id'")
    private String yearId;

    /**
     * 业务方向分类
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向分类'")
    private String businessType;

    /**
     * 工作量权重(%)
     */
    @Column(name = "workWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工作量权重(%)'")
    private Double workWeight;

    /**
     * 各业务类型计划金额（万元）
     */
    @Column(name = "planMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各业务类型计划金额（万元）'")
    private Double planMoney;

    /**
     * 各业务类型实际金额（万元）
     */
    @Column(name = "actualMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各业务类型实际金额（万元）'")
    private Double actualMoney;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'",insertable = false)
    private Status status;


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
}