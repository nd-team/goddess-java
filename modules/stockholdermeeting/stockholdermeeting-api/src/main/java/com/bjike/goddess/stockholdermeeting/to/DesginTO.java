package com.bjike.goddess.stockholdermeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 股东大会组织内容设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesginTO extends BaseTO {

    /**
     * 会议层面
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议层面不能为空")
    private String meetingLevel;

    /**
     * 会议议题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议议题不能为空")
    private String meetingTopic;

    /**
     * 议题包含内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "议题包含内容不能为空")
    private String topicContent;

    /**
     * 会议议题关联的功能
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "会议议题关联的功能不能为空")
    private String function;

    /**
     * 计划会议时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "计划会议时间不能为空")
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