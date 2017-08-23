package com.bjike.goddess.staffmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.staffmeeting.enums.MeetingPurpose;
import com.bjike.goddess.staffmeeting.enums.MeetingType;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 员工代表大会组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmeeting_organize")
public class MeetingOrganize extends BaseEntity {

    /**
     * 会议组织
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "meetingOrganize")
    private MeetingSummary meetingSummary;

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 议题产生原因
     */
    @Column(name = "topicReason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '议题产生原因'")
    private String topicReason;

    /**
     * 议题目的
     */
    @Column(name = "meetingPurpose", columnDefinition = "TINYINT(2)   COMMENT '议题目的'")
    private MeetingPurpose meetingPurpose;

    /**
     * 层面
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "meetingLay_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '层面'")
    private MeetingLay meetingLay;


    /**
     * 会议内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议内容'")
    private String content;

    /**
     * 计划参会人员
     */
    @Column(name = "planUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会人员'")
    private String[] planUser;

    /**
     * 计划参会时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划参会时间'")
    private LocalDateTime planTime;

    /**
     * 计划会议地点
     */
    @Column(name = "planPlace", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划会议地点'")
    private String planPlace;

    /**
     * 会议形式
     */
    @Column(name = "meetingType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '会议形式'")
    private MeetingType meetingType;

    /**
     * 会议主持人
     */
    @Column(name = "compere", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String compere;

    /**
     * 会议组织人
     */
    @Column(name = "organizer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议组织人'")
    private String organizer;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'")
    private Status status;


    public MeetingSummary getMeetingSummary() {
        return meetingSummary;
    }

    public void setMeetingSummary(MeetingSummary meetingSummary) {
        this.meetingSummary = meetingSummary;
    }

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public MeetingPurpose getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(MeetingPurpose meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public String getTopicReason() {
        return topicReason;
    }

    public void setTopicReason(String topicReason) {
        this.topicReason = topicReason;
    }

    public MeetingLay getMeetingLay() {
        return meetingLay;
    }

    public void setMeetingLay(MeetingLay meetingLay) {
        this.meetingLay = meetingLay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getPlanUser() {
        return planUser;
    }

    public void setPlanUser(String[] planUser) {
        this.planUser = planUser;
    }

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
        this.planTime = planTime;
    }

    public String getPlanPlace() {
        return planPlace;
    }

    public void setPlanPlace(String planPlace) {
        this.planPlace = planPlace;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}