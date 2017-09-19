package com.bjike.goddess.bidding.vo;

/**
 * 招标问题受理和处理表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAcceptVO {

    /**
     * id
     */
    private String id;
    /**
     * 录入人
     */
    private String inputUser;

    /**
     * 受理问题编号(对外)
     */
    private String problemNum;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String department;

    /**
     * 问题提出人
     */
    private String problemExhibitor;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 问题发现形式
     */
    private String problemDiscoveryForm;

    /**
     * 时间(背景)描述
     */
    private String eventDescription;

    /**
     * 问题描述
     */
    private String problemDescription;

    /**
     * 是否通报
     */
    private Boolean notification;

    /**
     * 获取时间(问题提出时间)
     */
    private String getTime;

    /**
     * 期望处理时间
     */
    private String expectDealTime;

    /**
     * 协助部门
     */
    private String assistDept;

    /**
     * 处理协商时间
     */
    private String processTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否闭环
     */
    private Boolean closedLoop;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(String problemNum) {
        this.problemNum = problemNum;
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

    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDiscoveryForm() {
        return problemDiscoveryForm;
    }

    public void setProblemDiscoveryForm(String problemDiscoveryForm) {
        this.problemDiscoveryForm = problemDiscoveryForm;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(String expectDealTime) {
        this.expectDealTime = expectDealTime;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }
}