package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 电脑补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:20 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerAssistTO extends BaseTO {

    /**
     * 员工名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "员工名称不能为空")
    private String empName;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "项目组不能为空")
    private String projectGroup;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "地区不能为空")
    private String area;

    /**
     * 电脑补助开始时间
     */
    private String assistStartTime;

    /**
     * 电脑补助结束时间
     */
    private String assistEndTime;

    /**
     * 补助计薪周期开始时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "补助计薪周期开始时间不能为空")
    private String salaryStartTime;

    /**
     * 补助计薪周期结束时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "补助计薪周期结束时间不能为空")
    private String salaryEndTime;

    /**
     * 补助天数
     */
    private Double assistDays;



    /**
     * 备注
     */
    private String remark;



    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAssistStartTime() {
        return assistStartTime;
    }

    public void setAssistStartTime(String assistStartTime) {
        this.assistStartTime = assistStartTime;
    }

    public String getAssistEndTime() {
        return assistEndTime;
    }

    public void setAssistEndTime(String assistEndTime) {
        this.assistEndTime = assistEndTime;
    }

    public String getSalaryStartTime() {
        return salaryStartTime;
    }

    public void setSalaryStartTime(String salaryStartTime) {
        this.salaryStartTime = salaryStartTime;
    }

    public String getSalaryEndTime() {
        return salaryEndTime;
    }

    public void setSalaryEndTime(String salaryEndTime) {
        this.salaryEndTime = salaryEndTime;
    }

    public Double getAssistDays() {
        return assistDays;
    }

    public void setAssistDays(Double assistDays) {
        this.assistDays = assistDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}