package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 会议组织部分内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetDesignTO extends BaseTO {


    private interface TestAdd{}
    private interface TestGetPerson{}

    /**
     * 会议层面
     */
    @NotBlank(groups = {MeetDesignTO.TestAdd.class} , message = "会议层面不能为空")
    private String meetLevel;

    /**
     * 计划参会岗位数组
     */
    @NotNull(groups = {MeetDesignTO.TestAdd.class} , message = "计划参会岗位数组不能为空")
    private String[] meetTitles;

    /**
     * 上一次民主会议的自我总结
     */private String meetContent;

    /**
     * 计划会议时间
     */
    @NotBlank(groups = {MeetDesignTO.TestAdd.class} , message = "计划会议时间不能为空")
    private String meetPlanTime;

    /**
     * 会议形式
     */
    private String meetForm;

    /**
     * 会议地点
     */
    private String meetArea;

    /**
     * 会议主持人
     */
    @NotBlank(groups = {MeetDesignTO.TestAdd.class} , message = "会议主持人不能为空")
    private String hostMan;

    /**
     * 计划参与人员数组
     */
    @NotBlank(groups = {MeetDesignTO.TestAdd.class} , message = "计划参与人员数组不能为空")
    private String[] attenders;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    /**
     * 计划参会岗位id数组
     */
    private String[] positionIds;


    public String getMeetLevel() {
        return meetLevel;
    }

    public void setMeetLevel(String meetLevel) {
        this.meetLevel = meetLevel;
    }

    public String[] getMeetTitles() {
        return meetTitles;
    }

    public void setMeetTitles(String[] meetTitles) {
        this.meetTitles = meetTitles;
    }

    public String getMeetContent() {
        return meetContent;
    }

    public void setMeetContent(String meetContent) {
        this.meetContent = meetContent;
    }

    public String getMeetPlanTime() {
        return meetPlanTime;
    }

    public void setMeetPlanTime(String meetPlanTime) {
        this.meetPlanTime = meetPlanTime;
    }

    public String getMeetForm() {
        return meetForm;
    }

    public void setMeetForm(String meetForm) {
        this.meetForm = meetForm;
    }

    public String getMeetArea() {
        return meetArea;
    }

    public void setMeetArea(String meetArea) {
        this.meetArea = meetArea;
    }

    public String getHostMan() {
        return hostMan;
    }

    public void setHostMan(String hostMan) {
        this.hostMan = hostMan;
    }

    public String[] getAttenders() {
        return attenders;
    }

    public void setAttenders(String[] attenders) {
        this.attenders = attenders;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String[] getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String[] positionIds) {
        this.positionIds = positionIds;
    }
}