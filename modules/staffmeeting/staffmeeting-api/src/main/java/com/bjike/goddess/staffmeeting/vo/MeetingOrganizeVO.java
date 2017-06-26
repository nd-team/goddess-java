package com.bjike.goddess.staffmeeting.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.staffmeeting.enums.MeetingType;

/**
 * 员工代表大会组织内容表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingOrganizeVO {

    /**
     * id
     */
    private String id;
    /**
     * 会议编号
     */
    private String meetingNum;


    /**
     * 议题产生原因
     */
    private String topicReason;

    /**
     * 层面Id
     */
    private String layId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getTopicReason() {
        return topicReason;
    }

    public void setTopicReason(String topicReason) {
        this.topicReason = topicReason;
    }

    public String getLayId() {
        return layId;
    }

    public void setLayId(String layId) {
        this.layId = layId;
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

}