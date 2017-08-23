package com.bjike.goddess.assistance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 公司员工补助信息记录业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistanceRecordBO extends BaseBO {

    /**
     * 员工名称
     */
    private String empName;

    /**
     * 员工编号
     */
    private String empNumber;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String job;

    /**
     * 入职时间
     */
    private String entryJobTime;

    /**
     * 补助方案序号
     */
    private String planNumber;

    /**
     * 补助类型名称
     */
    private String assistName;

    /**
     * 获得补助的开始时间
     */
    private String assistStartTime;

    /**
     * 获得补助的结束时间
     */
    private String assistEndTime;

    /**
     * 计薪周期开始时间
     */
    private String salaryStartTime;

    /**
     * 计薪周期结束时间
     */
    private String salaryEndTime;

    /**
     * 获得补助的天数
     */
    private Double assistDays;

    /**
     * 补助内容
     */
    private String assistContent;

    /**
     * 内容明细
     */
    private String contentDetail;

    /**
     * 补助形式
     */
    private String assistForm;

    /**
     * 补助发放时间
     */
    private String assistGiveTime;

    /**
     * 本人是否已领补助(是/否)
     */
    private String recieveCondition;

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


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEntryJobTime() {
        return entryJobTime;
    }

    public void setEntryJobTime(String entryJobTime) {
        this.entryJobTime = entryJobTime;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getAssistName() {
        return assistName;
    }

    public void setAssistName(String assistName) {
        this.assistName = assistName;
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

    public String getAssistContent() {
        return assistContent;
    }

    public void setAssistContent(String assistContent) {
        this.assistContent = assistContent;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getAssistForm() {
        return assistForm;
    }

    public void setAssistForm(String assistForm) {
        this.assistForm = assistForm;
    }

    public String getAssistGiveTime() {
        return assistGiveTime;
    }

    public void setAssistGiveTime(String assistGiveTime) {
        this.assistGiveTime = assistGiveTime;
    }

    public String getRecieveCondition() {
        return recieveCondition;
    }

    public void setRecieveCondition(String recieveCondition) {
        this.recieveCondition = recieveCondition;
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