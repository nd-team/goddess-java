package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 问题反馈模块
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-01 10:38 ]
 * @Description: [ 问题反馈模块 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_problemfeedback")
public class ProblemFeedback extends BaseEntity {

    /**
     * 录入人
     */
    @Column(name = "inputUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '录入人'")
    private String inputUser;

    /**
     * 问题编号(对外)
     */
    @Column(name = "problemNum", columnDefinition = "VARCHAR(255)   COMMENT '问题编号(对外)'")
    private String problemNum;

    /**
     * 所属地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目组/部门
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '所属项目组/部门'")
    private String projectGroup;

    /**
     * 问题提出人
     */
    @Column(name = "problemExhibitor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String problemExhibitor;

    /**
     * 事件(背景)描述
     */
    @Column(name = "eventDescription", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '事件(背景)描述'")
    private String eventDescription;

    /**
     * 问题描述
     */
    @Column(name = "problemDescription", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String problemDescription;

    /**
     * 获取时间(问题提出时间)
     */
    @Column(name = "getTime", nullable = false, columnDefinition = "DATETIME   COMMENT '获取时间(问题提出时间)'")
    private LocalDateTime getTime;

    /**
     * 期望处理时间
     */
    @Column(name = "expectDealTime", nullable = false, columnDefinition = "DATETIME   COMMENT '期望处理时间'")
    private LocalDateTime expectDealTime;

    /**
     * 问题类型
     */
    @Column(name = "problemType",  columnDefinition = "VARCHAR(255)   COMMENT '问题类型'")
    private String problemType;

    /**
     * 主功能
     */
    @Column(name = "mainFunction",  columnDefinition = "VARCHAR(255)   COMMENT '主功能'")
    private String mainFunction;

    /**
     * 关联相关模块
     */
    @Column(name = "correlativeModule",  columnDefinition = "VARCHAR(255)   COMMENT '关联相关模块'")
    private String correlativeModule;

    /**
     * 问题责任人
     */
    @Column(name = "problemDutyOfficer",  columnDefinition = "VARCHAR(255)   COMMENT '问题责任人'")
    private String problemDutyOfficer;

    /**
     * 问题来源
     */
    @Column(name = "problemSource",  columnDefinition = "VARCHAR(255)   COMMENT '问题来源'")
    private String problemSource;

    /**
     * 是否通报
     */
    @Column(name = "is_notification", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否通报'")
    private Boolean notification;
    /**
     * 通报途径
     */
    @Column(name = "notificationWay",  columnDefinition = "VARCHAR(255)   COMMENT '通报途径'")
    private String notificationWay;
    /**
     * 通报时间
     */
    @Column(name = "notificationTime", columnDefinition = "DATETIME   COMMENT '通报时间'")
    private LocalDateTime notificationTime;


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

    public LocalDateTime getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDateTime getTime) {
        this.getTime = getTime;
    }

    public LocalDateTime getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(LocalDateTime expectDealTime) {
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

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }
}