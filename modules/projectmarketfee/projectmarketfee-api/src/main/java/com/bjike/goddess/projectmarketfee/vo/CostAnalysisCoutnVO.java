package com.bjike.goddess.projectmarketfee.vo;

/**
 * @Author: [yewenbo]
 * @Date: [2017-05-09 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CostAnalysisCoutnVO {
    /**
     * id
     */
    private String id;
    /**
     * 项目组
     */
    private String project;
    /**
     * 地区
     */
    private String arrival;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 预计收入
     */
    private Double expectedIncomeSum;
    /**
     * 预计市场费
     */
    private Double expectedMarketCostSum;
    /**
     * 实际市场费
     */
    private Double actualMarketCostSum;
    /**
     * 差额
     */
    private Double differences;
    /**
     * 预计占比
     */
    private Double expectedScale;
    /**
     * 实际占比
     */
    private Double actualScale;
    /**
     * 预警
     */
    private String warn;
    /**
     * 等级评定
     */
    private String grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getExpectedIncomeSum() {
        return expectedIncomeSum;
    }

    public void setExpectedIncomeSum(Double expectedIncomeSum) {
        this.expectedIncomeSum = expectedIncomeSum;
    }

    public Double getExpectedMarketCostSum() {
        return expectedMarketCostSum;
    }

    public void setExpectedMarketCostSum(Double expectedMarketCostSum) {
        this.expectedMarketCostSum = expectedMarketCostSum;
    }

    public Double getActualMarketCostSum() {
        return actualMarketCostSum;
    }

    public void setActualMarketCostSum(Double actualMarketCostSum) {
        this.actualMarketCostSum = actualMarketCostSum;
    }

    public Double getDifferences() {
        return differences;
    }

    public void setDifferences(Double differences) {
        this.differences = differences;
    }

    public Double getExpectedScale() {
        return expectedScale;
    }

    public void setExpectedScale(Double expectedScale) {
        this.expectedScale = expectedScale;
    }

    public Double getActualScale() {
        return actualScale;
    }

    public void setActualScale(Double actualScale) {
        this.actualScale = actualScale;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
