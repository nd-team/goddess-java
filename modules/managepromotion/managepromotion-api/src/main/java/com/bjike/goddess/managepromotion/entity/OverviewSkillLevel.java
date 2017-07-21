package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 技能等级情况概览
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_overviewskilllevel")
public class OverviewSkillLevel extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 体系
     */
    @Column(name = "system", columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "jobs", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 技能定位-专业（业务范围包含的技能）
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '技能定位-专业（业务范围包含的技能）'")
    private String major;

    /**
     * 小项主项技能等级
     */
    @Column(name = "minorMasterSkillLevel", columnDefinition = "VARCHAR(255)   COMMENT '小项主项技能等级'")
    private String minorMasterSkillLevel;

    /**
     * 转正技能等级
     */
    @Column(name = "transferSkillLevel", columnDefinition = "VARCHAR(255)   COMMENT '转正技能等级'")
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    @Column(name = "positiveTime", columnDefinition = "DATE   COMMENT '转正时间'")
    private LocalDate positiveTime;

    /**
     * 获取时间
     */
    @Column(name = "acquisitionTime", columnDefinition = "DATE   COMMENT '获取时间'")
    private LocalDate acquisitionTime;

    /**
     * 已晋升次数
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '已晋升次数'")
    private Integer promotedNumber;

    /**
     * 主项/小项（是否为主项）
     */
    @Column(name = "is_subject", columnDefinition = "TINYINT(2)  COMMENT '主项/小项（是否为主项）'")
    private Boolean subject;

    /**
     * 小项顺序
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '小项顺序'")
    private Integer orderEvent;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinorMasterSkillLevel() {
        return minorMasterSkillLevel;
    }

    public void setMinorMasterSkillLevel(String minorMasterSkillLevel) {
        this.minorMasterSkillLevel = minorMasterSkillLevel;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
    }

    public LocalDate getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(LocalDate positiveTime) {
        this.positiveTime = positiveTime;
    }

    public LocalDate getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(LocalDate acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Integer getPromotedNumber() {
        return promotedNumber;
    }

    public void setPromotedNumber(Integer promotedNumber) {
        this.promotedNumber = promotedNumber;
    }

    public Boolean getSubject() {
        return subject;
    }

    public void setSubject(Boolean subject) {
        this.subject = subject;
    }

    public Integer getOrderEvent() {
        return orderEvent;
    }

    public void setOrderEvent(Integer orderEvent) {
        this.orderEvent = orderEvent;
    }
}