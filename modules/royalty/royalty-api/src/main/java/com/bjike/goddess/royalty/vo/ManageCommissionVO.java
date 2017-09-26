package com.bjike.goddess.royalty.vo;

/**
 * Created by ike on 17-7-17.
 */
public class ManageCommissionVO {
    /**
     * 对赌开始时间
     */
    private String betTime;

    /**
     * 地区
     */
    private String area;
    /**
     * 项目组/部门
     */
    private String projectGroup;

    /**
     * 内部项目名称
     */
    private String projectName;

    /**
     * 分值（利润额）
     */
    private Integer scoreProfit;
    /**
     * 计划分值（利润额）
     */
    private Integer planProfit;
    /**
     * 实际分值（利润额）
     */
    private Integer practiceProfit;
    /**
     * 体系
     */
    private String system;

    /**
     * 目标-部门分配基础权重（%）
     */
    private Double systemBaseWeight;

    /**
     * 目标-部门分配对赌权重（%）
     */
    private Double systemBetWeight;
    /**
     * 指标编号
     */
    private String systemIndexNum;

    /**
     * 指标名称
     */
    private String systemIndexName;

    /**
     * 部门对赌承诺-确认目标值
     */
    private String systemConfirmTargetValue;

    /**
     * 项目对赌是否达标
     */
    private Boolean systemStandard;

    /**
     * 目标基础得分
     */
    private Double systemBasesScore;
    /**
     * 计划基础得分
     */
    private Double systemPlanBasesScore;
    /**
     * 实际基础得分
     */
    private Double systemPracticeBasesScore;
    /**
     * 目标对赌得分
     */
    private Double systemBetScore;
    /**
     * 计划对赌得分
     */
    private Double systemPlanBetScore;
    /**
     * 实际对赌得分
     */
    private Double systemPracticeBetScore;

    /**
     * 未达标分配体系
     */
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    private Double systemUnmetAllocation;
    /**
     * 目标制约得分
     */
    private Double systemRestrictScore;
    /**
     * 计划制约得分
     */
    private Double systemPlanRestrictScore;

    /**
     * 实际制约得分
     */
    private Double systemPracticeRestrictScore;

    /**
     * 目标体系总得分
     */
    private Double systemTotalScore;
    /**
     * 计划体系总得分
     */
    private Double systemPlanTotalScore;
    /**
     * 实际体系总得分
     */
    private Double systemPracticeTotalScore;
    /**
     * 部门
     */
    private String department;

    /**
     * 目标-部门分配基础权重（%）
     */
    private Double departmentBaseWeight;
    /**
     * 目标-部门分配对赌权重（%）
     */
    private Double departmentBetWeight;
    /**
     * 指标编号
     */
    private String departmentIndexNum;

    /**
     * 指标名称
     */
    private String departmentIndexName;

    /**
     * 部门对赌承诺-确认目标值
     */
    private String departmentConfirmTargetValue;


    /**
     * 项目对赌是否达标
     */
    private Boolean departmentStandard;
    /**
     * 目标基础得分
     */
    private Double departmentBasesScore;
    /**
     * 计划基础得分
     */
    private Double departmentPlanBasesScore;
    /**
     * 实际基础得分
     */
    private Double departmentPracticeBasesScore;
    /**
     * 目标对赌得分
     */
    private Double departmentBetScore;
    /**
     * 计划对赌得分
     */
    private Double departmentPlanBetScore;
    /**
     * 实际对赌得分
     */
    private Double departmentPracticeBetScore;

    /**
     * 未达标分配部门
     */
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    private Double departmentUnmetAllocation;
    /**
     * 目标制约得分
     */
    private Double departmentRestrictScore;
    /**
     * 计划制约得分
     */
    private Double departmentPlanRestrictScore;

    /**
     * 实际制约得分
     */
    private Double departmentPracticeRestrictScore;

