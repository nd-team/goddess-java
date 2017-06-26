package com.bjike.goddess.staffmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.staffmeeting.enums.MeetingPurpose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 员工代表大会纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmeeting_summary")
public class MeetingSummary extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 实际参会人员
     */
    @Column(name = "actualUsers", columnDefinition = "VARCHAR(255)   COMMENT '实际参会人员'")
    private String actualUsers;

    /**
     * 新增参会人员
     */
    @Column(name = "addUsers", columnDefinition = "VARCHAR(255)   COMMENT '新增参会人员'")
    private String addUsers;

    /**
     * 未参会人员
     */
    @Column(name = "notAttendUsers", columnDefinition = "VARCHAR(255)   COMMENT '未参会人员'")
    private String notAttendUsers;

    /**
     * 会议目的
     */
    @Column(name = "meetingPurpose", columnDefinition = "TINYINT(2)   COMMENT '会议目的'")
    private MeetingPurpose meetingPurpose;

    /**
     * 参会人数
     */
    @Column(name = "attendAccount", columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer attendAccount;

    /**
     * 实际参会时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际参会时间'")
    private LocalDateTime actualTime;

    /**
     * 实际会议地点
     */
    @Column(name = "actualPlace", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实际会议地点'")
    private String actualPlace;

    /**
     * 最终监督结果
     */
    @Column(name = "superviseResult", columnDefinition = "TEXT   COMMENT '最终监督结果'")
    private String superviseResult;

    /**
     * 最终表决结果
     */
    @Column(name = "voteResult", columnDefinition = "TEXT   COMMENT '最终表决结果'")
    private String voteResult;

    /**
     * 会议记录人
     */
    @Column(name = "recordUser", columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recordUser;

    /**
     * 会议主持人
     */
    @Column(name = "compere", columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String compere;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'")
    private Status status;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getActualUsers() {
        return actualUsers;
    }

    public void setActualUsers(String actualUsers) {
        this.actualUsers = actualUsers;
    }

    public String getAddUsers() {
        return addUsers;
    }

    public void setAddUsers(String addUsers) {
        this.addUsers = addUsers;
    }

    public String getNotAttendUsers() {
        return notAttendUsers;
    }

    public void setNotAttendUsers(String notAttendUsers) {
        this.notAttendUsers = notAttendUsers;
    }

    public MeetingPurpose getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(MeetingPurpose meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public Integer getAttendAccount() {
        return attendAccount;
    }

    public void setAttendAccount(Integer attendAccount) {
        this.attendAccount = attendAccount;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPlace() {
        return actualPlace;
    }

    public void setActualPlace(String actualPlace) {
        this.actualPlace = actualPlace;
    }

    public String getSuperviseResult() {
        return superviseResult;
    }

    public void setSuperviseResult(String superviseResult) {
        this.superviseResult = superviseResult;
    }

    public String getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(String voteResult) {
        this.voteResult = voteResult;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}