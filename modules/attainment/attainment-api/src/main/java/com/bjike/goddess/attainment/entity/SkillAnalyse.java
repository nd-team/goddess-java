package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 技能分析表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_skill_analyse")
public class SkillAnalyse extends BaseEntity {

    /**
     * 员工
     */
    @Column(name = "user", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工'")
    private String user;

    /**
     * 调研编号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研编号'")
    private String serialNumber;

    /**
     * 调研类型
     */
    @Column(name = "surveyType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研类型'")
    private String surveyType;

    /**
     * 技能名称
     */
    @Column(name = "skill", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能名称'")
    private String skill;

    /**
     * 技能水平
     */
    @Column(name = "skillLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能水平'")
    private String skillLevel;

    /**
     * 填写人
     */
    @Column(name = "writer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String writer;

    /**
     * 填写时间
     */
    @Column(name = "writerTime", nullable = false, columnDefinition = "DATETIME   COMMENT '填写时间'")
    private LocalDateTime writerTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(LocalDateTime writerTime) {
        this.writerTime = writerTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}