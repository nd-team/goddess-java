package com.bjike.goddess.communicatemeeting.vo;

/**
 * 交流会纪要表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingSummaryVO {

    /**
     * id
     */
    private String id;
    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 实际参会人员
     */
    private String actualPeople;

    /**
     * 新增参会人员
     */
    private String addPeople;

    /**
     * 未参会人员
     */
    private String notAttend;

    /**
     * 参会人数
     */
    private Integer amount;

    /**
     * 发言部门
     */
    private String speakDepartment;

    /**
     * 发言岗位
     */
    private String speakJob;

    /**
     * 发言人
     */
    private String speaker;

    /**
     * 一轮交流内容
     */
    private String oneRound;

    /**
     * 二轮交流内容
     */
    private String twoRound;

    /**
     * 最终结论
     */
    private String result;

    /**
     * 会议编号
     */
    private String meetingNumber;

    /**
     * 会议形式
     */
    private String meetingFormat;

    /**
     * 会议地点
     */
    private String meetingArea;

    /**
     * 会议议题
     */
    private String meetingTopic;

    /**
     * 会议内容
     */
    private String meetingContent;

    /**
     * 会议组织人
     */
    private String organization;

    /**
     * 会议记录人
     */
    private String recorder;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
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

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingContent() {
        return meetingContent;
    }

    public void setMeetingContent(String meetingContent) {
        this.meetingContent = meetingContent;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualPeople() {
        return actualPeople;
    }

    public void setActualPeople(String actualPeople) {
        this.actualPeople = actualPeople;
    }

    public String getAddPeople() {
        return addPeople;
    }

    public void setAddPeople(String addPeople) {
        this.addPeople = addPeople;
    }

    public String getNotAttend() {
        return notAttend;
    }

    public void setNotAttend(String notAttend) {
        this.notAttend = notAttend;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSpeakDepartment() {
        return speakDepartment;
    }

    public void setSpeakDepartment(String speakDepartment) {
        this.speakDepartment = speakDepartment;
    }

    public String getSpeakJob() {
        return speakJob;
    }

    public void setSpeakJob(String speakJob) {
        this.speakJob = speakJob;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getOneRound() {
        return oneRound;
    }

    public void setOneRound(String oneRound) {
        this.oneRound = oneRound;
    }

    public String getTwoRound() {
        return twoRound;
    }

    public void setTwoRound(String twoRound) {
        this.twoRound = twoRound;
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
}