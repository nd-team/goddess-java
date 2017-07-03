package com.bjike.goddess.communicatemeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.communicatemeeting.enums.PlanJobStatus;
import com.bjike.goddess.communicatemeeting.enums.PlanTimeStatus;

/**
 * 交流会组织内容设计业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 03:31 ]
 * @Description: [ 交流会组织内容设计业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesginBO extends BaseBO {

    /**
     * 会议议题
     */
    private String meetingTopic;

    /**
     * 会议层面
     */
    private String meetingLevel;

    /**
     * 计划参会岗位
     */
    private String planJob;

    /**
     * 议题包含内容
     */
    private String topicContent;

    /**
     * 计划会议时间
     */
    private String planTime;

    /**
     * 计划参会岗位状态
     */
    private PlanJobStatus planJobStatus;

    /**
     * 计划会议时间状态
     */
    private PlanTimeStatus planTimeStatus;

    /**
     * 会议编号
     */
    private String meetingNumber;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public PlanJobStatus getPlanJobStatus() {
        return planJobStatus;
    }

    public void setPlanJobStatus(PlanJobStatus planJobStatus) {
        this.planJobStatus = planJobStatus;
    }

    public PlanTimeStatus getPlanTimeStatus() {
        return planTimeStatus;
    }

    public void setPlanTimeStatus(PlanTimeStatus planTimeStatus) {
        this.planTimeStatus = planTimeStatus;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingLevel() {
        return meetingLevel;
    }

    public void setMeetingLevel(String meetingLevel) {
        this.meetingLevel = meetingLevel;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}