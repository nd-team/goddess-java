package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 简洁交流讨论纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_concisesummary")
public class ConciseSummary extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 实际会议时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际会议时间'")
    private LocalDateTime actualTime;

    /**
     * 会议地点
     */
    @Column(name = "place", columnDefinition = "VARCHAR(255)   COMMENT '会议地点'")
    private String place;

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
     * 会议主持人
     */
    @Column(name = "compere", columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String compere;

    /**
     * 参会人数
     */
    @Column(name = "attendAccount", columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer attendAccount;

    /**
     * 会议所属模块
     */
    @Column(name = "module", columnDefinition = "VARCHAR(255)   COMMENT '会议所属模块'")
    private String module;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Integer getAttendAccount() {
        return attendAccount;
    }

    public void setAttendAccount(Integer attendAccount) {
        this.attendAccount = attendAccount;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}