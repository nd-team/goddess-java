package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 技能评定标准
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillstandard")
public class SkillStandard extends BaseEntity {

    /**
     * 业务类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String type;

    /**
     * 业务方向-科目
     */
    @Column(name = "subject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向-科目'")
    private String subject;

    /**
     * 技能定位-专业(业务范围包含的技能)
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '技能定位-专业(业务范围包含的技能)'")
    private String major;

    /**
     * 工作年限
     */
    @Column(name = "term", columnDefinition = "VARCHAR(255)   COMMENT '工作年限'")
    private String term;

    /**
     * 具体时间
     */
    @Column(name = "specificTime", columnDefinition = "VARCHAR(255)   COMMENT '具体时间'")
    private String specificTime;

    /**
     * 常规技能大纲
     */
    @Column(name = "generalSkillOutline", columnDefinition = "VARCHAR(255)   COMMENT '常规技能大纲'")
    private String generalSkillOutline;

    /**
     * 具体文档
     */
    @Column(name = "specificDocument", columnDefinition = "VARCHAR(255)   COMMENT '具体文档'")
    private String specificDocument;

    /**
     * 要求
     */
    @Column(name = "demand",columnDefinition = "VARCHAR(255)   COMMENT '要求'")
    private String demand;

    /**
     * 收集文档提示
     */
    @Column(name = "collectDocumentTips", columnDefinition = "VARCHAR(255)   COMMENT '收集文档提示'")
    private String collectDocumentTips;

    /**
     * 达到目的
     */
    @Column(name = "achieveGoal", columnDefinition = "VARCHAR(255)   COMMENT '达到目的'")
    private String achieveGoal;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSpecificTime() {
        return specificTime;
    }

    public void setSpecificTime(String specificTime) {
        this.specificTime = specificTime;
    }

    public String getGeneralSkillOutline() {
        return generalSkillOutline;
    }

    public void setGeneralSkillOutline(String generalSkillOutline) {
        this.generalSkillOutline = generalSkillOutline;
    }

    public String getSpecificDocument() {
        return specificDocument;
    }

    public void setSpecificDocument(String specificDocument) {
        this.specificDocument = specificDocument;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getCollectDocumentTips() {
        return collectDocumentTips;
    }

    public void setCollectDocumentTips(String collectDocumentTips) {
        this.collectDocumentTips = collectDocumentTips;
    }

    public String getAchieveGoal() {
        return achieveGoal;
    }

    public void setAchieveGoal(String achieveGoal) {
        this.achieveGoal = achieveGoal;
    }
}