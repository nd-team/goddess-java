package com.bjike.goddess.moneyside.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 收益比例分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeDistributionTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    /**
     * 投资人
     */
    @NotBlank(groups = {IncomeDistributionTO.TestAdd.class},message = "投资人不能为空")
    private String investor;

    /**
     * 项目名称
     */
    @NotBlank(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "项目名称不能为空")
    private String innerProject;

    /**
     * 一次分配比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "一次分配比例(%)不能为空")
    private Double primaryProportion;

    /**
     * 入资到账时间比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "入资到账时间比例(%)不能为空")
    private Double proportionGroup;

    /**
     * 入资信用度比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "入资信用度比例(%)不能为空")
    private Double creditScale;
    /**
     * 投资分配时间
     */
    @NotBlank(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "投资分配时间不能为空")
    private String incomeDistributionTime;


    /**
     * 其他
     */
    @NotBlank(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "其他不能为空")
    private String other;

    /**
     * 投资分配比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "投资分配比例(%)不能为空")
    private Double proportionInvestment;

    /**
     * 风险控制保证金比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "风险控制保证金比例(%)不能为空")
    private Double riskControlMarginRatio;

    /**
     * 总分配比例(%)
     */
    @NotNull(groups = {IncomeDistributionTO.TestAdd.class,IncomeDistributionTO.TestEdit.class},message = "总分配比例(%)不能为空")
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

    public String getIncomeDistributionTime() {
        return incomeDistributionTime;
    }

    public void setIncomeDistributionTime(String incomeDistributionTime) {
        this.incomeDistributionTime = incomeDistributionTime;
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