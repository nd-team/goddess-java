package com.bjike.goddess.communicatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.communicatemeeting.enums.PlanJobStatus;
import com.bjike.goddess.communicatemeeting.enums.PlanTimeStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 交流会组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 03:31 ]
 * @Description: [ 交流会组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "communicatemeeting_desgin")
public class Desgin extends BaseEntity {

    /**
     * 会议议题
     */
    @Column(name = "meetingTopic", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议议题'")
    private String meetingTopic;

    /**
     * 会议层面
     */
    @Column(name = "meetingLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议层面'")
    private String meetingLevel;

    /**
     * 计划参会岗位
     */
    @Column(name = "planJob", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会岗位'")
    private String planJob;

    /**
     * 议题包含内容
     */
    @Column(name = "topicContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '议题包含内容'")
    private String topicContent;

    /**
     * 计划会议时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划会议时间'")
    private String planTime;

    /**
     * 计划参会岗位状态
     */
    @Column(name = "planJobStatus", columnDefinition = "TINYINT(2) COMMENT '计划参会岗位状态'", nullable = false)
    private PlanJobStatus planJobStatus;

    /**
     * 计划会议时间状态
     */
    @Column(name = "planTimeStatus", columnDefinition = "TINYINT(2) COMMENT '计划会议时间状态'", nullable = false)
    private PlanTimeStatus planTimeStatus;

    /**
     * 会议编号
     */
    @Column(name = "meetingNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNumber;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public PlanJobStatus getPlanJobStatus() {
        return planJobStatus;
    }

    public void setPlanJobStatus(PlanJobStatus planJobStatus) {
        this.planJobStatus = planJobStatus;
    }

    public PlanTimeStatus getPlanTimeStatus() {
        return planTimeStatus;
    }

    public void setPlanTimeStatus(PlanTimeStatus planTimeStatus) {
        this.planTimeStatus = planTimeStatus;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}