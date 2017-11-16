package com.bjike.goddess.archive.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 人员简历信息
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-14 11:30 ]
 * @Description: [ 人员简历信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "archive_resumeinfo")
public class ResumeInfo extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 员工编号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String serialNumber;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String project;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 学历
     */
    @Column(name = "education", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '学历'")
    private String education;

    /**
     * 专业
     */
    @Column(name = "major", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String major;

    /**
     * 毕业学校
     */
    @Column(name = "school", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '毕业学校'")
    private String school;

    /**
     * 毕业时间
     */
    @Column(name = "graduate", nullable = false, columnDefinition = "DATE   COMMENT '毕业时间'")
    private LocalDate graduate;

    /**
     * 入职时间
     */
    @Column(name = "entryTime", nullable = false, columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate entryTime;

    /**
     * 兴趣爱好
     */
    @Column(name = "hobby", nullable = false, columnDefinition = "TEXT   COMMENT '兴趣爱好'")
    private String hobby;

    /**
     * 工作经历描述
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT   COMMENT '工作经历描述'")
    private String description;

    /**
     * 行业
     */
    @Column(name = "industry", nullable = false, columnDefinition = "TEXT   COMMENT '行业'")
    private String industry;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private Status status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getGraduate() {
        return graduate;
    }

    public void setGraduate(LocalDate graduate) {
        this.graduate = graduate;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}