package com.bjike.goddess.royalty.vo;

/**
 * Created by ike on 17-7-17.
 */
public class ManageCommissionVO {
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;
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
     * 基础得分
     */
    private Double systemBasesScore;
    /**
     * 对赌得分（分值*目标-部门分配对赌权重）
     */
    private Double systemBetScore;

    /**
     * 未达标分配体系
     */
    private String unmetAllocationSystem;

    /**
     * 未达标分配
     */
    private Double systemUnmetAllocation;
    /**
     * 制约得分
     */
    private Double systemRestrictScore;

    /**
     * 部门总得分
     */
    private Double systemTotalScore;
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
     * 基础得分
     */
    private Double departmentBasesScore;
    /**
     * 对赌得分
     */
    private Double departmentBetScore;

    /**
     * 未达标分配体系
     */
    private String unmetAllocationDepartment;

    /**
     * 未达标分配
     */
    private Double departmentUnmetAllocation;
    /**
     * 制约得分
     */
    private Double departmentRestrictScore;

    /**
     * 部门总得分
     */
    private Double departmentTotalScore;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 岗位
     */
    private String jobs;

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
     * 基础得分
     */
    private Double jobsBasesScore;
    /**
     * 对赌得分
     */
    private Double jobsBetScore;

    /**
     * 制约得分
     */
    private Double jobsRestrictScore;

    /**
     * 总得分
     */
    private Double jobsTotalScore;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Double getSystemBetScore() {
        return systemBetScore;
    }

    public void setSystemBetScore(Double systemBetScore) {
        this.systemBetScore = systemBetScore;
    }

    public Double getSystemRestrictScore() {
        return systemRestrictScore;
    }

    public void setSystemRestrictScore(Double systemRestrictScore) {
        this.systemRestrictScore = systemRestrictScore;
    }

    public Double getSystemTotalScore() {
        return systemTotalScore;
    }

    public void setSystemTotalScore(Double systemTotalScore) {
        this.systemTotalScore = systemTotalScore;
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

    public Double getDepartmentBasesScore() {
        return departmentBasesScore;
    }

    public void setDepartmentBasesScore(Double departmentBasesScore) {
        this.departmentBasesScore = departmentBasesScore;
    }

    public Double getDepartmentRestrictScore() {
        return departmentRestrictScore;
    }

    public void setDepartmentRestrictScore(Double departmentRestrictScore) {
        this.departmentRestrictScore = departmentRestrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
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

    public Double getDepartmentBetScore() {
        return departmentBetScore;
    }

    public void setDepartmentBetScore(Double departmentBetScore) {
        this.departmentBetScore = departmentBetScore;
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

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public Double getJobsBaseWeight() {
        return jobsBaseWeight;
    }

    public void setJobsBaseWeight(Double jobsBaseWeight) {
        this.jobsBaseWeight = jobsBaseWeight;
    }

    public Double getJobsBasesScore() {
        return jobsBasesScore;
    }

    public void setJobsBasesScore(Double jobsBasesScore) {
        this.jobsBasesScore = jobsBasesScore;
    }

    public Double getJobsRestrictScore() {
        return jobsRestrictScore;
    }

    public void setJobsRestrictScore(Double jobsRestrictScore) {
        this.jobsRestrictScore = jobsRestrictScore;
    }

    public Double getJobsTotalScore() {
        return jobsTotalScore;
    }

    public void setJobsTotalScore(Double jobsTotalScore) {
        this.jobsTotalScore = jobsTotalScore;
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

    public Double getJobsBetScore() {
        return jobsBetScore;
    }

    public void setJobsBetScore(Double jobsBetScore) {
        this.jobsBetScore = jobsBetScore;
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
}
