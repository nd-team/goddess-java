package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.AuditType;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitDemandBO extends BaseBO {

    /**
     * 招聘地区
     */
    private String recruitArea;

    /**
     * 招聘部门/项目组
     */
    private String recruitGroup;

    /**
     * 招聘岗位
     */
    private String recruitPost;

    /**
     * 计划招聘人数
     */
    private Integer planRecruitNo;

    /**
     * 岗位说明书
     */
    private String postInstruction;

    /**
     * 岗位要求
     */
    private String postRequire;

    /**
     * 到岗时间
     */
    private String dutyTime;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 项目经理审核
     */
    private AuditType pmAudit;

    /**
     * 商务审核
     */
    private AuditType commerceAudit;

    /**
     * 总经办审核
     */
    private AuditType gmAudit;

    /**
     * 负责招聘员工
     */
    private String firmPrincipal;

    /**
     * 已完成招聘人数
     */
    private Integer alreadyRecruitNo;

    /**
     * 是否完成招聘
     */
    private Boolean whetherFinishRecruit;

    /**
     * 备注
     */
    private String comment;

    public RecruitDemandBO(){}

    public String getRecruitArea() {
        return recruitArea;
    }

    public void setRecruitArea(String recruitArea) {
        this.recruitArea = recruitArea;
    }

    public String getRecruitGroup() {
        return recruitGroup;
    }

    public void setRecruitGroup(String recruitGroup) {
        this.recruitGroup = recruitGroup;
    }

    public String getRecruitPost() {
        return recruitPost;
    }

    public void setRecruitPost(String recruitPost) {
        this.recruitPost = recruitPost;
    }

    public Integer getPlanRecruitNo() {
        return planRecruitNo;
    }

    public void setPlanRecruitNo(Integer planRecruitNo) {
        this.planRecruitNo = planRecruitNo;
    }

    public String getPostInstruction() {
        return postInstruction;
    }

    public void setPostInstruction(String postInstruction) {
        this.postInstruction = postInstruction;
    }

    public String getPostRequire() {
        return postRequire;
    }

    public void setPostRequire(String postRequire) {
        this.postRequire = postRequire;
    }

    public String getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(String dutyTime) {
        this.dutyTime = dutyTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public AuditType getPmAudit() {
        return pmAudit;
    }

    public void setPmAudit(AuditType pmAudit) {
        this.pmAudit = pmAudit;
    }

    public AuditType getCommerceAudit() {
        return commerceAudit;
    }

    public void setCommerceAudit(AuditType commerceAudit) {
        this.commerceAudit = commerceAudit;
    }

    public AuditType getGmAudit() {
        return gmAudit;
    }

    public void setGmAudit(AuditType gmAudit) {
        this.gmAudit = gmAudit;
    }

    public String getFirmPrincipal() {
        return firmPrincipal;
    }

    public void setFirmPrincipal(String firmPrincipal) {
        this.firmPrincipal = firmPrincipal;
    }

    public Integer getAlreadyRecruitNo() {
        return alreadyRecruitNo;
    }

    public void setAlreadyRecruitNo(Integer alreadyRecruitNo) {
        this.alreadyRecruitNo = alreadyRecruitNo;
    }

    public Boolean getWhetherFinishRecruit() {
        return whetherFinishRecruit;
    }

    public void setWhetherFinishRecruit(Boolean whetherFinishRecruit) {
        this.whetherFinishRecruit = whetherFinishRecruit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
