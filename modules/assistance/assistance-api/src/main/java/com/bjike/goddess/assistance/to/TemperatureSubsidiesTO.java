package com.bjike.goddess.assistance.to;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.enums.StaffStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 高温补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TemperatureSubsidiesTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 入职日期
     */
    @NotBlank(message = "入职日期不能为空",groups = {ADD.class, EDIT.class})
    private String entryDate;

    /**
     * 计薪开始时间
     */
    @NotBlank(message = "计薪开始时间不能为空",groups = {ADD.class, EDIT.class})
    private String salaryStartDate;

    /**
     * 计薪结束时间
     */
    @NotBlank(message = "计薪结束时间不能为空",groups = {ADD.class, EDIT.class})
    private String salaryEndDate;

    /**
     * 室外工作日期
     */
    @NotBlank(message = "室外工作日期不能为空",groups = {ADD.class, EDIT.class})
    private String outdoorWorkDate;

    /**
     * 天数
     */
    @NotNull(message = "天数不能为空",groups = {ADD.class, EDIT.class})
    private Integer days;

    /**
     * 补助单价/天
     */
    @NotNull(message = "补助单价不能为空",groups = {ADD.class, EDIT.class})
    private Double subsidiesPrice;

    /**
     * 补助状态
     */
    @NotNull(message = "补助状态不能为空",groups = {ADD.class, EDIT.class})
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

    public SubsidiesStatus getSubsidiesStatus() {
        return subsidiesStatus;
    }

    public void setSubsidiesStatus(SubsidiesStatus subsidiesStatus) {
        this.subsidiesStatus = subsidiesStatus;
    }
}