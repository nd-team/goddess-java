package com.bjike.goddess.negotiatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 协商会议组织内容
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:30 ]
 * @Description: [ 协商会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "negotiatemeeting_organization")
public class Organization extends BaseEntity {

    /**
     * 会议类型
     */
    @Column(name = "meetingType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议类型'")
    private String meetingType;

    /**
     * 会议层面
     */
    @Column(name = "meetingLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议层面'")
    private String meetingLevel;

    /**
     * 会议议题
     */
    @Column(name = "meetingTopic", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议议题'")
    private String meetingTopic;

    /**
     * 议题包含内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '议题包含内容'")
    private String content;

    /**
     * 议题产生原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '议题产生原因'")
    private String reason;

    /**
     * 议题目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '议题目的'")
    private String purpose;

    /**
     * 计划参会岗位
     */
    @Column(name = "planJob", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会岗位'")
    private String planJob;

    /**
     * 计划参会人员
     */
    @Column(name = "planAttend", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会人员'")
    private String planAttend;

    /**
     * 计划会议时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划会议时间'")
    private LocalDateTime planTime;

    /**
     * 会议形式
     */
    @Column(name = "meetingFormat", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议形式'")
    private String meetingFormat;

    /**
     * 会议地点
     */
    @Column(name = "meetingArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议地点'")
    private String meetingArea;

    /**
     * 会议主持人
     */
    @Column(name = "host", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String host;

    /**
     * 会议组织人
     */
    @Column(name = "organization", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议组织人'")
    private String organization;

    /**
     * 会议编号
     */
    @Column(name = "meetingNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNumber;


    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getPlanAttend() {
        return planAttend;
    }

    public void setPlanAttend(String planAttend) {
        this.planAttend = planAttend;
    }

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
        this.planTime = planTime;
    }

    public String getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(String meetingFormat) {
        this.meetingFormat = meetingFormat;
    }

    public String getMeetingArea() {
        return meetingArea;
    }

    public void setMeetingArea(String meetingArea) {
        this.meetingArea = meetingArea;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }
}