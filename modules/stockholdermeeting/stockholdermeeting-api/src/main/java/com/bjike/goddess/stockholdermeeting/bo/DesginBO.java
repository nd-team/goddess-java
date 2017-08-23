package com.bjike.goddess.stockholdermeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 股东大会组织内容设计业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesginBO extends BaseBO {

    /**
     * 会议层面
     */
    private String meetingLevel;

    /**
     * 会议议题
     */
    private String meetingTopic;

    /**
     * 议题包含内容
     */
    private String topicContent;

    /**
     * 会议议题关联的功能
     */
    private String function;

    /**
     * 计划会议时间
     */
    private String planTime;


    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}