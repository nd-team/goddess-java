package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 业务方向类型数据业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 02:11 ]
 * @Description: [ 业务方向类型数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthBusinessDataBO extends BaseBO {

    /**
     * 月份金额id
     */
    private String monthMoneyId;

    /**
     * 业务方向类型
     */
    private String businessType;

    /**
     * 工作量权重(%)
     */
    private Double workWeight;

    /**
     * 各业务类型目标金额（万元）/年
     */
    private Double targerMoney;

    /**
     * 各业务类型实际金额（万元）/年
     */
    private Double actualMoney;

    /**
     * 各业务类型差异金额（万元）/年
     */
    private Double difference;

    private List<MonthSubjectDataBO> monthSubjectDataVOs;


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

    public List<MonthSubjectDataBO> getMonthSubjectDataVOs() {
        return monthSubjectDataVOs;
    }

    public void setMonthSubjectDataVOs(List<MonthSubjectDataBO> monthSubjectDataVOs) {
        this.monthSubjectDataVOs = monthSubjectDataVOs;
    }
}