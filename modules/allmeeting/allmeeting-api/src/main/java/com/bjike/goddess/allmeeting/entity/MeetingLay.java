package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(name = "allmeeting_meetinglay", uniqueConstraints = {@UniqueConstraint(columnNames = {"topicId", "name"})})
public class MeetingLay extends BaseEntity {

    /**
     * 议题Id
     */
    @Column(name = "topicId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '议题Id'")
    private String topicId;

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


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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
}