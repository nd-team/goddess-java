package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同立项情况金额汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-23 11:36 ]
 * @Description: [ 合同立项情况金额汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MakeCaseFigureBO extends BaseBO {


    /**
     * 地区
     */
    private String area;
    /**
     * 所属项目组
     */
    private String projectGroup;
    /**
     * 专业
     */
    private String major;
    /**
     * 总包单位名称
     */
    private String majorCompany;
    /**
     * 年月份
     */
    private String yearMonth;
    /**
     * 已派工金额
     */
    private Double taskMoney;
    /**
     * 立项总金额
     */
    private Double makeMoney;
    /**
     * 预估总金额
     */
    private Double forecastMoney;

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
}