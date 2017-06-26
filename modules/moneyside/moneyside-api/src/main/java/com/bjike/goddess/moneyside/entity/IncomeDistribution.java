package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 收益比例分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_incomedistribution")
public class IncomeDistribution extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 项目名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String innerProject;

    /**
     * 一次分配比例(%)
     */
    @Column(name = "primaryProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '一次分配比例(%)'")
    private Double primaryProportion;

    /**
     * 入资到账时间比例(%)
     */
    @Column(name = "proportionGroup", columnDefinition = "DECIMAL(10,2)   COMMENT '入资到账时间比例(%)'")
    private Double proportionGroup;

    /**
     * 入资信用度比例(%)
     */
    @Column(name = "creditScale", columnDefinition = "DECIMAL(10,2)   COMMENT '入资信用度比例(%)'")
    private Double creditScale;

    /**
     * 其他
     */
    @Column(name = "other", columnDefinition = "VARCHAR(255)   COMMENT '其他'")
    private String other;
    /**
     * 投资分配时间
     */
    @Column(name = "incomeDistributionTime", columnDefinition = "DATE   COMMENT '投资分配时间'")
    private LocalDate incomeDistributionTime;
    /**
     * 投资分配比例(%)
     */
    @Column(name = "proportionInvestment", columnDefinition = "DECIMAL(10,2)   COMMENT '投资分配比例(%)'")
    private Double proportionInvestment;

    /**
     * 风险控制保证金比例(%)
     */
    @Column(name = "riskControlMarginRatio", columnDefinition = "DECIMAL(10,2)   COMMENT '风险控制保证金比例(%)'")
    private Double riskControlMarginRatio;

    /**
     * 总分配比例(%)
     */
    @Column(name = "totalProportion", columnDefinition = "DECIMAL(10,2)   COMMENT '总分配比例(%)'")
    private Double totalProportion;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public Double getPrimaryProportion() {
        return primaryProportion;
    }

    public void setPrimaryProportion(Double primaryProportion) {
        this.primaryProportion = primaryProportion;
    }

    public Double getProportionGroup() {
        return proportionGroup;
    }

    public void setProportionGroup(Double proportionGroup) {
        this.proportionGroup = proportionGroup;
    }

    public Double getCreditScale() {
        return creditScale;
    }

    public void setCreditScale(Double creditScale) {
        this.creditScale = creditScale;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Double getProportionInvestment() {
        return proportionInvestment;
    }

    public void setProportionInvestment(Double proportionInvestment) {
        this.proportionInvestment = proportionInvestment;
    }

    public LocalDate getIncomeDistributionTime() {
        return incomeDistributionTime;
    }

    public void setIncomeDistributionTime(LocalDate incomeDistributionTime) {
        this.incomeDistributionTime = incomeDistributionTime;
    }

    public Double getRiskControlMarginRatio() {
        return riskControlMarginRatio;
    }

    public void setRiskControlMarginRatio(Double riskControlMarginRatio) {
        this.riskControlMarginRatio = riskControlMarginRatio;
    }

    public Double getTotalProportion() {
        return totalProportion;
    }

    public void setTotalProportion(Double totalProportion) {
        this.totalProportion = totalProportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}