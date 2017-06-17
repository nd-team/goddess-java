package com.bjike.goddess.projectmeasure.vo;


/**
 * 输出评估结果业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 输出评估结果业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectEvaluateResultVO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 工作界面
     */
    private String workInterface;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 回款时间
     */
    private String backDate;

    /**
     * 工期
     */
    private String timeLimit;

    /**
     * 人工
     */
    private Integer labour;

    /**
     * 耗时费用
     */
    private Double consumptionCosts;

    /**
     * 成本
     */
    private Integer totalCost;

    /**
     * 利润
     */
    private Double profit;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkInterface() {
        return workInterface;
    }

    public void setWorkInterface(String workInterface) {
        this.workInterface = workInterface;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getLabour() {
        return labour;
    }

    public void setLabour(Integer labour) {
        this.labour = labour;
    }

    public Double getConsumptionCosts() {
        return consumptionCosts;
    }

    public void setConsumptionCosts(Double consumptionCosts) {
        this.consumptionCosts = consumptionCosts;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}