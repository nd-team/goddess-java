package com.bjike.goddess.regularization.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.regularization.type.SexType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 员工转正
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegularizationTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "姓名不能为空")
    private String name;

    /**
     * 性别
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "性别不能为空")
    private SexType gender;

    /**
     * 学历
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "学历不能为空")
    private String education;

    /**
     * 专业
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "专业不能为空")
    private String profession;

    /**
     * 入职日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "入职时间不能为空")
    private String hiredate;

    /**
     * 员工编号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工编号不能为空")
    private String empNo;

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String projectGroup;

    /**
     * 岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String post;
    /**
     * 岗位层级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String postHierarchy;
    /**
     * 转正申请日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String regularDate;
    /**
     * 工作年限
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private Double workingYear;
    /**
     * 确定事项是否确认
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private Boolean confirmEvent;
    /**
     * 确认人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "岗位不能为空")
    private String confirmPeople;
    /**
     * 试用期月份
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "试用期月份不能为空")
    private String probationPeriod;

    /**
     * 入职以来月平考勤扣分量
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "入职以来月平考勤扣分量不能为空")
    private String monthAverageDeductMark;

    /**
     * 奖励分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "奖励分数不能为空")
    private Integer awardSoce;

    /**
     * 处罚分数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "处罚分数不能为空")
    private Integer penaltyScore;

    /**
     * 工作自述
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "工作自述不能为空")
    private String jobReadme;

    /**
     * 工作总结
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "工作总结不能为空")
    private String jobSummary;

    /**
     * 条件相关说明
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "条件相关说明不能为空")
    private String criteriaAttention;

    /**
     * 转正条件确认
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "转正条件确定不能为空")
    private Boolean posCriteriaConfirmed;

    /**
     * 转正面谈信息
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "转正面谈信息不能为空")
    private String posFacialInfor;

    /**
     * 管理层评分平均分
     */
    private Double managementAverage;

    /**
     * 决策层
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "决策层不能为空")
    private String decisionLevel;

    /**
     * 决策层评价
     */
    private String decisionLevelEvaluate;

    /**
     * 决策层评分等级
     */
    private String decisionLevelRank;

    /**
     * 决策层具体评分
     */
    private Integer decisionLevelScore;

    /**
     * 规划模块
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "规划模块不能为空")
    private String planModule;

    /**
     * 转正后岗位
     */
    private String afterPost;

    /**
     * 转正技能定级
     */
    private String afterSkillRank;

    /**
     * 规划模块转正意见
     */
    private String planPositiveComment;

    /**
     * 预算模块
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "预算模块不能为空")
    private String budgetModule;

    /**
     * 预算模块转正意见
     */
    private String budgetPositiveComment;

    /**
     * 总经办
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "总经办不能为空")
    private String gmOffice;

    /**
     * 转正类型
     */
    private String positiveType;

    /**
     * 总经办评价
     */
    private String zjbAppraise;

    /**
     * 转正时间
     */
    private String positiveDate;

    /**
     * 管理层意见
     */
    private String opinion;

    /**
     * 评分等级
     */
    private String scoreGrade;

    /**
     * 具体分数
     */
    private Integer specificScore;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexType getGender() {
        return gender;
    }

    public void setGender(SexType gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getProbationPeriod() {
        return probationPeriod;
    }

    public void setProbationPeriod(String probationPeriod) {
        this.probationPeriod = probationPeriod;
    }

    public String getMonthAverageDeductMark() {
        return monthAverageDeductMark;
    }

    public void setMonthAverageDeductMark(String monthAverageDeductMark) {
        this.monthAverageDeductMark = monthAverageDeductMark;
    }

    public Integer getAwardSoce() {
        return awardSoce;
    }

    public void setAwardSoce(Integer awardSoce) {
        this.awardSoce = awardSoce;
    }

    public Integer getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(Integer penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public String getJobReadme() {
        return jobReadme;
    }

    public void setJobReadme(String jobReadme) {
        this.jobReadme = jobReadme;
    }

    public String getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(String jobSummary) {
        this.jobSummary = jobSummary;
    }

    public String getCriteriaAttention() {
        return criteriaAttention;
    }

    public void setCriteriaAttention(String criteriaAttention) {
        this.criteriaAttention = criteriaAttention;
    }

    public Boolean getPosCriteriaConfirmed() {
        return posCriteriaConfirmed;
    }

    public void setPosCriteriaConfirmed(Boolean posCriteriaConfirmed) {
        this.posCriteriaConfirmed = posCriteriaConfirmed;
    }

    public Double getManagementAverage() {
        return managementAverage;
    }

    public void setManagementAverage(Double managementAverage) {
        this.managementAverage = managementAverage;
    }

    public String getDecisionLevel() {
        return decisionLevel;
    }

    public void setDecisionLevel(String decisionLevel) {
        this.decisionLevel = decisionLevel;
    }

    public String getDecisionLevelEvaluate() {
        return decisionLevelEvaluate;
    }

    public void setDecisionLevelEvaluate(String decisionLevelEvaluate) {
        this.decisionLevelEvaluate = decisionLevelEvaluate;
    }

    public String getDecisionLevelRank() {
        return decisionLevelRank;
    }

    public void setDecisionLevelRank(String decisionLevelRank) {
        this.decisionLevelRank = decisionLevelRank;
    }

    public Integer getDecisionLevelScore() {
        return decisionLevelScore;
    }

    public void setDecisionLevelScore(Integer decisionLevelScore) {
        this.decisionLevelScore = decisionLevelScore;
    }

    public String getPlanModule() {
        return planModule;
    }

    public void setPlanModule(String planModule) {
        this.planModule = planModule;
    }

    public String getAfterPost() {
        return afterPost;
    }

    public void setAfterPost(String afterPost) {
        this.afterPost = afterPost;
    }

    public String getAfterSkillRank() {
        return afterSkillRank;
    }

    public void setAfterSkillRank(String afterSkillRank) {
        this.afterSkillRank = afterSkillRank;
    }

    public String getPlanPositiveComment() {
        return planPositiveComment;
    }

    public void setPlanPositiveComment(String planPositiveComment) {
        this.planPositiveComment = planPositiveComment;
    }

    public String getBudgetModule() {
        return budgetModule;
    }

    public void setBudgetModule(String budgetModule) {
        this.budgetModule = budgetModule;
    }

    public String getBudgetPositiveComment() {
        return budgetPositiveComment;
    }

    public void setBudgetPositiveComment(String budgetPositiveComment) {
        this.budgetPositiveComment = budgetPositiveComment;
    }

    public String getGmOffice() {
        return gmOffice;
    }

    public void setGmOffice(String gmOffice) {
        this.gmOffice = gmOffice;
    }

    public String getPositiveType() {
        return positiveType;
    }

    public void setPositiveType(String positiveType) {
        this.positiveType = positiveType;
    }

    public String getZjbAppraise() {
        return zjbAppraise;
    }

    public void setZjbAppraise(String zjbAppraise) {
        this.zjbAppraise = zjbAppraise;
    }

    public String getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(String positiveDate) {
        this.positiveDate = positiveDate;
    }

    public String getPosFacialInfor() {
        return posFacialInfor;
    }

    public void setPosFacialInfor(String posFacialInfor) {
        this.posFacialInfor = posFacialInfor;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public Integer getSpecificScore() {
        return specificScore;
    }

    public void setSpecificScore(Integer specificScore) {
        this.specificScore = specificScore;
    }

    public String getPostHierarchy() {
        return postHierarchy;
    }

    public void setPostHierarchy(String postHierarchy) {
        this.postHierarchy = postHierarchy;
    }

    public String getRegularDate() {
        return regularDate;
    }

    public void setRegularDate(String regularDate) {
        this.regularDate = regularDate;
    }

    public Double getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(Double workingYear) {
        this.workingYear = workingYear;
    }

    public Boolean getConfirmEvent() {
        return confirmEvent;
    }

    public void setConfirmEvent(Boolean confirmEvent) {
        this.confirmEvent = confirmEvent;
    }

    public String getConfirmPeople() {
        return confirmPeople;
    }

    public void setConfirmPeople(String confirmPeople) {
        this.confirmPeople = confirmPeople;
    }
}