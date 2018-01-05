package com.bjike.goddess.feedback.vo;

/**
 * 问题反馈模块表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemFeedbackVO {

    /**
     * id
     */
    private String id;
    /**
     * 录入人
     */
    private String inputUser;

    /**
     * 问题编号(对外)
     */
    private String problemNum;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 所属项目组/部门
     */
    private String projectGroup;

    /**
     * 问题提出人
     */
    private String problemExhibitor;

    /**
     * 事件(背景)描述
     */
    private String eventDescription;

    /**
     * 问题描述
     */
    private String problemDescription;

    /**
     * 获取时间(问题提出时间)
     */
    private String getTime;

    /**
     * 期望处理时间
     */
    private String expectDealTime;

    /**
     * 问题类型
     */
    private String problemType;

    /**
     * 主功能
     */
    private String mainFunction;

    /**
     * 关联相关模块
     */
    private String correlativeModule;

    /**
     * 问题责任人
     */
    private String problemDutyOfficer;

    /**
     * 问题来源
     */
    private String problemSource;

    /**
     * 是否通报
     */
    private Boolean notification;
    /**
     * 通报途径
     */
    private String notificationWay;
    /**
     * 通报时间
     */
    private String notificationTime;


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

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProblemExhibitor() {
        return problemExhibitor;
    }

    public void setProblemExhibitor(String problemExhibitor) {
        this.problemExhibitor = problemExhibitor;
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

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getMainFunction() {
        return mainFunction;
    }

    public void setMainFunction(String mainFunction) {
        this.mainFunction = mainFunction;
    }

    public String getCorrelativeModule() {
        return correlativeModule;
    }

    public void setCorrelativeModule(String correlativeModule) {
        this.correlativeModule = correlativeModule;
    }

    public String getProblemDutyOfficer() {
        return problemDutyOfficer;
    }

    public void setProblemDutyOfficer(String problemDutyOfficer) {
        this.problemDutyOfficer = problemDutyOfficer;
    }

    public String getProblemSource() {
        return problemSource;
    }

    public void setProblemSource(String problemSource) {
        this.problemSource = problemSource;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getNotificationWay() {
        return notificationWay;
    }

    public void setNotificationWay(String notificationWay) {
        this.notificationWay = notificationWay;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}