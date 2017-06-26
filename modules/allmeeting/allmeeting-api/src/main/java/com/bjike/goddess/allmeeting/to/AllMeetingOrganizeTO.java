package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.allmeeting.enums.MeetingType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 所有工作内容汇总会议组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AllMeetingOrganizeTO extends BaseTO {

    /**
     * 层面Id
     */
    @NotBlank(message = "层面不能为空!", groups = {ADD.class, EDIT.class})
    private String layId;

    /**
     * 会议内容
     */
    @NotBlank(message = "会议内容不能为空!", groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 关联功能
     */
    @NotBlank(message = "关联功能不能为空!", groups = {ADD.class, EDIT.class})
    private String relation;

    /**
     * 计划参会人员
     */
    @NotBlank(message = "计划参会人员不能为空!", groups = {ADD.class, EDIT.class})
    private String planUser;

    /**
     * 计划会议时间
     */
    @NotBlank(message = "计划会议时间不能为空!", groups = {ADD.class, EDIT.class})
    private String planTime;

    /**
     * 会议形式
     */
    @NotNull(message = "会议形式不能为空!", groups = {ADD.class, EDIT.class})
    private MeetingType meetingType;

    /**
     * 会议主持人
     */
    @NotBlank(message = "会议主持人不能为空!", groups = {ADD.class, EDIT.class})
    private String compere;


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

}