package com.bjike.goddess.staffmeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.staffmeeting.enums.MeetingPurpose;
import com.bjike.goddess.staffmeeting.enums.MeetingType;

/**
 * 员工代表大会组织内容业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingOrganizeBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNum;

    /**
     * 议题产生原因
     */
    private String topicReason;


    /**
     * 会议议题
     */
    private String topic;

    /**
     * 议题目的
     */
    private MeetingPurpose meetingPurpose;

    /**
     * 会议议题
     */
    private String topicContent;

    /**
     * 会议层面
     */
    private String layName;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 计划参会人员
     */
    private String planUser;

    /**
     * 计划参会时间
     */
    private String planTime;

    /**
     * 计划参会岗位
     */
    private String position;

    /**
     * 计划会议地点
     */
    private String planPlace;

    /**
     * 会议形式
     */
    private MeetingType meetingType;

    /**
     * 会议主持人
     */
    private String compere;

    /**
     * 会议组织人
     */
    private String organizer;

    /**
     * 状态
     */
    private Status status;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public MeetingPurpose getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(MeetingPurpose meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public String getTopicReason() {
        return topicReason;
    }

    public void setTopicReason(String topicReason) {
        this.topicReason = topicReason;
    }

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

    public String getLayName() {
        return layName;
    }

    public void setLayName(String layName) {
        this.layName = layName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlanUser() {
        return planUser;
    }

    public void setPlanUser(String planUser) {
        this.planUser = planUser;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getPlanPlace() {
        return planPlace;
    }

    public void setPlanPlace(String planPlace) {
        this.planPlace = planPlace;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}