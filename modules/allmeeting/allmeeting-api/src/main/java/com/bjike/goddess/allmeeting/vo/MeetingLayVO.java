package com.bjike.goddess.allmeeting.vo;

/**
 * 会议层面表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingLayVO {

    /**
     * id
     */
    private String id;

    /**
     * 议题名称
     */
    private String topicName;

    /**
     * 议题包含内容
     */
    private String topicContent;

    /**
     * 层面名称
     */
    private String name;

    /**
     * 计划参会岗位
     */
    private String position;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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