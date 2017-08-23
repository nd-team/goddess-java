package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 会议层面
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_meetinglay", uniqueConstraints = {@UniqueConstraint(columnNames = {"meetingTopic_id", "name"})})
public class MeetingLay extends BaseEntity {

    /**
     * 议题
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meetingTopic_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '议题'")
    private MeetingTopic meetingTopic;

    /**
     * 层面名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '层面名称'")
    private String name;

    /**
     * 计划参会岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会岗位'")
    private StringBuilder[] position;

    public StringBuilder[] getPosition() {
        return position;
    }

    public void setPosition(StringBuilder[] position) {
        this.position = position;
    }

    public MeetingTopic getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(MeetingTopic meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}