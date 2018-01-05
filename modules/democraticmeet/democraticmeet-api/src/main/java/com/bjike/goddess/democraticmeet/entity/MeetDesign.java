package com.bjike.goddess.democraticmeet.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 会议组织部分内容
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-02 11:27 ]
 * @Description: [ 会议组织部分内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "democraticmeet_meetdesign")
public class MeetDesign extends BaseEntity {

    /**
     * 会议层面
     */
    @Column(name = "meetLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议层面	'")
    private String meetLevel;

    /**
     * 计划参会岗位
     */
    @Column(name = "meetTitle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计划参会岗位'")
    private String meetTitle;

    /**
     * 上一次民主会议的自我总结
     */
    @Column(name = "meetContent",   columnDefinition = "VARCHAR(255)   COMMENT '上一次民主会议的自我总结'")
    private String meetContent;

    /**
     * 计划会议时间
     */
    @Column(name = "meetPlanTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划会议时间'")
    private LocalDateTime meetPlanTime;

    /**
     * 会议形式
     */
    @Column(name = "meetForm",   columnDefinition = "VARCHAR(255)   COMMENT '会议形式'")
    private String meetForm;

    /**
     * 会议地点
     */
    @Column(name = "meetArea",   columnDefinition = "VARCHAR(255)   COMMENT '会议地点'")
    private String meetArea;

    /**
     * 会议主持人
     */
    @Column(name = "hostMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议主持人'")
    private String hostMan;

    /**
     * 民主生活会议组织内容
     */
    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.REFRESH} )
    @JoinColumn(name = "democraticContent_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '民主生活会议组织内容'")
    private DemocraticContent democraticContent;



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

    public LocalDateTime getMeetPlanTime() {
        return meetPlanTime;
    }

    public void setMeetPlanTime(LocalDateTime meetPlanTime) {
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

    public DemocraticContent getDemocraticContent() {
        return democraticContent;
    }

    public void setDemocraticContent(DemocraticContent democraticContent) {
        this.democraticContent = democraticContent;
    }
}