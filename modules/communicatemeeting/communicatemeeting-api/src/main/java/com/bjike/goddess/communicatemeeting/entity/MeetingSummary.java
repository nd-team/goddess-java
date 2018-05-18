package com.bjike.goddess.communicatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 交流会纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "communicatemeeting_meetingsummary")
public class MeetingSummary extends BaseEntity {

    /**
     * 实际会议时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际会议时间'")
    private LocalDateTime actualTime;

    /**
     * 实际参会人员
     */
    @Column(name = "actualPeople", columnDefinition = "VARCHAR(255)   COMMENT '实际参会人员'")
    private String actualPeople;

    /**
     * 新增参会人员
     */
    @Column(name = "addPeople", columnDefinition = "VARCHAR(255)   COMMENT '新增参会人员'")
    private String addPeople;

    /**
     * 未参会人员
     */
    @Column(name = "notAttend", columnDefinition = "VARCHAR(255)   COMMENT '未参会人员'")
    private String notAttend;

    /**
     * 参会人数
     */
    @Column(name = "amount", columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer amount;

    /**
     * 发言部门
     */
    @Column(name = "speakDepartment", columnDefinition = "VARCHAR(255)   COMMENT '发言部门'")
    private String speakDepartment;

    /**
     * 发言岗位
     */
    @Column(name = "speakJob", columnDefinition = "VARCHAR(255)   COMMENT '发言岗位'")
    private String speakJob;

    /**
     * 发言人
     */
    @Column(name = "speaker", columnDefinition = "VARCHAR(255)   COMMENT '发言人'")
    private String speaker;

    /**
     * 一轮交流内容
     */
    @Column(name = "oneRound", columnDefinition = "VARCHAR(255)   COMMENT '一轮交流内容'")
    private String oneRound;

    /**
     * 二轮交流内容
     */
    @Column(name = "twoRound", columnDefinition = "VARCHAR(255)   COMMENT '二轮交流内容'")
    private String twoRound;

    /**
     * 最终结论
     */
    @Column(name = "result", columnDefinition = "VARCHAR(255)   COMMENT '最终结论'")
    private String result;

    /**
     * 会议记录人
     */
    @Column(name = "recorder",columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recorder;

    /**
     * 交流会组织内容信息
     */
    @Column(name = "organizeContent_id", nullable = false, unique = true, columnDefinition = "VARCHAR(36)   COMMENT '交流会组织内容信息'")
    private String organizeContentId;

    public String getOrganizeContentId() {
        return organizeContentId;
    }

    public void setOrganizeContentId(String organizeContentId) {
        this.organizeContentId = organizeContentId;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPeople() {
        return actualPeople;
    }

    public void setActualPeople(String actualPeople) {
        this.actualPeople = actualPeople;
    }

    public String getAddPeople() {
        return addPeople;
    }

    public void setAddPeople(String addPeople) {
        this.addPeople = addPeople;
    }

    public String getNotAttend() {
        return notAttend;
    }

    public void setNotAttend(String notAttend) {
        this.notAttend = notAttend;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSpeakDepartment() {
        return speakDepartment;
    }

    public void setSpeakDepartment(String speakDepartment) {
        this.speakDepartment = speakDepartment;
    }

    public String getSpeakJob() {
        return speakJob;
    }

    public void setSpeakJob(String speakJob) {
        this.speakJob = speakJob;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getOneRound() {
        return oneRound;
    }

    public void setOneRound(String oneRound) {
        this.oneRound = oneRound;
    }

    public String getTwoRound() {
        return twoRound;
    }

    public void setTwoRound(String twoRound) {
        this.twoRound = twoRound;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }
}