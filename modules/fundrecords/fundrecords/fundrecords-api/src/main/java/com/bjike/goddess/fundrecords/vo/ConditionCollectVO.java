package com.bjike.goddess.fundrecords.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 条件汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ConditionCollectVO extends BaseBO {

    /**
     * 时间
     */
    private String recordDate;

    /**
     * 收入
     */
    private Double income;

    /**
     * 支出
     */
    private Double expenditure;
    /**
     * 本月发生额
     */
    private Double incurredAmount;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目组
     */
    private String projectGroup;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
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

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getIncurredAmount() {
        return incurredAmount;
    }

    public void setIncurredAmount(Double incurredAmount) {
        this.incurredAmount = incurredAmount;
    }
}