package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 住宿补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 11:27 ]
 * @Description: [ 住宿补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HouseAssistTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String empName;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String jobPosition;

    /**
     * 获得补助开始时间
     */
    private String assistStartTime;

    /**
     * 获得补助结束时间
     */
    private String assistEndTime;

    /**
     * 计薪周期开始时间
     */
    @NotBlank(message = "计薪周期开始时间不能为空")
    private String salaryStartTime;

    /**
     * 计薪周期结束时间
     */
    @NotBlank(message = "计薪周期结束时间不能为空")
    private String salaryEndTime;

    /**
     * 外宿天数
     */
    @NotNull(message = "外宿天数不能为空,且是数字")
    private Double outDays;

    /**
     * 住宿补助额度
     */
    private Double money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
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

    public Double getOutDays() {
        return outDays;
    }

    public void setOutDays(Double outDays) {
        this.outDays = outDays;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}