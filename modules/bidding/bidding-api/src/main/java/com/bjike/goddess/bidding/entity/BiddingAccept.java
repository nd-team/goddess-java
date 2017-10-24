package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 招标问题受理和处理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_biddingaccept")
public class BiddingAccept extends BaseEntity {

    /**
     * 录入人
     */
    @Column(name = "inputUser",  columnDefinition = "VARCHAR(255)   COMMENT '录入人'")
    private String inputUser;

    /**
     * 受理问题编号(对外)
     */
    @Column(name = "problemNum", columnDefinition = "VARCHAR(255)   COMMENT '受理问题编号(对外)'")
    private String problemNum;

    /**
     * 所属地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '所属地区'")
    private String area;

    /**
     * 所属项目组/部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '所属项目组/部门'")
    private String department;

    /**
     * 问题提出人
     */
    @Column(name = "problemExhibitor",  columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String problemExhibitor;

    /**
     * 问题类型
     */
    @Column(name = "problemType",  columnDefinition = "VARCHAR(255)   COMMENT '问题类型'")
    private String problemType;

    /**
     * 问题发现形式
     */
    @Column(name = "problemDiscoveryForm",  columnDefinition = "VARCHAR(255)   COMMENT '问题发现形式'")
    private String problemDiscoveryForm;

    /**
     * 时间(背景)描述
     */
    @Column(name = "eventDescription",  columnDefinition = "VARCHAR(255)   COMMENT '时间(背景)描述'")
    private String eventDescription;

    /**
     * 问题描述
     */
    @Column(name = "problemDescription",  columnDefinition = "VARCHAR(255)   COMMENT '问题描述'")
    private String problemDescription;

    /**
     * 是否通报
     */
    @Column(name = "is_notification",  columnDefinition = "TINYINT(1)  COMMENT '是否通报'")
    private Boolean notification;

    /**
     * 获取时间(问题提出时间)
     */
    @Column(name = "getTime",  columnDefinition = "DATE   COMMENT '获取时间(问题提出时间)'")
    private LocalDate getTime;

    /**
     * 期望处理时间
     */
    @Column(name = "expectDealTime",  columnDefinition = "DATE   COMMENT '期望处理时间'")
    private LocalDate expectDealTime;

    /**
     * 协助部门
     */
    @Column(name = "assistDept", columnDefinition = "VARCHAR(255)   COMMENT '协助部门'")
    private String assistDept;

    /**
     * 处理协商时间
     */
    @Column(name = "processTime",  columnDefinition = "DATE   COMMENT '处理协商时间'")
    private LocalDate processTime;

    /**
     * 状态
     */
    @Column(name = "status",  columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;

    /**
     * 是否闭环
     */
    @Column(name = "is_closedLoop",  columnDefinition = "TINYINT(1)   COMMENT '是否闭环'")
    private Boolean closedLoop;


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

    public LocalDate getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDate getTime) {
        this.getTime = getTime;
    }

    public LocalDate getExpectDealTime() {
        return expectDealTime;
    }

    public void setExpectDealTime(LocalDate expectDealTime) {
        this.expectDealTime = expectDealTime;
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept;
    }

    public LocalDate getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDate processTime) {
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