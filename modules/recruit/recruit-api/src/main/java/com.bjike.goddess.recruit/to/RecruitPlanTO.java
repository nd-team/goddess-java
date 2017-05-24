package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitPlanTO extends BaseTO {

    /**
     * 招聘地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招聘地区不能为空")
    private String recruitArea;

    /**
     * 招聘部门/项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招聘部门/项目组不能为空")
    private String recruitDepart;

    /**
     * 招聘日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招聘日期不能为空")
    private String recruitDate;

    /**
     * 招聘岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招聘岗位不能为空")
    private String recruitPost;

    /**
     * 计划招聘人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划招聘人数不能为空")
    private Integer planRecruitNo;

    /**
     * 岗位说明书
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位说明书不能为空")
    private String postInstruction;

    /**
     * 岗位要求
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位要求不能为空")
    private String postRequire;

    /**
     * 到岗时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "到岗时间不能为空")
    private String dutyTime;

    /**
     * 优先级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "优先级不能为空")
    private String priority;

    /**
     * 招聘渠道
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "招聘渠道不能为空")
    private String recruitName;

    /**
     * 计划日简历筛选量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划日简历筛选量不能为空")
    private Integer planDayResumeNo;

    /**
     * 计划日邀约面试量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "计划日邀约面试量不能为空")
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

    public String getRecruitDate() {
        return recruitDate;
    }

    public void setRecruitDate(String recruitDate) {
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
