package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.managepromotion.enums.AuditStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 技能晋升申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillpromotionapply")
public class SkillPromotionApply extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 部门/项目组
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门/项目组'")
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
     * 技能定位-专业
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '技能定位-专业'")
    private String major;

    /**
     * 主项/小项
     */
    @Column(name = "is_subject",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '主项/小项'", insertable = false)
    private Boolean subject;

    /**
     * 申请考试/晋升时间
     */
    @Column(name = "applyTest",columnDefinition = "VARCHAR(255)   COMMENT '申请考试/晋升时间'")
    private String applyTest;

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
     * 当前技能等级
     */
    @Column(name = "currentSkillLevel",  columnDefinition = "VARCHAR(255)   COMMENT '当前技能等级'")
    private String currentSkillLevel;

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
     * 本次晋升等级
     */
    @Column(name = "promotionLevel", columnDefinition = "VARCHAR(255)   COMMENT '本次晋升等级'")
    private String promotionLevel;

    /**
     * 是否达到课时完成量
     */
    @Column(name = "is_classCompletion", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否达到课时完成量'", insertable = false)
    private Boolean classCompletion;

    /**
     * 技能水平等级考试成绩
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '技能水平等级考试成绩'")
    private Integer skillLevelScore;
    /**
     * 阶段
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '阶段'")
    private Integer phase;
    /**
     * 模块负责人审核意见
     */
    @Column(name = "headOpinion", columnDefinition = "VARCHAR(255)   COMMENT '模块负责人审核意见'")
    private String headOpinion;
    /**
     * 运营商务部预算模块审核意见
     */
    @Column(name = "budgetOpinion", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部预算模块审核意见'")
    private String budgetOpinion;

    /**
     * 项目经理审核意见
     */
    @Column(name = "projectManagerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '项目经理审核意见'")
    private String projectManagerOpinion;

    /**
     * 综合资源部规划模块审核意见
     */
    @Column(name = "planOpinion", columnDefinition = "VARCHAR(255)   COMMENT '综合资源部规划模块审核意见'")
    private String planOpinion;

    /**
     * 总经办审核意见
     */
    @Column(name = "managerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办审核意见'")
    private String managerOpinion;
    /**
     * 审核状态（审核中/通过/不通过）
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '审核状态（审核中/通过/不通过）'")
    private String auditStatus;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public LocalDate getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(LocalDate positiveTime) {
        this.positiveTime = positiveTime;
    }

    public String getCurrentSkillLevel() {
        return currentSkillLevel;
    }

    public void setCurrentSkillLevel(String currentSkillLevel) {
        this.currentSkillLevel = currentSkillLevel;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}