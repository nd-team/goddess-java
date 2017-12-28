package com.bjike.goddess.royalty.vo;

import com.bjike.goddess.royalty.bo.DepartmentBetBBO;
import com.bjike.goddess.royalty.bo.JobsBetBBO;
import com.bjike.goddess.royalty.bo.SystemBetBBO;

import java.util.List;

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
     * 体系间
     */
    List<SystemBetBBO> systemBetBBOS;
    /**
     * 部门间
     */
    List<DepartmentBetBBO> departmentBetBBOS;
    /**
     * 岗位间
     */
    List<JobsBetBBO> jobsBetBBOS;

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

    public List<SystemBetBBO> getSystemBetBBOS() {
        return systemBetBBOS;
    }

    public void setSystemBetBBOS(List<SystemBetBBO> systemBetBBOS) {
        this.systemBetBBOS = systemBetBBOS;
    }

    public List<DepartmentBetBBO> getDepartmentBetBBOS() {
        return departmentBetBBOS;
    }

    public void setDepartmentBetBBOS(List<DepartmentBetBBO> departmentBetBBOS) {
        this.departmentBetBBOS = departmentBetBBOS;
    }

    public List<JobsBetBBO> getJobsBetBBOS() {
        return jobsBetBBOS;
    }

    public void setJobsBetBBOS(List<JobsBetBBO> jobsBetBBOS) {
        this.jobsBetBBOS = jobsBetBBOS;
    }
}
