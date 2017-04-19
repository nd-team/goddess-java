package com.bjike.goddess.firmreward.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 奖励项目比例
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:35 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "firmreward_rewardprogramratio")
public class RewardProgramRatio extends BaseEntity {

    /**
     * 奖励项目
     */
    @Column(name = "rewardProgram", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励项目'")
    private String rewardProgram;

    /**
     * 当月侧重程度
     */
    @Column(name = "focusingDegree", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '当月侧重程度'")
    private String focusingDegree;

    /**
     * 当月预算范围
     */
    @Column(name = "budgetRange", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '当月预算范围'")
    private Double budgetRange;

    /**
     * 奖金权重
     */
    @Column(name = "bonusWeight", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖金权重'")
    private String bonusWeight;

    /**
     * 奖金额度
     */
    @Column(name = "bonusLimit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '奖金额度'")
    private Double bonusLimit;

    /**
     * 荣誉衍生奖品权重
     */
    @Column(name = "honorWeight", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '荣誉衍生奖品权重'")
    private String honorWeight;

    /**
     * 荣誉衍生奖品额度
     */
    @Column(name = "honorLimit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '荣誉衍生奖品额度'")
    private Double honorLimit;

    /**
     * 经验值
     */
    @Column(name = "empiricalValue", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '经验值'")
    private String empiricalValue;

    /**
     * 经验值额度
     */
    @Column(name = "empiricalValueLimit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '经验值额度'")
    private Double empiricalValueLimit;

    /**
     * 经验值换成金额
     */
    @Column(name = "empiricalValueToMoney", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '经验值换成金额'")
    private Double empiricalValueToMoney;

    /**
     * 奖金预算id
     */
    @Column(name = "bonusBudgetId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖金预算id'")
    private String bonusBudgetId;

    public String getRewardProgram() {
        return rewardProgram;
    }

    public void setRewardProgram(String rewardProgram) {
        this.rewardProgram = rewardProgram;
    }

    public String getFocusingDegree() {
        return focusingDegree;
    }

    public void setFocusingDegree(String focusingDegree) {
        this.focusingDegree = focusingDegree;
    }

    public Double getBudgetRange() {
        return budgetRange;
    }

    public void setBudgetRange(Double budgetRange) {
        this.budgetRange = budgetRange;
    }

    public String getBonusWeight() {
        return bonusWeight;
    }

    public void setBonusWeight(String bonusWeight) {
        this.bonusWeight = bonusWeight;
    }

    public Double getBonusLimit() {
        return bonusLimit;
    }

    public void setBonusLimit(Double bonusLimit) {
        this.bonusLimit = bonusLimit;
    }

    public String getHonorWeight() {
        return honorWeight;
    }

    public void setHonorWeight(String honorWeight) {
        this.honorWeight = honorWeight;
    }

    public Double getHonorLimit() {
        return honorLimit;
    }

    public void setHonorLimit(Double honorLimit) {
        this.honorLimit = honorLimit;
    }

    public String getEmpiricalValue() {
        return empiricalValue;
    }

    public void setEmpiricalValue(String empiricalValue) {
        this.empiricalValue = empiricalValue;
    }

    public Double getEmpiricalValueLimit() {
        return empiricalValueLimit;
    }

    public void setEmpiricalValueLimit(Double empiricalValueLimit) {
        this.empiricalValueLimit = empiricalValueLimit;
    }

    public Double getEmpiricalValueToMoney() {
        return empiricalValueToMoney;
    }

    public void setEmpiricalValueToMoney(Double empiricalValueToMoney) {
        this.empiricalValueToMoney = empiricalValueToMoney;
    }

    public String getBonusBudgetId() {
        return bonusBudgetId;
    }

    public void setBonusBudgetId(String bonusBudgetId) {
        this.bonusBudgetId = bonusBudgetId;
    }
}