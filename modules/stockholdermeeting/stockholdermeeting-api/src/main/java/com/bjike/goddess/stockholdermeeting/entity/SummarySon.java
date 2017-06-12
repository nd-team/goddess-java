package com.bjike.goddess.stockholdermeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 股东大会纪要子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "stockholdermeeting_summaryson")
public class SummarySon extends BaseEntity {

    /**
     * 发言部门
     */
    @Column(name = "speakDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言部门'")
    private String speakDepartment;

    /**
     * 发言岗位
     */
    @Column(name = "speakJob", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言岗位'")
    private String speakJob;

    /**
     * 发言人
     */
    @Column(name = "speaker", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言人'")
    private String speaker;

    /**
     * 发言内容
     */
    @Column(name = "speakContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发言内容'")
    private String speakContent;

    /**
     * 是否修改发言内容
     */
    @Column(name = "is_alter",  columnDefinition = "TINYINT(1)  COMMENT '是否修改发言内容'", insertable = false)
    private Boolean alter;

    /**
     * 修改后的发言内容
     */
    @Column(name = "alterContent",  columnDefinition = "VARCHAR(255)   COMMENT '修改后的发言内容'")
    private String alterContent;

    /**
     * 会议纪要信息
     */
    @Column(name = "summary_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '会议纪要信息'")
    private String summaryId;

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

    public String getSpeakContent() {
        return speakContent;
    }

    public void setSpeakContent(String speakContent) {
        this.speakContent = speakContent;
    }

    public Boolean getAlter() {
        return alter;
    }

    public void setAlter(Boolean alter) {
        this.alter = alter;
    }

    public String getAlterContent() {
        return alterContent;
    }

    public void setAlterContent(String alterContent) {
        this.alterContent = alterContent;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}