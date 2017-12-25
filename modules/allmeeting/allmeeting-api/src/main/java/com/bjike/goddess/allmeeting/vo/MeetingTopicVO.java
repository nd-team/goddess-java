package com.bjike.goddess.allmeeting.vo;

/**
 * 议题管理表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingTopicVO {

    /**
     * id
     */
    private String id;
    /**
     * 主题
     */
    private String topic;

    /**
     * 议题包含内容
     */
    private String topicContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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