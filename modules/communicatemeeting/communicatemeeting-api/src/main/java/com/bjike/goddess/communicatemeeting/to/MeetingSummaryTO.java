package com.bjike.goddess.communicatemeeting.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 交流会纪要
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingSummaryTO extends BaseTO {

    /**
     * 实际会议时间
     */
    @NotBlank(groups = {EDIT.class}, message = "实际会议时间不能为空")
    private String actualTime;

    /**
     * 实际参会人员
     */
    @NotBlank(groups = {EDIT.class}, message = "实际参会人员不能为空")
    private String[] actualPeoples;

    /**
     * 新增参会人员
     */
    @NotBlank(groups = {EDIT.class}, message = "新增参会人员不能为空")
    private String[] addPeoples;

    /**
     * 未参会人员
     */
    private String[] notAttends;

    /**
     * 参会人数
     */
    @NotNull(groups = {EDIT.class}, message = "参会人数不能为空")
    @Min(value = 0, groups = {EDIT.class}, message = "参会人数必须大于0")
    private Integer amount;

    /**
     * 发言部门
     */
    @NotBlank(groups = {EDIT.class}, message = "发言部门不能为空")
    private String speakDepartment;

    /**
     * 发言岗位
     */
    @NotBlank(groups = {EDIT.class}, message = "发言岗位不能为空")
    private String speakJob;

    /**
     * 发言人
     */
    @NotBlank(groups = {EDIT.class}, message = "发言人不能为空")
    private String speaker;

    /**
     * 一轮交流内容
     */
    @NotBlank(groups = {EDIT.class}, message = "一轮交流内容不能为空")
    private String oneRound;

    /**
     * 二轮交流内容
     */
    @NotBlank(groups = {EDIT.class}, message = "二轮交流内容不能为空")
    private String twoRound;

    /**
     * 最终结论
     */
    @NotBlank(groups = {EDIT.class}, message = "最终结论不能为空")
    private String result;

    /**
     * 会议记录人
     */
    @NotBlank(groups = {EDIT.class}, message = "会议记录人不能为空")
    private String recorder;

    /**
     * 交流会组织内容信息
     */
    private String organizeContentId;

    public String getOrganizeContentId() {
        return organizeContentId;
    }

    public void setOrganizeContentId(String organizeContentId) {
        this.organizeContentId = organizeContentId;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String[] getActualPeoples() {
        return actualPeoples;
    }

    public void setActualPeoples(String[] actualPeoples) {
        this.actualPeoples = actualPeoples;
    }

    public String[] getAddPeoples() {
        return addPeoples;
    }

    public void setAddPeoples(String[] addPeoples) {
        this.addPeoples = addPeoples;
    }

    public String[] getNotAttends() {
        return notAttends;
    }

    public void setNotAttends(String[] notAttends) {
        this.notAttends = notAttends;
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