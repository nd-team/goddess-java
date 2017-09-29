package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 体系间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:52 ]
 * @Description: [ 体系间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_systembetb")
public class SystemBetB extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 目标-部门分配基础权重（%）
     */
    @Column(name = "baseWeight", columnDefinition = "DECIMAL(10,2)   COMMENT '目标-部门分配基础权重（%）'")
    private Double baseWeight;

    /**
     * 目标基础得分（分值（利润额）*目标-部门分配基础权重）
     */
    @Column(name = "basesScore", columnDefinition = "DECIMAL(10,2)   COMMENT '目标基础得分'")
    private Double basesScore;
    /**
     * 计划基础得分（计划分值（利润额）*目标-部门分配基础权重）
     */
    @Column(name = "basesScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '计划基础得分'")
    private Double basesScorePlan;
    /**
     * 实际基础得分（实际分值（利润额）*目标-部门分配基础权重）
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
     * 体系目标总得分（目标制约得分+目标基础得分）
     */
    @Column(name = "systemTotalScore", columnDefinition = "DECIMAL(10,2)   COMMENT '体系目标总得分'")
    private Double systemTotalScore;
    /**
     * 体系计划总得分（计划制约得分+计划基础得分）
     */
    @Column(name = "systemTotalScorePlan", columnDefinition = "DECIMAL(10,2)   COMMENT '体系计划总得分'")
    private Double systemTotalScorePlan;
    /**
     * 体系实际总得分（实际制约得分+实际基础得分）
     */
    @Column(name = "systemTotalScorePractice", columnDefinition = "DECIMAL(10,2)   COMMENT '体系实际总得分'")
    private Double systemTotalScorePractice;

    /**
     * 体系间对赌表A
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "systemBetA_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '体系间对赌表A'")
    private SystemBetA systemBetA;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(Double baseWeight) {
        this.baseWeight = baseWeight;
    }

    public Double getRestrictScore() {
        return restrictScore;
    }

    public void setRestrictScore(Double restrictScore) {
        this.restrictScore = restrictScore;
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

    public Double getSystemTotalScore() {
        return systemTotalScore;
    }

    public void setSystemTotalScore(Double systemTotalScore) {
        this.systemTotalScore = systemTotalScore;
    }

    public Double getSystemTotalScorePlan() {
        return systemTotalScorePlan;
    }

    public void setSystemTotalScorePlan(Double systemTotalScorePlan) {
        this.systemTotalScorePlan = systemTotalScorePlan;
    }

    public Double getSystemTotalScorePractice() {
        return systemTotalScorePractice;
    }

    public void setSystemTotalScorePractice(Double systemTotalScorePractice) {
        this.systemTotalScorePractice = systemTotalScorePractice;
    }

    public SystemBetA getSystemBetA() {
        return systemBetA;
    }

    public void setSystemBetA(SystemBetA systemBetA) {
        this.systemBetA = systemBetA;
    }
}