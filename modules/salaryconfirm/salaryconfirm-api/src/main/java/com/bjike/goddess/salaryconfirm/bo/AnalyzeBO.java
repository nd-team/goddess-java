package com.bjike.goddess.salaryconfirm.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 分析对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 分析对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalyzeBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 本月实发工资
     */
    private Double salary;

    /**
     * 上月工资
     */
    private Double lastSalary;

    /**
     * 差额
     */
    private Double balance;

    /**
     * 增长率
     */
    private String growUpRate;

    /**
     * 百分比
     */
    private String percent;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getLastSalary() {
        return lastSalary;
    }

    public void setLastSalary(Double lastSalary) {
        this.lastSalary = lastSalary;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getGrowUpRate() {
        return growUpRate;
    }

    public void setGrowUpRate(String growUpRate) {
        this.growUpRate = growUpRate;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}