    /**
     * 目标体系总得分
     */
    private Double departmentTotalScore;
    /**
     * 计划体系总得分
     */
    private Double departmentPlanTotalScore;
    /**
     * 实际体系总得分
     */
    private Double departmentPracticeTotalScore;
    /**
     * 岗位
     */
    private String jobs;
    /**
     * 人数
     */
    private Integer people;

    /**
     * 分配基础权重（%）
     */
    private Double jobsBaseWeight;
    /**
     * 分配对赌权重（%）
     */
    private Double jobsBetWeight;

    /**
     * 指标编号
     */
    private String jobsIndexNum;

    /**
     * 指标名称
     */
    private String jobsIndexName;

    /**
     * 对赌承诺-确认目标值
     */
    private String jobsConfirmTargetValue;

    /**
     * 是否达标
     */
    private Boolean jobsStandard;
    /**
     * 未达标分配岗位
     */
    private String unmetAllocationJobs;

    /**
     * 未达标分配
     */
    private Double jobsUnmetAllocation;
    /**
     * 目标基础得分
     */
    private Double jobsBasesScore;
    /**
     * 计划基础得分
     */
    private Double jobsPlanBasesScore;
    /**
     * 实际基础得分
     */
    private Double jobsPracticeBasesScore;
    /**
     * 目标对赌得分
     */
    private Double jobsBetScore;
    /**
     * 计划对赌得分
     */
    private Double jobsPlanBetScore;
    /**
     * 实际对赌得分
     */
    private Double jobsPracticeBetScore;

    /**
     * 目标制约得分
     */
    private Double jobsRestrictScore;

    /**
     * 计划制约得分
     */
    private Double jobsPlanRestrictScore;

    /**
     * 实际制约得分
     */
    private Double jobsPracticeRestrictScore;

    /**
     * 目标总得分
     */
    private Double totalScore;
    /**
     * 计划总得分
     */
    private Double planTotalScore;
    /**
     * 实际总得分
     */
    private Double practiceTotalScore;

    public String getBetTime() {
        return betTime;
    }

