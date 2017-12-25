package com.bjike.goddess.staffmeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 会议层面业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingLayBO extends BaseBO {

    /**
     * 议题Id
     */
    private String topicId;

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
    private String[] position;

    /**
     * 关联功能
     */
    private String relation;


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}