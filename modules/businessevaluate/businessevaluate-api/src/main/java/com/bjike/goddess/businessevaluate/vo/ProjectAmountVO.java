package com.bjike.goddess.businessevaluate.vo;

/**
 * 项目金额表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 项目金额表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectAmountVO {

    /**
     * id
     */
    private String id;
    /**
     * 成本
     */
    private Double cost;

    /**
     * 预算成本
     */
    private Double budgetCost;

    /**
     * 费用
     */
    private Double fee;

    /**
     * 预算费用
     */
    private Double budgetFee;

    /**
     * 管理费
     */
    private Double manageFee;

    /**
     * 预算管理费
     */
    private Double budgetManageFee;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 预算税金
     */
    private Double budgetTaxes;

    /**
     * 利润
     */
    private Double profit;

    /**
     * 预算利润
     */
    private Double budgetProfit;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getBudgetCost() {
        return budgetCost;
    }

    public void setBudgetCost(Double budgetCost) {
        this.budgetCost = budgetCost;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getBudgetFee() {
        return budgetFee;
    }

    public void setBudgetFee(Double budgetFee) {
        this.budgetFee = budgetFee;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public Double getBudgetManageFee() {
        return budgetManageFee;
    }

    public void setBudgetManageFee(Double budgetManageFee) {
        this.budgetManageFee = budgetManageFee;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getBudgetTaxes() {
        return budgetTaxes;
    }

    public void setBudgetTaxes(Double budgetTaxes) {
        this.budgetTaxes = budgetTaxes;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getBudgetProfit() {
        return budgetProfit;
    }

    public void setBudgetProfit(Double budgetProfit) {
        this.budgetProfit = budgetProfit;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}