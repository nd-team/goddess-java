package com.bjike.goddess.negotiatemeeting.vo;

/**
 * 纪要反馈投诉表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryFeedbackVO {

    /**
     * id
     */
    private String id;
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
     * 议题产生原因
     */
    private String reason;

    /**
     * 议题目的
     */
    private String purpose;

    /**
     * 最终协商结果
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}