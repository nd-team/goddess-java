package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 节假日工作安排
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_holidayworkplan")
public class HolidayWorkPlan extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 加班原因
     */
    @Column(name = "overTimeReasion",  columnDefinition = "VARCHAR(255)   COMMENT '加班原因'")
    private String overTimeReasion;

    /**
     * 加班名单
     */
    @Column(name = "overTimeEmper",  columnDefinition = "VARCHAR(255)   COMMENT '加班名单'")
    private String overTimeEmper;

    /**
     * 加班时间
     */
    @Column(name = "overTimeTime",  columnDefinition = "VARCHAR(255)   COMMENT '加班时间'")
    private String overTimeTime;

    /**
     * 加班总人数
     */
    @Column(name = "overTimeEmperNum",  columnDefinition = "VARCHAR(255)   COMMENT '加班总人数'")
    private String overTimeEmperNum;

    /**
     * 加班报酬形式
     */
    @Column(name = "overTimeSalary", columnDefinition = "VARCHAR(255)   COMMENT '加班报酬形式'")
    private String overTimeSalary;

    /**
     * 安全检查员名单
     */
    @Column(name = "saferName",  columnDefinition = "VARCHAR(255)   COMMENT '安全检查员名单'")
    private String saferName;

    /**
     * 安全检查员总人数
     */
    @Column(name = "saferNum",  columnDefinition = "VARCHAR(255)   COMMENT '安全检查员总人数'")
    private String saferNum;

    /**
     * 应急方案
     */
    @Column(name = "emergencyPlan",  columnDefinition = "VARCHAR(255)   COMMENT '应急方案'")
    private String emergencyPlan;

    /**
     * 法定节假日放假方案
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "holidayProgramme_id",  columnDefinition = "VARCHAR(36)   COMMENT '法定节假日放假方案'")
    private HolidayProgramme holidayProgramme;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getOverTimeReasion() {
        return overTimeReasion;
    }

    public void setOverTimeReasion(String overTimeReasion) {
        this.overTimeReasion = overTimeReasion;
    }

    public String getOverTimeEmper() {
        return overTimeEmper;
    }

    public void setOverTimeEmper(String overTimeEmper) {
        this.overTimeEmper = overTimeEmper;
    }

    public String getOverTimeTime() {
        return overTimeTime;
    }

    public void setOverTimeTime(String overTimeTime) {
        this.overTimeTime = overTimeTime;
    }

    public String getOverTimeEmperNum() {
        return overTimeEmperNum;
    }

    public void setOverTimeEmperNum(String overTimeEmperNum) {
        this.overTimeEmperNum = overTimeEmperNum;
    }

    public String getOverTimeSalary() {
        return overTimeSalary;
    }

    public void setOverTimeSalary(String overTimeSalary) {
        this.overTimeSalary = overTimeSalary;
    }

    public String getSaferName() {
        return saferName;
    }

    public void setSaferName(String saferName) {
        this.saferName = saferName;
    }

    public String getSaferNum() {
        return saferNum;
    }

    public void setSaferNum(String saferNum) {
        this.saferNum = saferNum;
    }

    public String getEmergencyPlan() {
        return emergencyPlan;
    }

    public void setEmergencyPlan(String emergencyPlan) {
        this.emergencyPlan = emergencyPlan;
    }

    public HolidayProgramme getHolidayProgramme() {
        return holidayProgramme;
    }

    public void setHolidayProgramme(HolidayProgramme holidayProgramme) {
        this.holidayProgramme = holidayProgramme;
    }
}