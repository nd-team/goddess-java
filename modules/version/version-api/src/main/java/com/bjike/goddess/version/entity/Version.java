package com.bjike.goddess.version.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 版本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "version_version")
public class Version extends BaseEntity {
    /**
     * 制度名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '制度名'")
    private String name;

    /**
     * 功能目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能目的'")
    private String purpose;

    /**
     * 功能特色
     */
    @Column(name = "feature", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能特色'")
    private String feature;

    /**
     * 版本号
     */
    @Column(name = "version", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '版本号'")
    private String version;

    /**
     * 采纳意见
     */
    @Column(name = "advice", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '采纳意见'")
    private String advice;

    /**
     * 制定/修订人
     */
    @Column(name = "designer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '制定/修订人'")
    private String designer;

    /**
     * 责任审核
     */
    @Column(name = "audit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任审核'")
    private String audit;

    /**
     * 制定/修订背景
     */
    @Column(name = "background", nullable = false, columnDefinition = "TEXT   COMMENT '制定/修订背景'")
    private String background;

    /**
     * 制作/修订内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "TEXT  COMMENT '制作/修订内容'")
    private String content;

    /**
     * 完成时间
     */
    @Column(name = "finishTime", nullable = false, columnDefinition = "DATETIME   COMMENT '完成时间'")
    private LocalDateTime finishTime;

    /**
     * 是否已开发
     */
    @Column(name = "is_haveDevelop", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否已开发'")
    private Boolean haveDevelop;

    /**
     * 开发人员
     */
    @Column(name = "developer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开发人员'")
    private String developer;

    /**
     * 是否通过体验
     */
    @Column(name = "is_pass", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否通过体验'")
    private Boolean pass;

    /**
     * 体验时间
     */
    @Column(name = "experienceTime", nullable = false, columnDefinition = "DATETIME   COMMENT '体验时间'")
    private LocalDateTime experienceTime;

    /**
     * 体验人员
     */
    @Column(name = "experiencer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体验人员'")
    private String experiencer;

    /**
     * 问题
     */
    @Column(name = "question",columnDefinition = "TEXT   COMMENT '问题'")
    private String question;

    /**
     * 修改建议
     */
    @Column(name = "editAdvice",  columnDefinition = "TEXT  COMMENT '修改建议'")
    private String editAdvice;

    /**
     * 备注
     */
    @Column(name = "rate", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String rate;

    /**
     * 模块名
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块名'")
    private String module;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Help> helps = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Help> getHelps() {
        return helps;
    }

    public void setHelps(List<Help> helps) {
        this.helps = helps;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Boolean getHaveDevelop() {
        return haveDevelop;
    }

    public void setHaveDevelop(Boolean haveDevelop) {
        this.haveDevelop = haveDevelop;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public LocalDateTime getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(LocalDateTime experienceTime) {
        this.experienceTime = experienceTime;
    }

    public String getExperiencer() {
        return experiencer;
    }

    public void setExperiencer(String experiencer) {
        this.experiencer = experiencer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getEditAdvice() {
        return editAdvice;
    }

    public void setEditAdvice(String editAdvice) {
        this.editAdvice = editAdvice;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}