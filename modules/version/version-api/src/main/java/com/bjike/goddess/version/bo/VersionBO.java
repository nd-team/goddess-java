package com.bjike.goddess.version.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 版本信息业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VersionBO extends BaseBO {
    /**
     * 制度名
     */
    private String name;
    /**
     * 功能目的
     */
    private String purpose;

    /**
     * 功能特色
     */
    private String feature;

    /**
     * 版本号
     */
    private String version;

    /**
     * 采纳意见
     */
    private String advice;

    /**
     * 制定/修订人
     */
    private String designer;

    /**
     * 责任审核
     */
    private String audit;

    /**
     * 制定/修订背景
     */
    private String background;

    /**
     * 制作/修订内容
     */
    private String content;

    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 是否已开发
     */
    private Boolean haveDevelop;

    /**
     * 开发人员
     */
    private String developer;

    /**
     * 是否通过体验
     */
    private Boolean pass;

    /**
     * 体验时间
     */
    private String experienceTime;

    /**
     * 体验人员
     */
    private String experiencer;

    /**
     * 问题
     */
    private String question;

    /**
     * 修改建议
     */
    private String editAdvice;

    /**
     * 备注
     */
    private String rate;

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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
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

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
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