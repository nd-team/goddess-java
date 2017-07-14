package com.bjike.goddess.staffmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 会议层面
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffmeeting_lay", uniqueConstraints = {@UniqueConstraint(columnNames = {"meetingTopic_id", "name"})})
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
    private String position;

    /**
     * 关联功能
     */
    @Column(name = "relation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '关联功能'")
    private String relation;


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}