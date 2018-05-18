package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 高温补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_temperaturesubsidies")
public class TemperatureSubsidies extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 入职日期
     */
    @Column(name = "entryDate", nullable = false, columnDefinition = "DATE   COMMENT '入职日期'")
    private LocalDate entryDate;

    /**
     * 计薪开始时间
     */
    @Column(name = "salaryStartDate", nullable = false, columnDefinition = "DATE   COMMENT '计薪开始时间'")
    private LocalDate salaryStartDate;

    /**
     * 计薪结束时间
     */
    @Column(name = "salaryEndDate", nullable = false, columnDefinition = "DATE   COMMENT '计薪结束时间'")
    private LocalDate salaryEndDate;

    /**
     * 室外工作日期
     */
    @Column(name = "outdoorWorkDate", nullable = false, columnDefinition = "DATE   COMMENT '室外工作日期'")
    private LocalDate outdoorWorkDate;

    /**
     * 天数
     */
    @Column(name = "days", nullable = false, columnDefinition = "INT(5)   COMMENT '天数'")
    private Integer days;

    /**
     * 补助单价/天
     */
    @Column(name = "subsidiesPrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补助单价/天'")
    private Double subsidiesPrice;

    /**
     * 补助总额
     */
    @Column(name = "subsidiesAmount", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补助总额'")
    private Double subsidiesAmount;

    /**
     * 是否确认
     */
    @Column(name = "is_confirm", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否确认'", insertable = false)
    private Boolean confirm;

    /**
     * 确认时间
     */
    @Column(name = "confirmDate",  columnDefinition = "DATE   COMMENT '确认时间'")
    private LocalDate confirmDate;

    /**
     * 补助状态
     */
    @Column(name = "subsidiesStatus", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助状态'")
    private SubsidiesStatus subsidiesStatus;


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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getSalaryStartDate() {
        return salaryStartDate;
    }

    public void setSalaryStartDate(LocalDate salaryStartDate) {
        this.salaryStartDate = salaryStartDate;
    }

    public LocalDate getSalaryEndDate() {
        return salaryEndDate;
    }

    public void setSalaryEndDate(LocalDate salaryEndDate) {
        this.salaryEndDate = salaryEndDate;
    }

    public LocalDate getOutdoorWorkDate() {
        return outdoorWorkDate;
    }

    public void setOutdoorWorkDate(LocalDate outdoorWorkDate) {
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

    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
        this.confirmDate = confirmDate;
    }

    public SubsidiesStatus getSubsidiesStatus() {
        return subsidiesStatus;
    }

    public void setSubsidiesStatus(SubsidiesStatus subsidiesStatus) {
        this.subsidiesStatus = subsidiesStatus;
    }

}