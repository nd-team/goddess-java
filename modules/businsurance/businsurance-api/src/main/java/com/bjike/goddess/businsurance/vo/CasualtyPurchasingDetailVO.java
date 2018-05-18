package com.bjike.goddess.businsurance.vo;

import com.bjike.goddess.businsurance.enums.BuyCasualtyStatus;

/**
 * 团体意外险购买详情表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CasualtyPurchasingDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 被保人姓名
     */
    private String beApplicantName;

    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组部门
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 入职日期
     */
    private String entryDate;

    /**
     * 离职日期
     */
    private String departureDate;

    /**
     * 是否购买社保
     */
    private Boolean buySocialSecurity;

    /**
     * 停止购买时间
     */
    private String stopBuyTime;

    /**
     * 购买意外险状态
     */
    private BuyCasualtyStatus buyCasualtyStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeApplicantName() {
        return beApplicantName;
    }

    public void setBeApplicantName(String beApplicantName) {
        this.beApplicantName = beApplicantName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Boolean getBuySocialSecurity() {
        return buySocialSecurity;
    }

    public void setBuySocialSecurity(Boolean buySocialSecurity) {
        this.buySocialSecurity = buySocialSecurity;
    }

    public String getStopBuyTime() {
        return stopBuyTime;
    }

    public void setStopBuyTime(String stopBuyTime) {
        this.stopBuyTime = stopBuyTime;
    }

    public BuyCasualtyStatus getBuyCasualtyStatus() {
        return buyCasualtyStatus;
    }

    public void setBuyCasualtyStatus(BuyCasualtyStatus buyCasualtyStatus) {
        this.buyCasualtyStatus = buyCasualtyStatus;
    }
}