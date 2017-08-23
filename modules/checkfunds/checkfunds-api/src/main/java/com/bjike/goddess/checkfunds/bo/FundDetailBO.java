package com.bjike.goddess.checkfunds.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资金流水明细
 * @Author: [chenjunhao]
 * @Date: [2017-06-12 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FundDetailBO extends BaseBO{
    /**
     * 时间
     */
    private String recordDate;

    /**
     * 借方
     */
    private Double income;

    /**
     * 贷方
     */
    private Double expenditure;

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
}
