package com.bjike.goddess.negotiatemeeting.excel;

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
public class SummaryExcel implements Serializable {
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
     * 参会人
     */
    @ExcelHeader(name = "参会人", notNull = true)
    private String attend;

    /**
     * 协商意见
     */
    @ExcelHeader(name = "协商意见", notNull = true)
    private String opinion;

    /**
     * 最终协商结果
     */
    @ExcelHeader(name = "最终协商结果", notNull = true)
    private String result;

    /**
     * 会议记录人
     */
    @ExcelHeader(name = "会议记录人", notNull = true)
    private String recorder;

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

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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
