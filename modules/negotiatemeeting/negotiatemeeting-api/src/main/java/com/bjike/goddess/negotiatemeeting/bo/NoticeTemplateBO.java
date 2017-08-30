package com.bjike.goddess.negotiatemeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 协商会议通告模板
 * @Author: [chenjunhao]
 * @Date: [2017-06-01 11:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NoticeTemplateBO extends BaseBO{
    /**
     * 会议类型
     */
    private String meetingType;

    /**
     * 会议编号
     */
    private String meetingNumber;

    /**
     * 计划会议时间
     */
    private String planTime;

    /**
     * 实际会议时间
     */
    private String actualTime;
    /**
     * 会议形式
     */
    private String meetingFormat;
    /**
     * 会议地点
     */
    private String meetingArea;
    /**
     * 计划参会人员
     */
    private String planAttend;
    /**
     * 实际参会人员
     */
    private String actualAttend;
    /**
     * 新增参会人员
     */
    private String addAttend;
    /**
     * 未参会人员
     */
    private String notAttend;
    /**
     * 参会人数
     */
    private Integer num;
    /**
     * 会议议题
     */
    private String meetingTopic;
    /**
     * 议题产生原因
     */
    private String reason;
    /**
     * 最终协商结果
     */
    private String result;
    /**
     * 会议记录人
     */
    private String recorder;
    /**
     * 会议主持人
     */
    private String host;
    /**
     * 会议组织人
     */
    private String organization;

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(String meetingFormat) {
        this.meetingFormat = meetingFormat;
    }

    public String getMeetingArea() {
        return meetingArea;
    }

    public void setMeetingArea(String meetingArea) {
        this.meetingArea = meetingArea;
    }

    public String getPlanAttend() {
        return planAttend;
    }

    public void setPlanAttend(String planAttend) {
        this.planAttend = planAttend;
    }

    public String getActualAttend() {
        return actualAttend;
    }

    public void setActualAttend(String actualAttend) {
        this.actualAttend = actualAttend;
    }

    public String getAddAttend() {
        return addAttend;
    }

    public void setAddAttend(String addAttend) {
        this.addAttend = addAttend;
    }

    public String getNotAttend() {
        return notAttend;
    }

    public void setNotAttend(String notAttend) {
        this.notAttend = notAttend;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
