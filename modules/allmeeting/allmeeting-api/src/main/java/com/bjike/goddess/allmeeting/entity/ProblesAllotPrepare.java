package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.allmeeting.enums.ProblemStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 问题分配责任模块议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_problesallotprepare")
public class ProblesAllotPrepare extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 问题来源
     */
    @Column(name = "resource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题来源'")
    private String resource;

    /**
     * 获取时间
     */
    @Column(name = "getTime", nullable = false, columnDefinition = "DATETIME   COMMENT '获取时间'")
    private LocalDateTime getTime;

    /**
     * 描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '描述'")
    private String description;

    /**
     * 问题提出人
     */
    @Column(name = "putForwardUSer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题提出人'")
    private String putForwardUSer;

    /**
     * 是否有解决方案
     */
    @Column(name = "is_solution", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否有解决方案'")
    private Boolean solution;

    /**
     * 优先级
     */
    @Column(name = "priority", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '优先级'")
    private String priority;

    /**
     * 责任模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任模块'")
    private String module;

    /**
     * 责任人
     */
    @Column(name = "responsibleUSer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任人'")
    private String responsibleUSer;

    /**
     * 处理时间
     */
    @Column(name = "dealTime", nullable = false, columnDefinition = "DATETIME   COMMENT '处理时间'")
    private LocalDateTime dealTime;

    /**
     * 问题处理状态
     */
    @Column(name = "problemStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '问题处理状态'")
    private ProblemStatus problemStatus;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDateTime getGetTime() {
        return getTime;
    }

    public void setGetTime(LocalDateTime getTime) {
        this.getTime = getTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPutForwardUSer() {
        return putForwardUSer;
    }

    public void setPutForwardUSer(String putForwardUSer) {
        this.putForwardUSer = putForwardUSer;
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getResponsibleUSer() {
        return responsibleUSer;
    }

    public void setResponsibleUSer(String responsibleUSer) {
        this.responsibleUSer = responsibleUSer;
    }

    public LocalDateTime getDealTime() {
        return dealTime;
    }

    public void setDealTime(LocalDateTime dealTime) {
        this.dealTime = dealTime;
    }

    public ProblemStatus getProblemStatus() {
        return problemStatus;
    }

    public void setProblemStatus(ProblemStatus problemStatus) {
        this.problemStatus = problemStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}