package com.bjike.goddess.regularization.vo;

/**
 * 员工转正表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegularizationVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String profession;

    /**
     * 入职时间
     */
    private String hiredate;

    /**
     * 员工编号
     */
    private String empNo;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String post;

    /**
     * 试用期月份
     */
    private String probationPeriod;

    /**
     * 入职以来月平考勤扣分量
     */
    private String monthAverageDeductMark;

    /**
     * 奖励分数
     */
    private Integer awardSoce;

    /**
     * 处罚分数
     */
    private Integer penaltyScore;

    /**
     * 工作自述
     */
    private String jobReadme;

    /**
     * 工作总结
     */
    private String jobSummary;

    /**
     * 条件相关说明
     */
    private String criteriaAttention;

    /**
     * 转正条件确认
     */
    private Boolean posCriteriaConfirmed;

    /**
     * 管理层评分平均分
     */
    private Double managementAverage;

    /**
     * 决策层
     */
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
    private String budgetModule;

    /**
     * 预算模块转正意见
     */
    private String budgetPositiveComment;

    /**
     * 总经办
     */
    private String gmOffice;

    /**
     * 转正类型
     */
    private String positiveType;

    /**
     * 转正时间
     */
    private String positiveDate;

    /**
     * 转正面谈信息
     */
    private String posFacialInfor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
}