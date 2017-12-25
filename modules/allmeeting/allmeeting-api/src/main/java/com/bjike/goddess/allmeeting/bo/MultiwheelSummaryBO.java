package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.allmeeting.enums.MeetingType;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 简洁交流讨论纪要业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultiwheelSummaryBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNum;

    /**
     * 关联功能
     */
    private String relation;

    /**
     * 会议主持人
     */
    private String compere;


    /**
     * 会议组织人
     */
    private String organizer;


    /**
     * 会议议题
     */
    private String topic;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 会议形式
     */
    private MeetingType meetingType;

    /**
     * 会议地点
     */
    private String place;

    /**
     * 实际参会人员
     */
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
     * 参会人数
     */
    private Integer attendAccount;

    /**
     * 会议所属模块
     */
    private String module;

    /**
     * 会议所属部门
     */
    private String department;

    /**
     * 最终结论
     */
    private String ultimatelyResult;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Status status;


    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

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

    public Integer getAttendAccount() {
        return attendAccount;
    }

    public void setAttendAccount(Integer attendAccount) {
        this.attendAccount = attendAccount;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUltimatelyResult() {
        return ultimatelyResult;
    }

    public void setUltimatelyResult(String ultimatelyResult) {
        this.ultimatelyResult = ultimatelyResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}