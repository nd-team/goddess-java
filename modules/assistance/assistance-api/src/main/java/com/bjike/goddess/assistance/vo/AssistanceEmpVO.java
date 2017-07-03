package com.bjike.goddess.assistance.vo;

/**
 * 补助员工名单表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistanceEmpVO {

    /**
     * id
     */
    private String id;
    /**
     * 补助方案序号
     */
    private String planNumber;

    /**
     * 补助类型名称
     */
    private String assistName;

    /**
     * 补助内容
     */
    private String assistContent;

    /**
     * 内容明细
     */
    private String contentDetail;

    /**
     * 获得补助的开始时间
     */
    private String recieveStartTime;

    /**
     * 获得补助的结束时间
     */
    private String recieveEndTime;

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
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRecieveStartTime() {
        return recieveStartTime;
    }

    public void setRecieveStartTime(String recieveStartTime) {
        this.recieveStartTime = recieveStartTime;
    }

    public String getRecieveEndTime() {
        return recieveEndTime;
    }

    public void setRecieveEndTime(String recieveEndTime) {
        this.recieveEndTime = recieveEndTime;
    }

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