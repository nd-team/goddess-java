package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.allmeeting.enums.MeetingType;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 所有工作内容汇总会议组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_organize")
public class AllMeetingOrganize extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 层面Id
     */
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "meetingLay_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '层面'")
    private MeetingLay meetingLay;

    /**
     * 会议内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议内容'")
    private String content;

    /**
     * 关联功能
     */
    @Column(name = "relation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '关联功能'")
    private String relation;

    /**
     * 计划参会人员
     */
    @Column(name = "planUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会人员'")
    private String planUser;

    /**
     * 计划会议时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划会议时间'")
    private LocalDateTime planTime;

    /**
     * 会议形式
     */
    @Column(name = "meetingType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议形式'")
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
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPlanUser() {
        return planUser;
    }

    public void setPlanUser(String planUser) {
        this.planUser = planUser;
    }

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
        this.planTime = planTime;
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