package com.bjike.goddess.stockholdermeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.stockholdermeeting.enums.SummaryStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 股东大会纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "stockholdermeeting_summary")
public class Summary extends BaseEntity {

    /**
     * 实际会议时间
     */
    @Column(name = "actualTime", nullable = false, columnDefinition = "DATETIME   COMMENT '实际会议时间'")
    private LocalDateTime actualTime;

    /**
     * 实际参会人员
     */
    @Column(name = "actualAttend", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '实际参会人员'")
    private String actualAttend;

    /**
     * 新增参会人员
     */
    @Column(name = "addAttend", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '新增参会人员'")
    private String addAttend;

    /**
     * 未参会人员
     */
    @Column(name = "notAttend", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未参会人员'")
    private String notAttend;

    /**
     * 参会人数
     */
    @Column(name = "num", nullable = false, columnDefinition = "INT(11)   COMMENT '参会人数'")
    private Integer num;


    /**
     * 会议最终决议
     */
    @Column(name = "result", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议最终决议'")
    private String result;

    /**
     * 会议记录人
     */
    @Column(name = "recorder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议记录人'")
    private String recorder;

    /**
     * 会议编号
     */
    @Column(name = "meetingNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNumber;

    /**
     * 会议纪要状态
     */
    @Column(name = "summaryStatus", columnDefinition = "TINYINT(2) COMMENT '会议纪要状态'", nullable = false)
    private SummaryStatus summaryStatus;

    public SummaryStatus getSummaryStatus() {
        return summaryStatus;
    }

    public void setSummaryStatus(SummaryStatus summaryStatus) {
        this.summaryStatus = summaryStatus;
    }

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