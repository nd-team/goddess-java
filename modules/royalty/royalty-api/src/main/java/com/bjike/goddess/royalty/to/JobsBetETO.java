package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位间对赌表D
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表D ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetETO extends BaseTO {
    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;
    /**
     * 人数
     */
    @NotNull(message = "人数不能为空",groups = {ADD.class, EDIT.class})
    private Integer people;

    /**
     * 分配基础权重（%）
     */
    @NotNull(message = "分配基础权重（%）不能为空",groups = {ADD.class, EDIT.class})
    private Double baseWeight;

    /**
     * 目标基础得分（体系目标总得分*目标-部门分配基础权重）
     */
    private Double basesScore;
    /**
     * 计划基础得分（体系计划总得分*目标-部门分配基础权重）
     */
    private Double basesScorePlan;
    /**
     * 实际基础得分（体系实际总得分*目标-部门分配基础权重）
     */
    private Double basesScorePractice;
    /**
     * 目标制约得分
     */
    private Double restrictScore;
    /**
     * 计划制约得分
     */
    private Double restrictScorePlan;
    /**
     * 实际制约得分
     */
    private Double restrictScorePractice;

    /**
     * 目标总得分（目标制约得分+目标基础得分）
     */
    private Double totalScore;
    /**
     * 计划总得分（计划制约得分+计划基础得分）
     */
    private Double totalScorePlan;
    /**
     * 实际总得分（实际制约得分+实际基础得分）
     */
    private Double totalScorePractice;
    /**
     * 岗位间对赌表F
     */
    private List<JobsBetFTO> jobsBetFTOS;

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

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public Double getBasesScore() {
        return basesScore;
    }

    public void setBasesScore(Double basesScore) {
        this.basesScore = basesScore;
    }

    public Double getBasesScorePlan() {
        return basesScorePlan;
    }

    public void setBasesScorePlan(Double basesScorePlan) {
        this.basesScorePlan = basesScorePlan;
    }

    public Double getBasesScorePractice() {
        return basesScorePractice;
    }

    public void setBasesScorePractice(Double basesScorePractice) {
        this.basesScorePractice = basesScorePractice;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getRestrictScorePlan() {
        return restrictScorePlan;
    }

    public void setRestrictScorePlan(Double restrictScorePlan) {
        this.restrictScorePlan = restrictScorePlan;
    }

    public Double getRestrictScorePractice() {
        return restrictScorePractice;
    }

    public void setRestrictScorePractice(Double restrictScorePractice) {
        this.restrictScorePractice = restrictScorePractice;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getTotalScorePlan() {
        return totalScorePlan;
    }

    public void setTotalScorePlan(Double totalScorePlan) {
        this.totalScorePlan = totalScorePlan;
    }

    public Double getTotalScorePractice() {
        return totalScorePractice;
    }

    public void setTotalScorePractice(Double totalScorePractice) {
        this.totalScorePractice = totalScorePractice;
    }

    public List<JobsBetFTO> getJobsBetFTOS() {
        return jobsBetFTOS;
    }

    public void setJobsBetFTOS(List<JobsBetFTO> jobsBetFTOS) {
        this.jobsBetFTOS = jobsBetFTOS;
    }
}