package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.entity.MeetingTopic;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 会议层面业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingLayBO extends BaseBO {

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