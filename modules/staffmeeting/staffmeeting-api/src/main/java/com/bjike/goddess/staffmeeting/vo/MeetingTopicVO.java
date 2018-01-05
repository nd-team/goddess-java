package com.bjike.goddess.staffmeeting.vo;

/**
 * 议题管理表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:31 ]
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
     * 议题
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