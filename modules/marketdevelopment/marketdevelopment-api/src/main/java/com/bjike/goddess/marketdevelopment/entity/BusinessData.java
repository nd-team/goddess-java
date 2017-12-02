package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务方向类型数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 02:11 ]
 * @Description: [ 业务方向类型数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_businessdata")
public class BusinessData extends BaseEntity {

    /**
     * 月份金额id
     */
    @Column(name = "monthMoneyId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '月份金额id'")
    private String monthMoneyId;

    /**
     * 业务方向类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向类型'")
    private String businessType;

    /**
     * 工作量权重(%)
     */
    @Column(name = "workWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工作量权重(%)'")
    private Double workWeight;

    /**
     * 各业务类型目标金额（万元）/年
     */
    @Column(name = "targerMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各业务类型目标金额（万元）/年'")
    private Double targerMoney;

    /**
     * 各业务类型实际金额（万元）/年
     */
    @Column(name = "actualMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各业务类型实际金额（万元）/年'")
    private Double actualMoney;

    /**
     * 各业务类型差异金额（万元）/年
     */
    @Column(name = "difference", columnDefinition = "DECIMAL(10,2)   COMMENT '各业务类型差异金额（万元）/年'")
    private Double difference;


    public String getMonthMoneyId() {
        return monthMoneyId;
    }

    public void setMonthMoneyId(String monthMoneyId) {
        this.monthMoneyId = monthMoneyId;
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

    public Double getTargerMoney() {
        return targerMoney;
    }

    public void setTargerMoney(Double targerMoney) {
        this.targerMoney = targerMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }
}