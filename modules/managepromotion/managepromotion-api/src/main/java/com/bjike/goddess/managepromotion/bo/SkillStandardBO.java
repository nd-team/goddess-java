package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 技能评定标准业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillStandardBO extends BaseBO {

    /**
     * 业务类型
     */
    private String type;

    /**
     * 业务方向-科目
     */
    private String subject;

    /**
     * 技能定位-专业(业务范围包含的技能)
     */
    private String major;

    /**
     * 工作年限
     */
    private String term;

    /**
     * 具体时间
     */
    private String specificTime;

    /**
     * 常规技能大纲
     */
    private String generalSkillOutline;

    /**
     * 具体文档
     */
    private String specificDocument;

    /**
     * 要求
     */
    private String demand;

    /**
     * 收集文档提示
     */
    private String collectDocumentTips;

    /**
     * 达到目的
     */
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