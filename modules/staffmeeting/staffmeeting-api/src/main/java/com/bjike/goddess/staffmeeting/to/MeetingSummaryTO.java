package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffmeeting.enums.MeetingPurpose;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 员工代表大会纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingSummaryTO extends BaseTO {

    /**
     * 实际参会人员
     */
    @NotBlank(message = "实际参会人员不能为空", groups = {ADD.class, EDIT.class})
    private String actualUsers;

    /**
     * 新增参会人员
     */
    private String addUsers;

    /**
     * 未参会人员
     */
    private String notAttendUsers;

    /**
     * 会议目的
     */
    @NotNull(message = "会议目的不能为空", groups = {ADD.class, EDIT.class})
    private MeetingPurpose meetingPurpose;

    /**
     * 参会人数
     */
    @NotNull(message = "参会人数不能为空", groups = {ADD.class, EDIT.class})
    private Integer attendAccount;

    /**
     * 实际参会时间
     */
    @NotBlank(message = "实际参会时间不能为空", groups = {ADD.class, EDIT.class})
    private String actualTime;

    /**
     * 实际会议地点
     */
    @NotBlank(message = "实际会议地点不能为空", groups = {ADD.class, EDIT.class})
    private String actualPlace;

    /**
     * 最终监督结果
     */
    private String superviseResult;

    /**
     * 最终表决结果
     */
    private String voteResult;

    /**
     * 会议记录人
     */
    @NotBlank(message = "会议记录人不能为空", groups = {ADD.class, EDIT.class})
    private String recordUser;

    /**
     * 会议主持人
     */
    @NotBlank(message = "会议主持人不能为空", groups = {ADD.class, EDIT.class})
    private String compere;

    public String getActualUsers() {
        return actualUsers;
    }

    public void setActualUsers(String actualUsers) {
        this.actualUsers = actualUsers;
    }

    public String getAddUsers() {
        return addUsers;
    }

    public void setAddUsers(String addUsers) {
        this.addUsers = addUsers;
    }

    public String getNotAttendUsers() {
        return notAttendUsers;
    }

    public void setNotAttendUsers(String notAttendUsers) {
        this.notAttendUsers = notAttendUsers;
    }

    public MeetingPurpose getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(MeetingPurpose meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public Integer getAttendAccount() {
        return attendAccount;
    }

    public void setAttendAccount(Integer attendAccount) {
        this.attendAccount = attendAccount;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPlace() {
        return actualPlace;
    }

    public void setActualPlace(String actualPlace) {
        this.actualPlace = actualPlace;
    }

    public String getSuperviseResult() {
        return superviseResult;
    }

    public void setSuperviseResult(String superviseResult) {
        this.superviseResult = superviseResult;
    }

    public String getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(String voteResult) {
        this.voteResult = voteResult;
    }

    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }

}