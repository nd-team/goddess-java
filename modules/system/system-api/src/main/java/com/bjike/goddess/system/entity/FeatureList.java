package com.bjike.goddess.system.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 功能列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "system_featurelist")
public class FeatureList extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 模块
     */
    @Column(name = "module", columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String module;

    /**
     * 小模块编号
     */
    @Column(name = "smallModuleNum", columnDefinition = "VARCHAR(255)   COMMENT '小模块编号'")
    private String smallModuleNum;

    /**
     * 小模块
     */
    @Column(name = "smallModule", columnDefinition = "VARCHAR(255)   COMMENT '小模块'")
    private String smallModule;

    /**
     * 功能名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '功能名称'")
    private String name;

    /**
     * 功能目的
     */
    @Column(name = "purpose", columnDefinition = "VARCHAR(255)   COMMENT '功能目的'")
    private String purpose;

    /**
     * 功能特色
     */
    @Column(name = "feature", columnDefinition = "VARCHAR(255)   COMMENT '功能特色'")
    private String feature;

    /**
     * 版本号
     */
    @Column(name = "version", columnDefinition = "VARCHAR(255)   COMMENT '版本号'")
    private String version;

    /**
     * 采纳意见
     */
    @Column(name = "advice", columnDefinition = "VARCHAR(255)   COMMENT '采纳意见'")
    private String advice;

    /**
     * 制定/修订人
     */
    @Column(name = "designer", columnDefinition = "VARCHAR(255)   COMMENT '制定/修订人'")
    private String designer;

    /**
     * 责任审核
     */
    @Column(name = "audit", columnDefinition = "VARCHAR(255)   COMMENT '责任审核'")
    private String audit;

    /**
     * 制定/修订背景
     */
    @Column(name = "background", columnDefinition = "VARCHAR(255)   COMMENT '制定/修订背景'")
    private String background;

    /**
     * 制作/修订内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '制作/修订内容'")
    private String content;

    /**
     * 完成时间
     */
    @Column(name = "finishTime", columnDefinition = "DATE   COMMENT '完成时间'")
    private LocalDate finishTime;

    /**
     * 是否已开发
     */
    @Column(name = "is_haveDevelop", columnDefinition = "TINYINT(1)  COMMENT '是否已开发'")
    private Boolean haveDevelop;

    /**
     * 开发人员
     */
    @Column(name = "developer", columnDefinition = "VARCHAR(255)   COMMENT '开发人员'")
    private String developer;

    /**
     * 是否通过体验
     */
    @Column(name = "is_pass", columnDefinition = "TINYINT(1)   COMMENT '是否通过体验'")
    private Boolean pass;

    /**
     * 体验时间
     */
    @Column(name = "experienceTime", columnDefinition = "DATE   COMMENT '体验时间'")
    private LocalDate experienceTime;

    /**
     * 体验人员
     */
    @Column(name = "experiencer", columnDefinition = "VARCHAR(255)   COMMENT '体验人员'")
    private String experiencer;

    /**
     * 问题
     */
    @Column(name = "question", columnDefinition = "VARCHAR(255)   COMMENT '问题'")
    private String question;

    /**
     * 修改建议
     */
    @Column(name = "editAdvice", columnDefinition = "VARCHAR(255)   COMMENT '修改建议'")
    private String editAdvice;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSmallModuleNum() {
        return smallModuleNum;
    }

    public void setSmallModuleNum(String smallModuleNum) {
        this.smallModuleNum = smallModuleNum;
    }

    public String getSmallModule() {
        return smallModule;
    }

    public void setSmallModule(String smallModule) {
        this.smallModule = smallModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
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

    public LocalDate getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(LocalDate experienceTime) {
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}