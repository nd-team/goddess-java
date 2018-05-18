package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位间对赌表D
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表D ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_jobsbetd")
public class JobsBetD extends BaseEntity {


    /**
     * 岗位
     */
    @Column(name = "jobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;
    /**
     * 人数
     */
    @Column(name = "people", nullable = false, columnDefinition = "INT(11)   COMMENT '人数'")
    private Integer people;


    /**
     * 分配基础权重（%）
     */
    @Column(name = "baseWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分配基础权重（%）'")
    private Double baseWeight;

    /**
     * 目标基础得分（体系目标总得分*目标-部门分配基础权重）
     */
    @Column(name = "basesScore", columnDefinition = "DECIMAL(10,2)   COMMENT '目标基础得分'")
    private Double basesScore;
    /**
     * 计划基础得分（体系计划总得分*目标-部门分配基础权重）
     */
    @Column(name = "basesScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '计划基础得分'")
    private Double basesScorePlan;
    /**
     * 实际基础得分（体系实际总得分*目标-部门分配基础权重）
     */
    @Column(name = "basesScorePractice", columnDefinition = "DECIMAL(10,2)   COMMENT '实际基础得分'")
    private Double basesScorePractice;
    /**
     * 目标制约得分
     */
    @Column(name = "restrictScore", columnDefinition = "DECIMAL(10,2)   COMMENT '目标制约得分'")
    private Double restrictScore;
    /**
     * 计划制约得分
     */
    @Column(name = "restrictScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '计划制约得分'")
    private Double restrictScorePlan;
    /**
     * 实际制约得分
     */
    @Column(name = "restrictScorePractice", columnDefinition = "DECIMAL(10,2)   COMMENT '实际制约得分'")
    private Double restrictScorePractice;

    /**
     * 目标总得分（目标制约得分+目标基础得分）
     */
    @Column(name = "totalScore", columnDefinition = "DECIMAL(10,2)   COMMENT '目标总得分'")
    private Double totalScore;
    /**
     * 计划总得分（计划制约得分+计划基础得分）
     */
    @Column(name = "totalScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '计划总得分'")
    private Double totalScorePlan;
    /**
     * 实际总得分（实际制约得分+实际基础得分）
     */
    @Column(name = "totalScorePractice", columnDefinition = "DECIMAL(10,2)   COMMENT '实际总得分'")
    private Double totalScorePractice;
    /**
     * 岗位间对赌表C
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "jobsBetC_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位间对赌表C'")
    private JobsBetC jobsBetC;


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

    public JobsBetC getJobsBetC() {
        return jobsBetC;
    }

    public void setJobsBetC(JobsBetC jobsBetC) {
        this.jobsBetC = jobsBetC;
    }
}