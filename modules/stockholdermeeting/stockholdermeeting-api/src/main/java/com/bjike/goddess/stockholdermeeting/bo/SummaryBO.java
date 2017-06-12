package com.bjike.goddess.stockholdermeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.stockholdermeeting.enums.SummaryStatus;

/**
 * 股东大会纪要业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryBO extends BaseBO {
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
    private String area;
    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 实际参会人员
     */
    private String actualAttend;

    /**
     * 新增参会人员
     */
    private String addAttend;

    /**
     * 未参会人员
     */
    private String notAttend;

    /**
     * 参会人数
     */
    private Integer num;

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
     * 发言内容
     */
    private String speakContent;
    /**
     * 会议最终决议
     */
    private String result;
    /**
     * 会议组织人
     */
    private String organization;
    /**
     * 会议目的
     */
    private String purpose;
    /**
     * 会议主持人
     */
    private String host;
    /**
     * 会议记录人
     */
    private String recorder;
    /**
     * 是否修改发言内容
     */
    private Boolean alter;

    /**
     * 修改后的发言内容
     */
    private String alterContent;

    /**
     * 会议纪要子表id
     */
    private String summarySonId;

    /**
     * 会议纪要状态
     */
    private SummaryStatus summaryStatus;

    public SummaryStatus getSummaryStatus() {
        return summaryStatus;
    }

    public void setSummaryStatus(SummaryStatus summaryStatus) {
        this.summaryStatus = summaryStatus;
    }

    public String getSummarySonId() {
        return summarySonId;
    }

    public void setSummarySonId(String summarySonId) {
        this.summarySonId = summarySonId;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getActualAttend() {
        return actualAttend;
    }

    public void setActualAttend(String actualAttend) {
        this.actualAttend = actualAttend;
    }

    public String getAddAttend() {
        return addAttend;
    }

    public void setAddAttend(String addAttend) {
        this.addAttend = addAttend;
    }

    public String getNotAttend() {
        return notAttend;
    }

    public void setNotAttend(String notAttend) {
        this.notAttend = notAttend;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getSpeakContent() {
        return speakContent;
    }

    public void setSpeakContent(String speakContent) {
        this.speakContent = speakContent;
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

    public Boolean getAlter() {
        return alter;
    }

    public void setAlter(Boolean alter) {
        this.alter = alter;
    }

    public String getAlterContent() {
        return alterContent;
    }

    public void setAlterContent(String alterContent) {
        this.alterContent = alterContent;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
