package com.bjike.goddess.communicatemeeting.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;

/**
 * 交流会纪要excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-27 15:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MeetingSummaryExcel implements Serializable {
    /**
     * 会议编号
     */
    @ExcelHeader(name = "会议编号", notNull = true)
    private String meetingNumber;

    /**
     * 实际会议时间
     */
    @ExcelHeader(name = "实际会议时间", notNull = true)
    private String actualTime;

    /**
     * 实际参会人员
     */
    @ExcelHeader(name = "实际参会人员", notNull = true)
    private String actualPeople;

    /**
     * 新增参会人员
     */
    @ExcelHeader(name = "新增参会人员", notNull = true)
    private String addPeople;

    /**
     * 未参会人员
     */
    @ExcelHeader(name = "未参会人员", notNull = true)
    private String notAttend;

    /**
     * 参会人数
     */
    @ExcelHeader(name = "参会人数", notNull = true)
    private Integer amount;

    /**
     * 发言部门
     */
    @ExcelHeader(name = "发言部门", notNull = true)
    private String speakDepartment;

    /**
     * 发言岗位
     */
    @ExcelHeader(name = "发言岗位", notNull = true)
    private String speakJob;

    /**
     * 发言人
     */
    @ExcelHeader(name = "发言人", notNull = true)
    private String speaker;

    /**
     * 一轮交流内容
     */
    @ExcelHeader(name = "一轮交流内容", notNull = true)
    private String oneRound;

    /**
     * 二轮交流内容
     */
    @ExcelHeader(name = "二轮交流内容", notNull = true)
    private String twoRound;

    /**
     * 最终结论
     */
    @ExcelHeader(name = "最终结论", notNull = true)
    private String result;

    /**
     * 会议记录人
     */
    @ExcelHeader(name = "会议记录人", notNull = true)
    private String recorder;

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

}
