package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.managepromotion.enums.AuditStatus;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 技能晋升申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillPromotionApplyTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 部门/项目组
     */
    @NotBlank(message = "部门/项目组不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 技能定位-专业
     */
    @NotBlank(message = "技能定位-专业不能为空",groups = {ADD.class, EDIT.class})
    private String major;

    /**
     * 主项/小项(是否为主项)
     */
    @NotBlank(message = "主项/小项(是否为主项)不能为空",groups = {ADD.class, EDIT.class})
    private Boolean subject;

    /**
     * 申请考试/晋升时间
     */
    @NotBlank(message = "申请考试/晋升时间不能为空",groups = {ADD.class, EDIT.class})
    private String applyTest;

    /**
     * 转正技能等级
     */
    @NotBlank(message = "转正技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String transferSkillLevel;

    /**
     * 转正时间
     */
    @NotBlank(message = "转正时间不能为空",groups = {ADD.class, EDIT.class})
    private String positiveTime;

    /**
     * 当前技能等级
     */
    @NotBlank(message = "当前技能等级不能为空",groups = {ADD.class, EDIT.class})
    private String currentSkillLevel;

    /**
     * 获取时间
     */
    @NotBlank(message = "获取时间不能为空",groups = {ADD.class, EDIT.class})
    private String acquisitionTime;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

    /**
     * 本次晋升等级
     */
    @NotBlank(message = "本次晋升等级不能为空",groups = {ADD.class, EDIT.class})
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
     * 审核状态（审核中/通过/不通过）
     */
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