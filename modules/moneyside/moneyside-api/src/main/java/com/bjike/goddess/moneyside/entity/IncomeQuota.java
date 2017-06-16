package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 收益分配额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:28 ]
 * @Description: [ 收益分配额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_incomequota")
public class IncomeQuota extends BaseEntity {

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
    @Column(name = "proportionGroup",  columnDefinition = "DECIMAL(10,2)   COMMENT '入资到账时间比例(%)'")
    private Double proportionGroup;

    /**
     * 入资信用度比例(%)
     */
    @Column(name = "creditScale",  columnDefinition = "DECIMAL(10,2)   COMMENT '入资信用度比例(%)'")
    private Double creditScale;

    /**
     * 其他
     */
    @Column(name = "other",  columnDefinition = "VARCHAR(255)   COMMENT '其他'")
    private String other;

    /**
     * 投资分配额(该项目的累计投资金额*投资分配比例)
     */
    @Column(name = "investmentAllocation",  columnDefinition = "DECIMAL(10,2)   COMMENT '投资分配额(该项目的累计投资金额*投资分配比例)'")
    private Double investmentAllocation;

    /**
     * 风险控制保证金额（该项目的累计投资金额*风险控制保证金比例）
     */
    @Column(name = "riskControlGuaranteeAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '风险控制保证金额（该项目的累计投资金额*风险控制保证金比例）'")
    private Double riskControlGuaranteeAmount;

    /**
     * 总分配额（该项目的累计投资金额*总分配比例）
     */
    @Column(name = "totalQuota",  columnDefinition = "DECIMAL(10,2)   COMMENT '总分配额（该项目的累计投资金额*总分配比例）'")
    private Double totalQuota;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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

    public Double getInvestmentAllocation() {
        return investmentAllocation;
    }

    public void setInvestmentAllocation(Double investmentAllocation) {
        this.investmentAllocation = investmentAllocation;
    }

    public Double getRiskControlGuaranteeAmount() {
        return riskControlGuaranteeAmount;
    }

    public void setRiskControlGuaranteeAmount(Double riskControlGuaranteeAmount) {
        this.riskControlGuaranteeAmount = riskControlGuaranteeAmount;
    }

    public Double getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(Double totalQuota) {
        this.totalQuota = totalQuota;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}