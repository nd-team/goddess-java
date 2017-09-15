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
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;
    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;
    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;


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
     * 岗位层级
     */
    @Column(name = "postHierarchy", columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String postHierarchy;
    /**
     * 入职时间
     */
    @Column(name = "entryTime", columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate entryTime;
    /**
     * 转正时间
     */
    @Column(name = "positiveTime", columnDefinition = "DATE   COMMENT '转正时间'")
    private LocalDate positiveTime;
    /**
     * 在职时间（月）
     */
    @Column(name = "workingTime", columnDefinition = "VARCHAR(255)   COMMENT '在职时间（月）'")
    private String workingTime ;
    /**
     * 技能项
     */
    @Column(name = "skill", columnDefinition = "VARCHAR(255)   COMMENT '技能项'")
    private String skill;

    /**
     * 技能专业
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '技能专业'")
    private String major;

    /**
     * 技能级别
     */
    @Column(name = "grade", columnDefinition = "VARCHAR(255)   COMMENT '技能级别'")
    private String grade;

    /**
     * 转正技能等级
     */
    @Column(name = "transferSkillLevel", columnDefinition = "VARCHAR(255)   COMMENT '转正技能等级'")
    private String transferSkillLevel;

    /**
     * 获取时间
     */
    @Column(name = "acquisitionTime", columnDefinition = "DATE   COMMENT '获取时间'")
    private LocalDate acquisitionTime;

    /**
     * 已晋升次数
     */
    @Column( columnDefinition = "INT(11)   COMMENT '已晋升次数'")
    private Integer promotedNumber;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(LocalDate positiveTime) {
        this.positiveTime = positiveTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
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
}