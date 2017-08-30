package com.bjike.goddess.stockholdermeeting.vo;

/**
 * 股东大会纪要子表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummarySonVO {

    /**
     * id
     */
    private String id;
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
     * 是否修改发言内容
     */
    private Boolean alter;

    /**
     * 修改后的发言内容
     */
    private String alterContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}