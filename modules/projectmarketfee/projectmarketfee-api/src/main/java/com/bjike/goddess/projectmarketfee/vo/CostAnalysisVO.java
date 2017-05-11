package com.bjike.goddess.projectmarketfee.vo;

/**
 * 费用效益分析表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostAnalysisVO {

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
    private Double expectedIncome;

    /**
     * 预计市场费
     */
    private Double expectedMarketCost;


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

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Double getExpectedMarketCost() {
        return expectedMarketCost;
    }

    public void setExpectedMarketCost(Double expectedMarketCost) {
        this.expectedMarketCost = expectedMarketCost;
    }
}