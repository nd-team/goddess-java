package com.bjike.goddess.stockholdermeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.stockholdermeeting.enums.OrganizationStauts;

/**
 * 股东大会组织内容业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:46 ]
 * @Description: [ 股东大会组织内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OrganizationBO extends BaseBO {

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
     * 会议议题关联的功能
     */
    private String function;

    /**
     * 计划参会岗位
     */
    private String planJob;

    /**
     * 计划参会人员
     */
    private String planAttend;

    /**
     * 计划会议时间
     */
    private String planTime;

    /**
     * 会议形式
     */
    private String meetingFormat;

    /**
     * 会议地点
     */
    private String area;

    /**
     * 会议目的
     */
    private String purpose;

    /**
     * 会议组织人
     */
    private String organization;

    /**
     * 会议主持人
     */
    private String host;

    /**
     * 会议编号
     */
    private String meetingNumber;

    /**
     * 组织内容状态
     */
    private OrganizationStauts organizationStauts;

    public OrganizationStauts getOrganizationStauts() {
        return organizationStauts;
    }

    public void setOrganizationStauts(OrganizationStauts organizationStauts) {
        this.organizationStauts = organizationStauts;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPlanJob() {
        return planJob;
    }

    public void setPlanJob(String planJob) {
        this.planJob = planJob;
    }

    public String getPlanAttend() {
        return planAttend;
    }

    public void setPlanAttend(String planAttend) {
        this.planAttend = planAttend;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}