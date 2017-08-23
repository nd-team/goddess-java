package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 议题管理业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingTopicBO extends BaseBO {

    /**
     * 主题
     */
    private String topic;

    /**
     * 议题包含内容
     */
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