package com.bjike.goddess.democraticmeet.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;

/**
 * 会议组织部分内容业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetDesignBO extends BaseBO {

    /**
     * 会议层面
     */
    private String meetLevel;

    /**
     * 计划参会岗位
     */
    private String meetTitle;

    /**
     * 上一次民主会议的自我总结
     */
    private String meetContent;

    /**
     * 计划会议时间
     */
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
    private String hostMan;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    /**
     * 会议计划参与人员表现层对象
     */
    private List<AttenderBO> attenderBOs;




    public String getMeetLevel() {
        return meetLevel;
    }

    public void setMeetLevel(String meetLevel) {
        this.meetLevel = meetLevel;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
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

    public List<AttenderBO> getAttenderBOs() {
        return attenderBOs;
    }

    public void setAttenderBOs(List<AttenderBO> attenderBOs) {
        this.attenderBOs = attenderBOs;
    }
}