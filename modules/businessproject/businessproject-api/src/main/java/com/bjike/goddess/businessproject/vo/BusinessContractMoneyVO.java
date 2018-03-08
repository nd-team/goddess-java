package com.bjike.goddess.businessproject.vo;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 商务合同金额信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractMoneyVO {

    private String id;

    /**
     * 派工金额
     */
    private Double taskMoney;

    /**
     * 立项金额
     */
    private Double makeMoney;

    /**
     * 预估金额
     */
    private Double forecastMoney;

    /**
     * 预估转正完成金额
     */
    private Double forecastFinishMoney;

    /**
     * 预估转正进行金额
     */
    private Double forecastMarchMoney;

    /**
     * 预估市场亏损金额
     */
    private Double estimatedMarketLosses;

    /**
     * 预估确认担保人
     */
    private String guarantor;

    /**
     * 预估确认担保人审核意见
     */
    private String guarantorIdea;

    /**
     * 预估项目是否确认实施
     */
    private Boolean implement;

    /**
     * 项目经理意见
     */
    private String managerIdea;

    /**
     * 规划模块分析意见
     */
    private String planIdea;

    /**
     * 预算模块分析意见
     */
    private String budgetIdea;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public Double getForecastFinishMoney() {
        return forecastFinishMoney;
    }

    public void setForecastFinishMoney(Double forecastFinishMoney) {
        this.forecastFinishMoney = forecastFinishMoney;
    }

    public Double getForecastMarchMoney() {
        return forecastMarchMoney;
    }

    public void setForecastMarchMoney(Double forecastMarchMoney) {
        this.forecastMarchMoney = forecastMarchMoney;
    }

    public Double getEstimatedMarketLosses() {
        return estimatedMarketLosses;
    }

    public void setEstimatedMarketLosses(Double estimatedMarketLosses) {
        this.estimatedMarketLosses = estimatedMarketLosses;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getGuarantorIdea() {
        return guarantorIdea;
    }

    public void setGuarantorIdea(String guarantorIdea) {
        this.guarantorIdea = guarantorIdea;
    }

    public Boolean getImplement() {
        return implement;
    }

    public void setImplement(Boolean implement) {
        this.implement = implement;
    }

    public String getManagerIdea() {
        return managerIdea;
    }

    public void setManagerIdea(String managerIdea) {
        this.managerIdea = managerIdea;
    }

    public String getPlanIdea() {
        return planIdea;
    }

    public void setPlanIdea(String planIdea) {
        this.planIdea = planIdea;
    }

    public String getBudgetIdea() {
        return budgetIdea;
    }

    public void setBudgetIdea(String budgetIdea) {
        this.budgetIdea = budgetIdea;
    }
}