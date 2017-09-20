package com.bjike.goddess.assistance.excel;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.organize.enums.StaffStatus;

import java.time.LocalDate;


/**
 * 高温补助导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TemperatureSubsidiesExcel extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 入职日期
     */
    @ExcelHeader(name = "入职日期", notNull = true)
    private LocalDate entryDate;

    /**
     * 计薪开始时间
     */
    @ExcelHeader(name = "计薪开始时间", notNull = true)
    private LocalDate salaryStartDate;

    /**
     * 计薪结束时间
     */
    @ExcelHeader(name = "计薪结束时间", notNull = true)
    private LocalDate salaryEndDate;

    /**
     * 室外工作日期
     */
    @ExcelHeader(name = "室外工作日期", notNull = true)
    private LocalDate outdoorWorkDate;

    /**
     * 天数
     */
    @ExcelHeader(name = "天数", notNull = true)
    private Integer days;

    /**
     * 补助单价/天
     */
    @ExcelHeader(name = "补助单价/天", notNull = true)
    private Double subsidiesPrice;

    /**
     * 补助总额
     */
    @ExcelHeader(name = "补助状态", notNull = true)
    private Double subsidiesAmount;

    /**
     * 是否确认
     */
    @ExcelHeader(name = "是否确认")
    private Boolean confirm;

    /**
     * 确认时间
     */
    @ExcelHeader(name = "确认时间")
    private LocalDate confirmDate;

    /**
     * 补助状态
     */
    @ExcelHeader(name = "补助状态", notNull = true)
    private SubsidiesStatus subsidiesStatus;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private StaffStatus staffStatus;


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

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }
}