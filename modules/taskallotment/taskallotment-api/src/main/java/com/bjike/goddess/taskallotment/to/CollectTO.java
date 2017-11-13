package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-10 11:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTO extends BaseTO {
    /**
     * 部门
     */
    private String department;
    /**
     * 人
     */
    private String person;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 周
     */
    private Integer week;
    /**
     * 季度
     */
    private Integer quarter;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}
