package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBet;

import java.util.List;

/**
 * 岗位间对赌表A业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetABO extends BaseBO {

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
     * 岗位间对赌表B
     */
    private List<JobsBetBBO> jobsBetBBOS;

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

    public List<JobsBetBBO> getJobsBetBBOS() {
        return jobsBetBBOS;
    }

    public void setJobsBetBBOS(List<JobsBetBBO> jobsBetBBOS) {
        this.jobsBetBBOS = jobsBetBBOS;
    }
}