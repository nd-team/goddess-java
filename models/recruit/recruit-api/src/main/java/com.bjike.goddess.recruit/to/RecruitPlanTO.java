package com.bjike.goddess.recruit.to;

import java.time.LocalDate;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitPlanTO {

    /**
     * 招聘地区
     */
    private String recruitArea;

    /**
     * 招聘部门/项目组
     */
    private String recruitDepart;

    /**
     * 招聘日期
     */
    private LocalDate recruitDate;

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
    private LocalDate dutyTime;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 招聘渠道
     */
    private String recruitName;

    /**
     * 计划日简历筛选量
     */
    private Integer planDayResumeNo;

    /**
     * 计划日邀约面试量
     */
    private Integer planDayInviteNo;

    /**
     * 计划日面试量
     */
    private Integer planDayInterviewNo;

    /**
     * 计划日成功通过面试量
     */
    private Integer planDayPassInterviewNo;

    /**
     * 负责招聘员工
     */
    private String firmPrincipal;

    /**
     * 备注
     */
    private String comment;

    public String getRecruitArea() {
        return recruitArea;
    }

    public void setRecruitArea(String recruitArea) {
        this.recruitArea = recruitArea;
    }

    public String getRecruitDepart() {
        return recruitDepart;
    }

    public void setRecruitDepart(String recruitDepart) {
        this.recruitDepart = recruitDepart;
    }

    public LocalDate getRecruitDate() {
        return recruitDate;
    }

    public void setRecruitDate(LocalDate recruitDate) {
        this.recruitDate = recruitDate;
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

    public LocalDate getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(LocalDate dutyTime) {
        this.dutyTime = dutyTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecruitName() {
        return recruitName;
    }

    public void setRecruitName(String recruitName) {
        this.recruitName = recruitName;
    }

    public Integer getPlanDayResumeNo() {
        return planDayResumeNo;
    }

    public void setPlanDayResumeNo(Integer planDayResumeNo) {
        this.planDayResumeNo = planDayResumeNo;
    }

    public Integer getPlanDayInviteNo() {
        return planDayInviteNo;
    }

    public void setPlanDayInviteNo(Integer planDayInviteNo) {
        this.planDayInviteNo = planDayInviteNo;
    }

    public Integer getPlanDayInterviewNo() {
        return planDayInterviewNo;
    }

    public void setPlanDayInterviewNo(Integer planDayInterviewNo) {
        this.planDayInterviewNo = planDayInterviewNo;
    }

    public Integer getPlanDayPassInterviewNo() {
        return planDayPassInterviewNo;
    }

    public void setPlanDayPassInterviewNo(Integer planDayPassInterviewNo) {
        this.planDayPassInterviewNo = planDayPassInterviewNo;
    }

    public String getFirmPrincipal() {
        return firmPrincipal;
    }

    public void setFirmPrincipal(String firmPrincipal) {
        this.firmPrincipal = firmPrincipal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
