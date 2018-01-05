package com.bjike.goddess.communicatemeeting.vo;

/**
 * 交流会组织内容表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizeContentVO {

    /**
     * id
     */
    private String id;
    /**
     * 会议类型
     */
    private String meetingType;

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
     * 会议内容
     */
    private String meetingContent;

    /**
     * 计划参会岗位
     */
    private String planJob;

    /**
     * 计划参会人员
     */
    private String planPeople;

    /**
     * 计划会议时间
     */
    private String planTime;

    /**
     * 会议形式
     */
    private String meetingFormat;

    /**
     * 会议主持人
     */
    private String host;

    /**
     * 会议组织人
     */
    private String organization;

    /**
     * 会议地点
     */
    private String meetingArea;

    /**
     * 会议编号
     */
    private String meetingNumber;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

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

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getPlanPeople() {
        return planPeople;
    }

    public void setPlanPeople(String planPeople) {
        this.planPeople = planPeople;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(String meetingFormat) {
        this.meetingFormat = meetingFormat;
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

    public String getMeetingArea() {
        return meetingArea;
    }

    public void setMeetingArea(String meetingArea) {
        this.meetingArea = meetingArea;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }
}