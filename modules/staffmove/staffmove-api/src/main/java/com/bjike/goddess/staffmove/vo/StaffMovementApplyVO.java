package com.bjike.goddess.staffmove.vo;

import com.bjike.goddess.staffmove.enums.AuditorType;

/**
 * 人员调动申请表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMovementApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 填写人
     */
    private String fillPerson;

    /**
     * 需求类型
     */
    private String requirementType;

    /**
     * 调动原因
     */
    private String moveReason;

    /**
     * 调动人员
     */
    private String movePeople;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位层级
     */
    private String postHierarchy;

    /**
     * 技能情况
     */
    private String skillIs;

    /**
     * 调往地区
     */
    private String moveArea;

    /**
     * 调往项目
     */
    private String moveProject;

    /**
     * 特殊要求
     */
    private String specialRequirement;

    /**
     * 计划到岗时间
     */
    private String planArriveTime;

    /**
     * 实际到岗时间
     */
    private String actualArriveTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核人
     */
    private AuditorType auditor;

    /**
     * 总经办审核意见
     */
    private String generalAuditOpinion;
    /**
     * 原决策层审核意见
     */
    private String originalAuditOpinion;
    /**
     * 调往决策层审核意见
     */
    private String transferAuditOpinion;
    /**
     * 规划模块审核意见
     */
    private String planAuditOpinion;
    /**
     * 预算审核意见
     */
    private String budgetAuditOpinion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }

    public String getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(String requirementType) {
        this.requirementType = requirementType;
    }

    public String getMoveReason() {
        return moveReason;
    }

    public void setMoveReason(String moveReason) {
        this.moveReason = moveReason;
    }

    public String getMovePeople() {
        return movePeople;
    }

    public void setMovePeople(String movePeople) {
        this.movePeople = movePeople;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public String getSkillIs() {
        return skillIs;
    }

    public void setSkillIs(String skillIs) {
        this.skillIs = skillIs;
    }

    public String getMoveArea() {
        return moveArea;
    }

    public void setMoveArea(String moveArea) {
        this.moveArea = moveArea;
    }

    public String getMoveProject() {
        return moveProject;
    }

    public void setMoveProject(String moveProject) {
        this.moveProject = moveProject;
    }

    public String getSpecialRequirement() {
        return specialRequirement;
    }

    public void setSpecialRequirement(String specialRequirement) {
        this.specialRequirement = specialRequirement;
    }

    public String getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(String planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public String getActualArriveTime() {
        return actualArriveTime;
    }

    public void setActualArriveTime(String actualArriveTime) {
        this.actualArriveTime = actualArriveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AuditorType getAuditor() {
        return auditor;
    }

    public void setAuditor(AuditorType auditor) {
        this.auditor = auditor;
    }


    public String getGeneralAuditOpinion() {
        return generalAuditOpinion;
    }

    public void setGeneralAuditOpinion(String generalAuditOpinion) {
        this.generalAuditOpinion = generalAuditOpinion;
    }

    public String getOriginalAuditOpinion() {
        return originalAuditOpinion;
    }

    public void setOriginalAuditOpinion(String originalAuditOpinion) {
        this.originalAuditOpinion = originalAuditOpinion;
    }

    public String getTransferAuditOpinion() {
        return transferAuditOpinion;
    }

    public void setTransferAuditOpinion(String transferAuditOpinion) {
        this.transferAuditOpinion = transferAuditOpinion;
    }

    public String getPlanAuditOpinion() {
        return planAuditOpinion;
    }

    public void setPlanAuditOpinion(String planAuditOpinion) {
        this.planAuditOpinion = planAuditOpinion;
    }

    public String getBudgetAuditOpinion() {
        return budgetAuditOpinion;
    }

    public void setBudgetAuditOpinion(String budgetAuditOpinion) {
        this.budgetAuditOpinion = budgetAuditOpinion;
    }
}