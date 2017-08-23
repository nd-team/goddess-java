package com.bjike.goddess.negotiatemeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 纪要反馈投诉
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "negotiatemeeting_summaryfeedback")
public class SummaryFeedback extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNumber;

    /**
     * 异议人
     */
    @Column(name = "objection", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '异议人'")
    private String objection;

    /**
     * 异议内容
     */
    @Column(name = "objectionContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '异议内容'")
    private String objectionContent;


    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public String getObjection() {
        return objection;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public String getObjectionContent() {
        return objectionContent;
    }

    public void setObjectionContent(String objectionContent) {
        this.objectionContent = objectionContent;
    }
}