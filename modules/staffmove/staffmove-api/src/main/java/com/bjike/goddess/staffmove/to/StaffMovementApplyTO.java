package com.bjike.goddess.staffmove.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 人员调动申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMovementApplyTO extends BaseTO {
    public interface planAudit{}
    public interface budgetAudit{}
    public interface originalAudit{}
    public interface transferAudit{}
    public interface generalAudit{}

    /**
     * 填写人
     */
    @NotBlank(message = "填写人不能为空", groups = {ADD.class, EDIT.class})
    private String fillPerson;

    /**
     * 需求类型
     */
    @NotBlank(message = "需求类型不能为空", groups = {ADD.class, EDIT.class})
    private String requirementType;

    /**
     * 调动原因
     */
    @NotBlank(message = "调动原因不能为空", groups = {ADD.class, EDIT.class})
    private String moveReason;

    /**
     * 调动人员
     */
    @NotBlank(message = "调动人员不能为空", groups = {ADD.class, EDIT.class})
    private String movePeople;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目
     */
    @NotBlank(message = "项目不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位层级
     */
    @NotBlank(message = "岗位层级不能为空", groups = {ADD.class, EDIT.class})
    private String postHierarchy;

    /**
     * 技能情况
     */
    @NotBlank(message = "技能情况不能为空", groups = {ADD.class, EDIT.class})
    private String skillIs;

    /**
     * 调往地区
     */
    @NotBlank(message = "调往地区不能为空", groups = {ADD.class, EDIT.class})
    private String moveArea;

    /**
     * 调往项目
     */
    @NotBlank(message = "调往项目不能为空", groups = {ADD.class, EDIT.class})
    private String moveProject;

    /**
     * 特殊要求
     */
    @NotBlank(message = "特殊要求不能为空", groups = {ADD.class, EDIT.class})
    private String specialRequirement;

    /**
     * 计划到岗时间
     */
    @NotBlank(message = "计划到岗时间不能为空", groups = {ADD.class, EDIT.class})
    private String planArriveTime;

    /**
     * 实际到岗时间
     */
    @NotBlank(message = "实际到岗时间时间不能为空", groups = {ADD.class, EDIT.class})
    private String actualArriveTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 规划模块审核人
     */
    @NotBlank(message = "规划模块审核人不能为空",groups = {StaffMovementApplyTO.planAudit.class})
    private String planAuditor;
    /**
     * 规划模块审核意见
     */
    @NotBlank(message = "规划模块审核意见不能为空",groups = {StaffMovementApplyTO.planAudit.class})
    private String planAuditOpinion;
    /**
     * 预算模块审核人
     */
    @NotBlank(message = "预算模块审核人不能为空",groups = {StaffMovementApplyTO.budgetAudit.class})
    private String budgetAuditor;
    /**
     * 预算审核意见
     */
    @NotBlank(message = "预算审核意见不能为空",groups = {StaffMovementApplyTO.budgetAudit.class})
    private String budgetAuditOpinion;
    /**
     * 原决策层审核人
     */
    @NotBlank(message = "原决策层审核人不能为空",groups = {StaffMovementApplyTO.originalAudit.class})
    private String originalAuditor;
    /**
     * 原决策层审核意见
     */
    @NotBlank(message = "原决策层审核意见不能为空",groups = {StaffMovementApplyTO.originalAudit.class})
    private String originalAuditOpinion;
    /**
     * 原决策层是否同意调动
     */
    @NotBlank(message = "原决策层是否同意调动不能为空",groups = {StaffMovementApplyTO.transferAudit.class})
    private String originalMove;
    /**
     * 调往决策层审核人
     */
    @NotBlank(message = "调往决策层审核人不能为空",groups = {StaffMovementApplyTO.transferAudit.class})
    private String transferAuditor;
    /**
     * 调往决策层审核意见
     */
    @NotBlank(message = "调往决策层审核意见不能为空",groups = {StaffMovementApplyTO.transferAudit.class})
    private String transferAuditOpinion;
    /**
     * 调往决策层是否同意调动
     */
    @NotBlank(message = "调往决策层是否同意调动不能为空",groups = {StaffMovementApplyTO.transferAudit.class})
    private String transferMove;
    /**
     * 总经办审核人
     */
    @NotBlank(message = "总经办审核人不能为空",groups = {StaffMovementApplyTO.generalAudit.class})
    private String generalAuditor;

    /**
     * 总经办审核意见
     */
    @NotBlank(message = "总经办审核意见不能为空",groups = {StaffMovementApplyTO.generalAudit.class})
    private String generalAuditOpinion;

    public String getOriginalMove() {
        return originalMove;
    }

    public void setOriginalMove(String originalMove) {
        this.originalMove = originalMove;
    }

    public String getTransferMove() {
        return transferMove;
    }

    public void setTransferMove(String transferMove) {
        this.transferMove = transferMove;
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

    public String getPlanAuditor() {
        return planAuditor;
    }

    public void setPlanAuditor(String planAuditor) {
        this.planAuditor = planAuditor;
    }

    public String getBudgetAuditor() {
        return budgetAuditor;
    }

    public void setBudgetAuditor(String budgetAuditor) {
        this.budgetAuditor = budgetAuditor;
    }

    public String getOriginalAuditor() {
        return originalAuditor;
    }

    public void setOriginalAuditor(String originalAuditor) {
        this.originalAuditor = originalAuditor;
    }

    public String getTransferAuditor() {
        return transferAuditor;
    }

    public void setTransferAuditor(String transferAuditor) {
        this.transferAuditor = transferAuditor;
    }

    public String getGeneralAuditor() {
        return generalAuditor;
    }

    public void setGeneralAuditor(String generalAuditor) {
        this.generalAuditor = generalAuditor;
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