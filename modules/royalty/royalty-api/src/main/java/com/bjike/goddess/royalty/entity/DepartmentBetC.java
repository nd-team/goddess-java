package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 部门间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_departmentbetc")
public class DepartmentBetC extends BaseEntity {
    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 目标-部门分配基础权重（%）
     */
    @Column(name = "baseWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目标-部门分配基础权重（%）'")
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
     * 部门目标总得分（目标制约得分+目标基础得分）
     */
    @Column(name = "departmentTotalScore", columnDefinition = "DECIMAL(10,2)   COMMENT '部门目标总得分'")
    private Double departmentTotalScore;
    /**
     * 部门计划总得分（计划制约得分+计划基础得分）
     */
    @Column(name = "departmentTotalScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '部门计划总得分'")
    private Double departmentTotalScorePlan;
    /**
     * 部门实际总得分（实际制约得分+实际基础得分）
     */
    @Column(name = "departmentTotalScorePractice", columnDefinition = "DECIMAL(10,2)   COMMENT '部门实际总得分'")
    private Double departmentTotalScorePractice;

    /**
     * 部门间对赌表B
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentBetB_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '部门间对赌表B'")
    private DepartmentBetB departmentBetB;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public Double getDepartmentTotalScore() {
        return departmentTotalScore;
    }

    public void setDepartmentTotalScore(Double departmentTotalScore) {
        this.departmentTotalScore = departmentTotalScore;
    }

    public Double getDepartmentTotalScorePlan() {
        return departmentTotalScorePlan;
    }

    public void setDepartmentTotalScorePlan(Double departmentTotalScorePlan) {
        this.departmentTotalScorePlan = departmentTotalScorePlan;
    }

    public Double getDepartmentTotalScorePractice() {
        return departmentTotalScorePractice;
    }

    public void setDepartmentTotalScorePractice(Double departmentTotalScorePractice) {
        this.departmentTotalScorePractice = departmentTotalScorePractice;
    }

    public DepartmentBetB getDepartmentBetB() {
        return departmentBetB;
    }

    public void setDepartmentBetB(DepartmentBetB departmentBetB) {
        this.departmentBetB = departmentBetB;
    }
}