    public void setBetTime(String betTime) {
        this.betTime = betTime;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScoreProfit() {
        return scoreProfit;
    }

    public void setScoreProfit(Integer scoreProfit) {
        this.scoreProfit = scoreProfit;
    }

    public Integer getPlanProfit() {
        return planProfit;
    }

    public void setPlanProfit(Integer planProfit) {
        this.planProfit = planProfit;
    }

    public Integer getPracticeProfit() {
        return practiceProfit;
    }

    public void setPracticeProfit(Integer practiceProfit) {
        this.practiceProfit = practiceProfit;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Double getSystemBaseWeight() {
        return systemBaseWeight;
    }

    public void setSystemBaseWeight(Double systemBaseWeight) {
        this.systemBaseWeight = systemBaseWeight;
    }

    public Double getSystemBetWeight() {
        return systemBetWeight;
    }

    public void setSystemBetWeight(Double systemBetWeight) {
        this.systemBetWeight = systemBetWeight;
    }

    public String getSystemIndexNum() {
        return systemIndexNum;
    }

    public void setSystemIndexNum(String systemIndexNum) {
        this.systemIndexNum = systemIndexNum;
    }

    public String getSystemIndexName() {
        return systemIndexName;
    }

    public void setSystemIndexName(String systemIndexName) {
        this.systemIndexName = systemIndexName;
    }

    public String getSystemConfirmTargetValue() {
        return systemConfirmTargetValue;
    }

    public void setSystemConfirmTargetValue(String systemConfirmTargetValue) {
        this.systemConfirmTargetValue = systemConfirmTargetValue;
    }

    public Boolean getSystemStandard() {
        return systemStandard;
    }

    public void setSystemStandard(Boolean systemStandard) {
        this.systemStandard = systemStandard;
    }

    public Double getSystemBasesScore() {
        return systemBasesScore;
    }

    public void setSystemBasesScore(Double systemBasesScore) {
        this.systemBasesScore = systemBasesScore;
    }

    public Double getSystemPlanBasesScore() {
        return systemPlanBasesScore;
    }

    public void setSystemPlanBasesScore(Double systemPlanBasesScore) {
        this.systemPlanBasesScore = systemPlanBasesScore;
    }

    public Double getSystemPracticeBasesScore() {
        return systemPracticeBasesScore;
    }

    public void setSystemPracticeBasesScore(Double systemPracticeBasesScore) {
        this.systemPracticeBasesScore = systemPracticeBasesScore;
    }

    public Double getSystemBetScore() {
        return systemBetScore;
    }

    public void setSystemBetScore(Double systemBetScore) {
        this.systemBetScore = systemBetScore;
    }

    public Double getSystemPlanBetScore() {
        return systemPlanBetScore;
    }

    public void setSystemPlanBetScore(Double systemPlanBetScore) {
        this.systemPlanBetScore = systemPlanBetScore;
    }

    public Double getSystemPracticeBetScore() {
        return systemPracticeBetScore;
    }

    public void setSystemPracticeBetScore(Double systemPracticeBetScore) {
        this.systemPracticeBetScore = systemPracticeBetScore;
    }

    public String getUnmetAllocationSystem() {
        return unmetAllocationSystem;
    }

    public void setUnmetAllocationSystem(String unmetAllocationSystem) {
        this.unmetAllocationSystem = unmetAllocationSystem;
    }

    public Double getSystemUnmetAllocation() {
        return systemUnmetAllocation;
    }

    public void setSystemUnmetAllocation(Double systemUnmetAllocation) {
        this.systemUnmetAllocation = systemUnmetAllocation;
    }

    public Double getSystemRestrictScore() {
        return systemRestrictScore;
    }

    public void setSystemRestrictScore(Double systemRestrictScore) {
        this.systemRestrictScore = systemRestrictScore;
    }

    public Double getSystemPlanRestrictScore() {
        return systemPlanRestrictScore;
    }

    public void setSystemPlanRestrictScore(Double systemPlanRestrictScore) {
        this.systemPlanRestrictScore = systemPlanRestrictScore;
    }

    public Double getSystemPracticeRestrictScore() {
        return systemPracticeRestrictScore;
    }

    public void setSystemPracticeRestrictScore(Double systemPracticeRestrictScore) {
        this.systemPracticeRestrictScore = systemPracticeRestrictScore;
    }

    public Double getSystemTotalScore() {
        return systemTotalScore;
    }

    public void setSystemTotalScore(Double systemTotalScore) {
        this.systemTotalScore = systemTotalScore;
    }

    public Double getSystemPlanTotalScore() {
        return systemPlanTotalScore;
    }

    public void setSystemPlanTotalScore(Double systemPlanTotalScore) {
        this.systemPlanTotalScore = systemPlanTotalScore;
    }

    public Double getSystemPracticeTotalScore() {
        return systemPracticeTotalScore;
    }

    public void setSystemPracticeTotalScore(Double systemPracticeTotalScore) {
        this.systemPracticeTotalScore = systemPracticeTotalScore;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getDepartmentBaseWeight() {
        return departmentBaseWeight;
    }

    public void setDepartmentBaseWeight(Double departmentBaseWeight) {
        this.departmentBaseWeight = departmentBaseWeight;
    }

    public Double getDepartmentBetWeight() {
        return departmentBetWeight;
    }

    public void setDepartmentBetWeight(Double departmentBetWeight) {
        this.departmentBetWeight = departmentBetWeight;
    }

    public String getDepartmentIndexNum() {
        return departmentIndexNum;
    }

    public void setDepartmentIndexNum(String departmentIndexNum) {
        this.departmentIndexNum = departmentIndexNum;
    }

    public String getDepartmentIndexName() {
        return departmentIndexName;
    }

    public void setDepartmentIndexName(String departmentIndexName) {
        this.departmentIndexName = departmentIndexName;
    }

    public String getDepartmentConfirmTargetValue() {
        return departmentConfirmTargetValue;
    }

    public void setDepartmentConfirmTargetValue(String departmentConfirmTargetValue) {
        this.departmentConfirmTargetValue = departmentConfirmTargetValue;
    }

    public Boolean getDepartmentStandard() {
        return departmentStandard;
    }

    public void setDepartmentStandard(Boolean departmentStandard) {
        this.departmentStandard = departmentStandard;
    }

    public Double getDepartmentBasesScore() {
        return departmentBasesScore;
    }

    public void setDepartmentBasesScore(Double departmentBasesScore) {
        this.departmentBasesScore = departmentBasesScore;
    }

    public Double getDepartmentPlanBasesScore() {
        return departmentPlanBasesScore;
    }

    public void setDepartmentPlanBasesScore(Double departmentPlanBasesScore) {
        this.departmentPlanBasesScore = departmentPlanBasesScore;
    }

    public Double getDepartmentPracticeBasesScore() {
        return departmentPracticeBasesScore;
    }

    public void setDepartmentPracticeBasesScore(Double departmentPracticeBasesScore) {
        this.departmentPracticeBasesScore = departmentPracticeBasesScore;
    }

    public Double getDepartmentBetScore() {
        return departmentBetScore;
    }

    public void setDepartmentBetScore(Double departmentBetScore) {
        this.departmentBetScore = departmentBetScore;
    }

    public Double getDepartmentPlanBetScore() {
        return departmentPlanBetScore;
    }

    public void setDepartmentPlanBetScore(Double departmentPlanBetScore) {
        this.departmentPlanBetScore = departmentPlanBetScore;
    }

    public Double getDepartmentPracticeBetScore() {
        return departmentPracticeBetScore;
    }

    public void setDepartmentPracticeBetScore(Double departmentPracticeBetScore) {
        this.departmentPracticeBetScore = departmentPracticeBetScore;
    }

    public String getUnmetAllocationDepartment() {
        return unmetAllocationDepartment;
    }

    public void setUnmetAllocationDepartment(String unmetAllocationDepartment) {
        this.unmetAllocationDepartment = unmetAllocationDepartment;
    }

    public Double getDepartmentUnmetAllocation() {
        return departmentUnmetAllocation;
    }

    public void setDepartmentUnmetAllocation(Double departmentUnmetAllocation) {
        this.departmentUnmetAllocation = departmentUnmetAllocation;
    }

    public Double getDepartmentRestrictScore() {
        return departmentRestrictScore;
    }

    public void setDepartmentRestrictScore(Double departmentRestrictScore) {
        this.departmentRestrictScore = departmentRestrictScore;
    }

    public Double getDepartmentPlanRestrictScore() {
        return departmentPlanRestrictScore;
    }

    public void setDepartmentPlanRestrictScore(Double departmentPlanRestrictScore) {
        this.departmentPlanRestrictScore = departmentPlanRestrictScore;
    }

    public Double getDepartmentPracticeRestrictScore() {
        return departmentPracticeRestrictScore;
    }

    public void setDepartmentPracticeRestrictScore(Double departmentPracticeRestrictScore) {
        this.departmentPracticeRestrictScore = departmentPracticeRestrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public Double getDepartmentPlanTotalScore() {
        return departmentPlanTotalScore;
    }

    public void setDepartmentPlanTotalScore(Double departmentPlanTotalScore) {
        this.departmentPlanTotalScore = departmentPlanTotalScore;
    }

    public Double getDepartmentPracticeTotalScore() {
        return departmentPracticeTotalScore;
    }

    public void setDepartmentPracticeTotalScore(Double departmentPracticeTotalScore) {
        this.departmentPracticeTotalScore = departmentPracticeTotalScore;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Double getJobsBaseWeight() {
        return jobsBaseWeight;
    }

    public void setJobsBaseWeight(Double jobsBaseWeight) {
        this.jobsBaseWeight = jobsBaseWeight;
    }

    public Double getJobsBetWeight() {
        return jobsBetWeight;
    }

    public void setJobsBetWeight(Double jobsBetWeight) {
        this.jobsBetWeight = jobsBetWeight;
    }

    public String getJobsIndexNum() {
        return jobsIndexNum;
    }

    public void setJobsIndexNum(String jobsIndexNum) {
        this.jobsIndexNum = jobsIndexNum;
    }

    public String getJobsIndexName() {
        return jobsIndexName;
    }

    public void setJobsIndexName(String jobsIndexName) {
        this.jobsIndexName = jobsIndexName;
    }

    public String getJobsConfirmTargetValue() {
        return jobsConfirmTargetValue;
    }

    public void setJobsConfirmTargetValue(String jobsConfirmTargetValue) {
        this.jobsConfirmTargetValue = jobsConfirmTargetValue;
    }

    public Boolean getJobsStandard() {
        return jobsStandard;
    }

    public void setJobsStandard(Boolean jobsStandard) {
        this.jobsStandard = jobsStandard;
    }

    public String getUnmetAllocationJobs() {
        return unmetAllocationJobs;
    }

    public void setUnmetAllocationJobs(String unmetAllocationJobs) {
        this.unmetAllocationJobs = unmetAllocationJobs;
    }

    public Double getJobsUnmetAllocation() {
        return jobsUnmetAllocation;
    }

    public void setJobsUnmetAllocation(Double jobsUnmetAllocation) {
        this.jobsUnmetAllocation = jobsUnmetAllocation;
    }

    public Double getJobsBasesScore() {
        return jobsBasesScore;
    }

    public void setJobsBasesScore(Double jobsBasesScore) {
        this.jobsBasesScore = jobsBasesScore;
    }

    public Double getJobsPlanBasesScore() {
        return jobsPlanBasesScore;
    }

    public void setJobsPlanBasesScore(Double jobsPlanBasesScore) {
        this.jobsPlanBasesScore = jobsPlanBasesScore;
    }

    public Double getJobsPracticeBasesScore() {
        return jobsPracticeBasesScore;
    }

    public void setJobsPracticeBasesScore(Double jobsPracticeBasesScore) {
        this.jobsPracticeBasesScore = jobsPracticeBasesScore;
    }

    public Double getJobsBetScore() {
        return jobsBetScore;
    }

    public void setJobsBetScore(Double jobsBetScore) {
        this.jobsBetScore = jobsBetScore;
    }

    public Double getJobsPlanBetScore() {
        return jobsPlanBetScore;
    }

    public void setJobsPlanBetScore(Double jobsPlanBetScore) {
        this.jobsPlanBetScore = jobsPlanBetScore;
    }

    public Double getJobsPracticeBetScore() {
        return jobsPracticeBetScore;
    }

    public void setJobsPracticeBetScore(Double jobsPracticeBetScore) {
        this.jobsPracticeBetScore = jobsPracticeBetScore;
    }

    public Double getJobsRestrictScore() {
        return jobsRestrictScore;
    }

    public void setJobsRestrictScore(Double jobsRestrictScore) {
        this.jobsRestrictScore = jobsRestrictScore;
    }

    public Double getJobsPlanRestrictScore() {
        return jobsPlanRestrictScore;
    }

    public void setJobsPlanRestrictScore(Double jobsPlanRestrictScore) {
        this.jobsPlanRestrictScore = jobsPlanRestrictScore;
    }

    public Double getJobsPracticeRestrictScore() {
        return jobsPracticeRestrictScore;
    }

    public void setJobsPracticeRestrictScore(Double jobsPracticeRestrictScore) {
        this.jobsPracticeRestrictScore = jobsPracticeRestrictScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getPlanTotalScore() {
        return planTotalScore;
    }

    public void setPlanTotalScore(Double planTotalScore) {
        this.planTotalScore = planTotalScore;
    }

    public Double getPracticeTotalScore() {
        return practiceTotalScore;
    }

    public void setPracticeTotalScore(Double practiceTotalScore) {
        this.practiceTotalScore = practiceTotalScore;
    }
}
