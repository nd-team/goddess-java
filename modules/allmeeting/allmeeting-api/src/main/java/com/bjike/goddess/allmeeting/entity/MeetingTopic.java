package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 议题管理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_topic", uniqueConstraints = {@UniqueConstraint(columnNames = {"topic", "topicContent"})})
public class MeetingTopic extends BaseEntity {

    /**
     * 主题
     */
    @Column(name = "topic", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主题'")
    private String topic;

    /**
     * 议题包含内容
     */
    @Column(name = "topicContent", nullable = false, columnDefinition = "TEXT   COMMENT '议题包含内容'")
    private String topicContent;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }
}