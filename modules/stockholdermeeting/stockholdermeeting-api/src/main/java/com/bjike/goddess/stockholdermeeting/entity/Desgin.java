package com.bjike.goddess.stockholdermeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 股东大会组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "stockholdermeeting_desgin")
public class Desgin extends BaseEntity {

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
    @Column(name = "topicContent", nullable = false, columnDefinition = "TEXT   COMMENT '议题包含内容'")
    private String topicContent;

    /**
     * 会议议题关联的功能
     */
    @Column(name = "function", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议议题关联的功能'")
    private String function;

    /**
     * 计划会议时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划会议时间'")
    private String planTime;


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

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}