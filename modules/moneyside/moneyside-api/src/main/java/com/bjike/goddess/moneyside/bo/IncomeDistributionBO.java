package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 收益比例分配业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeDistributionBO extends BaseBO {

    /**
     * 投资人
     */
    private String investor;

    /**
     * 项目名称
     */
    private String innerProject;

    /**
     * 一次分配比例(%)
     */
    private Double primaryProportion;

    /**
     * 入资到账时间比例(%)
     */
    private Double proportionGroup;
    /**
     * 投资分配时间
     */
    private String incomeDistributionTime;

    /**
     * 入资信用度比例(%)
     */
    private Double creditScale;

    /**
     * 其他
     */
    private String other;

    /**
     * 投资分配比例(%)
     */
    private Double proportionInvestment;

    /**
     * 风险控制保证金比例(%)
     */
    private Double riskControlMarginRatio;

    /**
     * 总分配比例(%)
     */
    private Double totalProportion;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


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

    public String getIncomeDistributionTime() {
        return incomeDistributionTime;
    }

    public void setIncomeDistributionTime(String incomeDistributionTime) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}