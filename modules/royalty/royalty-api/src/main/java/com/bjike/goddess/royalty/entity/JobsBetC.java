package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 岗位间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_jobsbetc")
public class JobsBetC extends BaseEntity {

    /**
     * 岗位
     */
    @Column(name = "jobs", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 分配基础权重（%）
     */
    @Column(name = "baseWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '分配基础权重（%）'")
    private Double baseWeight;



    /**
     * 基础得分（部门总得分*目标-部门分配基础权重）
     */
    @Column(name = "basesScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '基础得分（部门总得分*目标-部门分配基础权重）'")
    private Double basesScore;

    /**
     * 制约得分
     */
    @Column(name = "restrictScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '制约得分'")
    private Double restrictScore;

    /**
     * 总得分（制约得分+基础得分）
     */
    @Column(name = "departmentTotalScore", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总得分（制约得分+基础得分）'")
    private Double departmentTotalScore;
    /**
     * 岗位间对赌表B
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "jobsBetB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '岗位间对赌表B'")
    private JobsBetB jobsBetB;


    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
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

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
    }

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public JobsBetB getJobsBetB() {
        return jobsBetB;
    }

    public void setJobsBetB(JobsBetB jobsBetB) {
        this.jobsBetB = jobsBetB;
    }
}