package com.bjike.goddess.assistance.vo;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.organize.enums.StaffStatus;

/**
 * 高温补助表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TemperatureSubsidiesVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 入职日期
     */
    private String entryDate;

    /**
     * 计薪开始时间
     */
    private String salaryStartDate;

    /**
     * 计薪结束时间
     */
    private String salaryEndDate;

    /**
     * 室外工作日期
     */
    private String outdoorWorkDate;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 补助单价/天
     */
    private Double subsidiesPrice;

    /**
     * 补助总额
     */
    private Double subsidiesAmount;

    /**
     * 是否确认
     */
    private Boolean confirm;

    /**
     * 确认时间
     */
    private String confirmDate;

    /**
     * 补助状态
     */
    private SubsidiesStatus subsidiesStatus;

    /**
     *
     */
    private StaffStatus staffStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getSalaryStartDate() {
        return salaryStartDate;
    }

    public void setSalaryStartDate(String salaryStartDate) {
        this.salaryStartDate = salaryStartDate;
    }

    public String getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(String salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }

    public String getOutdoorWorkDate() {
        return outdoorWorkDate;
    }

    public void setOutdoorWorkDate(String outdoorWorkDate) {
        this.outdoorWorkDate = outdoorWorkDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getSubsidiesPrice() {
        return subsidiesPrice;
    }

    public void setSubsidiesPrice(Double subsidiesPrice) {
        this.subsidiesPrice = subsidiesPrice;
    }

    public Double getSubsidiesAmount() {
        return subsidiesAmount;
    }

    public void setSubsidiesAmount(Double subsidiesAmount) {
        this.subsidiesAmount = subsidiesAmount;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public SubsidiesStatus getSubsidiesStatus() {
        return subsidiesStatus;
    }

    public void setSubsidiesStatus(SubsidiesStatus subsidiesStatus) {
        this.subsidiesStatus = subsidiesStatus;
    }

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }
}