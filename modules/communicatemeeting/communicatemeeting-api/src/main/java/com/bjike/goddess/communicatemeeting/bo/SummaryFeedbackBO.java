package com.bjike.goddess.communicatemeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 纪要反馈投诉业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:39 ]
 * @Description: [ 纪要反馈投诉业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryFeedbackBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNumber;

    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 会议类型
     */
    private String meetingType;

    /**
     * 会议内容
     */
    private String meetingContent;

    /**
     * 会议议题
     */
    private String meetingTopic;

    /**
     * 最终结论
     */
    private String result;

    /**
     * 异议人
     */
    private String objection;

    /**
     * 异议内容
     */
    private String objectionContent;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getObjection() {
        return objection;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public String getObjectionContent() {
        return objectionContent;
    }

    public void setObjectionContent(String objectionContent) {
        this.objectionContent = objectionContent;
    }
}