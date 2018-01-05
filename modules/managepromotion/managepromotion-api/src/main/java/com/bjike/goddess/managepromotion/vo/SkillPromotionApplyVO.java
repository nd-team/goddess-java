package com.bjike.goddess.managepromotion.vo;

import com.bjike.goddess.managepromotion.enums.AuditStatus;
import com.bjike.goddess.managepromotion.enums.DealStatus;

/**
 * 技能晋升申请表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillPromotionApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 姓名
     */
    private String name;

    /**
     * 技能定位-专业
     */
    private String major;

    /**
     * 主项/小项
     */
    private Boolean subject;

    /**
     * 申请考试/晋升时间
     */
    private String applyTest;

    /**
     * 转正技能等级
     */
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    private String positiveTime;

    /**
     * 当前技能等级
     */
    private String currentSkillLevel;

    /**
     * 获取时间
     */
    private String acquisitionTime;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

    /**
     * 本次晋升等级
     */
    private String promotionLevel;

    /**
     * 是否达到课时完成量
     */
    private Boolean classCompletion;

    /**
     * 技能水平等级考试成绩
     */
    private Integer skillLevelScore;
    /**
     * 阶段
     */
    private Integer phase;

    /**
     * 模块负责人审核意见
     */
    private String headOpinion;
    /**
     * 运营商务部预算模块审核意见
     */
    private String budgetOpinion;

    /**
     * 项目经理审核意见
     */
    private String projectManagerOpinion;

    /**
     * 综合资源部规划模块审核意见
     */
    private String planOpinion;
    /**
     * 总经办审核意见
     */
    private String managerOpinion;
    /**
     * 处理状态
     */
    private DealStatus dealStatus;
    /**
     * 晋升时间
     */
    private String promotionTime;
    /**
     * 是否通过
     */
    private Boolean pass;
    /**
     * 是否通报结果
     */
    private Boolean result;
    /**
     * 审核状态
     */
    private AuditStatus auditStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getPromotionTime() {
        return promotionTime;
    }

    public void setPromotionTime(String promotionTime) {
        this.promotionTime = promotionTime;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
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

    public Boolean getSubject() {
        return subject;
    }

    public void setSubject(Boolean subject) {
        this.subject = subject;
    }

    public String getApplyTest() {
        return applyTest;
    }

    public void setApplyTest(String applyTest) {
        this.applyTest = applyTest;
    }

    public String getTransferSkillLevel() {
        return transferSkillLevel;
    }

    public void setTransferSkillLevel(String transferSkillLevel) {
        this.transferSkillLevel = transferSkillLevel;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
    }

    public String getCurrentSkillLevel() {
        return currentSkillLevel;
    }

    public void setCurrentSkillLevel(String currentSkillLevel) {
        this.currentSkillLevel = currentSkillLevel;
    }

    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Integer getPromotedNumber() {
        return promotedNumber;
    }

    public void setPromotedNumber(Integer promotedNumber) {
        this.promotedNumber = promotedNumber;
    }

    public String getPromotionLevel() {
        return promotionLevel;
    }

    public void setPromotionLevel(String promotionLevel) {
        this.promotionLevel = promotionLevel;
    }

    public Boolean getClassCompletion() {
        return classCompletion;
    }

    public void setClassCompletion(Boolean classCompletion) {
        this.classCompletion = classCompletion;
    }

    public Integer getSkillLevelScore() {
        return skillLevelScore;
    }

    public void setSkillLevelScore(Integer skillLevelScore) {
        this.skillLevelScore = skillLevelScore;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }


    public String getHeadOpinion() {
        return headOpinion;
    }

    public void setHeadOpinion(String headOpinion) {
        this.headOpinion = headOpinion;
    }


    public String getBudgetOpinion() {
        return budgetOpinion;
    }

    public void setBudgetOpinion(String budgetOpinion) {
        this.budgetOpinion = budgetOpinion;
    }

    public String getProjectManagerOpinion() {
        return projectManagerOpinion;
    }

    public void setProjectManagerOpinion(String projectManagerOpinion) {
        this.projectManagerOpinion = projectManagerOpinion;
    }


    public String getPlanOpinion() {
        return planOpinion;
    }

    public void setPlanOpinion(String planOpinion) {
        this.planOpinion = planOpinion;
    }


    public String getManagerOpinion() {
        return managerOpinion;
    }

    public void setManagerOpinion(String managerOpinion) {
        this.managerOpinion = managerOpinion;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }
}