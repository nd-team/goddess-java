package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 利润增减率分析管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_profitregulationadvice")
public class ProfitRegulationAdvice extends BaseEntity {

    /**
     * 净利润增减最小值
     */
    @Column(name = "netProfitMin", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '净利润增减最小值'")
    private Double netProfitMin;

    /**
     * 净利润增减最大值
     */
    @Column(name = "netProfitMax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '净利润增减最大值'")
    private Double netProfitMax;

    /**
     * 利润总额增减最小值
     */
    @Column(name = "profitMin", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '利润总额增减最小值'")
    private Double profitMin;

    /**
     * 利润总额增减最大值
     */
    @Column(name = "profitMax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '利润总额增减最大值'")
    private Double profitMax;

    /**
     * 营业利润增减最小值
     */
    @Column(name = "incomeMin", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '营业利润增减最小值'")
    private Double incomeMin;

    /**
     * 营业利润增减最大值
     */
    @Column(name = "incomeMax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '营业利润增减最大值'")
    private Double incomeMax;

    /**
     * 管理建议
     */
    @Column(name = "advice", nullable = false, columnDefinition = "MEDIUMTEXT   COMMENT '管理建议'")
    private String advice;


    public Double getNetProfitMin() {
        return netProfitMin;
    }

    public void setNetProfitMin(Double netProfitMin) {
        this.netProfitMin = netProfitMin;
    }

    public Double getNetProfitMax() {
        return netProfitMax;
    }

    public void setNetProfitMax(Double netProfitMax) {
        this.netProfitMax = netProfitMax;
    }

    public Double getProfitMin() {
        return profitMin;
    }

    public void setProfitMin(Double profitMin) {
        this.profitMin = profitMin;
    }

    public Double getProfitMax() {
        return profitMax;
    }

    public void setProfitMax(Double profitMax) {
        this.profitMax = profitMax;
    }

    public Double getIncomeMin() {
        return incomeMin;
    }

    public void setIncomeMin(Double incomeMin) {
        this.incomeMin = incomeMin;
    }

    public Double getIncomeMax() {
        return incomeMax;
    }

    public void setIncomeMax(Double incomeMax) {
        this.incomeMax = incomeMax;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}