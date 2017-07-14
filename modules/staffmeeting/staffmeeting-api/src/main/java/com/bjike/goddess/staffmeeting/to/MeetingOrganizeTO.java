package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.staffmeeting.enums.MeetingPurpose;
import com.bjike.goddess.staffmeeting.enums.MeetingType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 员工代表大会组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingOrganizeTO extends BaseTO {

    /**
     * 层面Id
     */
    @NotBlank(message = "层面Id不能为空",groups = {ADD.class, EDIT.class})
    private String layId;

    /**
     * 议题目的
     */
    @NotNull(message = "议题目的不能为空",groups = {ADD.class, EDIT.class})
    private MeetingPurpose meetingPurpose;

    /**
     * 议题产生原因
     */
    @NotBlank(message = "议题产生原因不能为空",groups = {ADD.class, EDIT.class})
    private String topicReason;

    /**
     * 会议内容
     */
    @NotBlank(message = "会议内容不能为空",groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 计划参会人员
     */
    @NotBlank(message = "计划参会人员不能为空",groups = {ADD.class, EDIT.class})
    private String planUser;

    /**
     * 计划参会时间
     */
    @NotBlank(message = "计划参会时间不能为空",groups = {ADD.class, EDIT.class})
    private String planTime;

    /**
     * 计划会议地点
     */
    @NotBlank(message = "计划会议地点不能为空",groups = {ADD.class, EDIT.class})
    private String planPlace;

    /**
     * 会议形式
     */
    @NotNull(message = "会议形式不能为空",groups = {ADD.class, EDIT.class})
    private MeetingType meetingType;

    /**
     * 会议主持人
     */
    @NotBlank(message = "会议主持人不能为空",groups = {ADD.class, EDIT.class})
    private String compere;

    /**
     * 会议组织人
     */
    @NotBlank(message = "会议组织人不能为空",groups = {ADD.class, EDIT.class})
    private String organizer;

    public String getLayId() {
        return layId;
    }

    public void setLayId(String layId) {
        this.layId = layId;
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