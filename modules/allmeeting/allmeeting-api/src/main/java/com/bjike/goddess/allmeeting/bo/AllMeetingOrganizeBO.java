package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.enums.MeetingType;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 所有工作内容汇总会议组织内容业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AllMeetingOrganizeBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNum;

    /**
     * 会议议题
     */
    private String topic;

    /**
     * 会议议题
     */
    private String topicContent;

    /**
     * 会议层面
     */
    private String layName;

    /**
     * 计划参会岗位
     */
    private String position;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 关联功能
     */
    private String relation;

    /**
     * 计划参会人员
     */
    private String planUser;

    /**
     * 计划会议时间
     */
    private String planTime;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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
}