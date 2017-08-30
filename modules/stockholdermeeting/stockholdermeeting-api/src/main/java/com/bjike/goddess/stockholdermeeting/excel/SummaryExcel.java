package com.bjike.goddess.stockholdermeeting.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会议纪要excel
 * @Author: [chenjunhao]
 * @Date: [2017-06-07 16:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SummaryExcel extends BaseTO implements Serializable {
    /**
     * 会议编号
     */
    @ExcelHeader(name = "会议编号", notNull = true)
    private String meetingNumber;

    /**
     * 实际会议时间
     */
    @ExcelHeader(name = "实际会议时间", notNull = true)
    private LocalDateTime actualTime;

    /**
     * 实际参会人员
     */
    @ExcelHeader(name = "实际参会人员", notNull = true)
    private String actualAttend;

    /**
     * 新增参会人员
     */
    @ExcelHeader(name = "新增参会人员", notNull = true)
    private String addAttend;

    /**
     * 未参会人员
     */
    @ExcelHeader(name = "未参会人员", notNull = true)
    private String notAttend;

    /**
     * 参会人数
     */
    @ExcelHeader(name = "参会人数", notNull = true)
    private Integer num;

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
     * 发言内容
     */
    @ExcelHeader(name = "发言内容", notNull = true)
    private String speakContent;

    /**
     * 会议最终决议
     */
    @ExcelHeader(name = "会议最终决议", notNull = true)
    private String result;

    /**
     * 会议记录人
     */
    @ExcelHeader(name = "会议记录人", notNull = true)
    private String recorder;

//    /**
//     * 是否修改发言内容
//     */
//    @ExcelHeader(name = "是否修改发言内容", notNull = true)
//    private Boolean alter;
//
//    /**
//     * 修改后的发言内容
//     */
//    @ExcelHeader(name = "修改后的发言内容", notNull = true)
//    private String alterContent;

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public void setMeetingNumber(String meetingNumber) {
        this.meetingNumber = meetingNumber;
    }

    public LocalDateTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalDateTime actualTime) {
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

//    public Boolean getAlter() {
//        return alter;
//    }
//
//    public void setAlter(Boolean alter) {
//        this.alter = alter;
//    }
//
//    public String getAlterContent() {
//        return alterContent;
//    }
//
//    public void setAlterContent(String alterContent) {
//        this.alterContent = alterContent;
//    }
}
