package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.businsurance.enums.BuyCasualtyStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 团体意外险购买详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_casualtypurchasingdetail")
public class CasualtyPurchasingDetail extends BaseEntity {

    /**
     * 被保人姓名
     */
    @Column(name = "beApplicantName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '被保人姓名'")
    private String beApplicantName;
    /**
     * 员工编号
     */
    @Column(name = "employeeNo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNo;
    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "jobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 入职日期
     */
    @Column(name = "entryDate", nullable = false, columnDefinition = "DATE   COMMENT '入职日期'")
    private LocalDate entryDate;
    /**
     * 离职日期
     */
    @Column(name = "departureDate", columnDefinition = "DATE   COMMENT '离职日期'")
    private LocalDate departureDate;

    /**
     * 是否购买社保
     */
    @Column(name = "is_buySocialSecurity", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否购买社保'", insertable = false)
    private Boolean buySocialSecurity;

    /**
     * 停止购买时间
     */
    @Column(name = "stopBuyTime", nullable = false, columnDefinition = "DATE   COMMENT '停止购买时间'")
    private LocalDate stopBuyTime;

    /**
     * 购买意外险状态
     */
    @Column(name = "buyCasualtyStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '购买意外险状态'")
    private BuyCasualtyStatus buyCasualtyStatus;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Boolean getBuySocialSecurity() {
        return buySocialSecurity;
    }

    public void setBuySocialSecurity(Boolean buySocialSecurity) {
        this.buySocialSecurity = buySocialSecurity;
    }

    public LocalDate getStopBuyTime() {
        return stopBuyTime;
    }

    public void setStopBuyTime(LocalDate stopBuyTime) {
        this.stopBuyTime = stopBuyTime;
    }

    public BuyCasualtyStatus getBuyCasualtyStatus() {
        return buyCasualtyStatus;
    }

    public void setBuyCasualtyStatus(BuyCasualtyStatus buyCasualtyStatus) {
        this.buyCasualtyStatus = buyCasualtyStatus;
    }

    public String getBeApplicantName() {
        return beApplicantName;
    }

    public void setBeApplicantName(String beApplicantName) {
        this.beApplicantName = beApplicantName;
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}