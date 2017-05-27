package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 奖励项目比例
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-27 17:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RewardProgramRatiosTO extends BaseTO {

    /**
     * 奖励项目
     */
    private String[] rewardPrograms;

    /**
     * 当月侧重程度
     */
    private String[] focusingDegrees;

    /**
     * 当月预算范围
     */
    private Double[] budgetRanges;

    /**
     * 奖金权重
     */
    private String[] bonusWeights;

    /**
     * 奖金额度
     */
    private Double[] bonusLimits;

    /**
     * 荣誉衍生奖品权重
     */
    private String[] honorWeights;

    /**
     * 荣誉衍生奖品额度
     */
    private Double[] honorLimits;

    /**
     * 经验值
     */
    private String[] empiricalValues;

    /**
     * 经验值额度
     */
    private Double[] empiricalValueLimits;

    /**
     * 经验值换成金额
     */
    private Double[] empiricalValueToMoneys;

    public String[] getRewardPrograms() {
        return rewardPrograms;
    }

    public void setRewardPrograms(String[] rewardPrograms) {
        this.rewardPrograms = rewardPrograms;
    }

    public String[] getFocusingDegrees() {
        return focusingDegrees;
    }

    public void setFocusingDegrees(String[] focusingDegrees) {
        this.focusingDegrees = focusingDegrees;
    }

    public Double[] getBudgetRanges() {
        return budgetRanges;
    }

    public void setBudgetRanges(Double[] budgetRanges) {
        this.budgetRanges = budgetRanges;
    }

    public String[] getBonusWeights() {
        return bonusWeights;
    }

    public void setBonusWeights(String[] bonusWeights) {
        this.bonusWeights = bonusWeights;
    }

    public Double[] getBonusLimits() {
        return bonusLimits;
    }

    public void setBonusLimits(Double[] bonusLimits) {
        this.bonusLimits = bonusLimits;
    }

    public String[] getHonorWeights() {
        return honorWeights;
    }

    public void setHonorWeights(String[] honorWeights) {
        this.honorWeights = honorWeights;
    }

    public Double[] getHonorLimits() {
        return honorLimits;
    }

    public void setHonorLimits(Double[] honorLimits) {
        this.honorLimits = honorLimits;
    }

    public String[] getEmpiricalValues() {
        return empiricalValues;
    }

    public void setEmpiricalValues(String[] empiricalValues) {
        this.empiricalValues = empiricalValues;
    }

    public Double[] getEmpiricalValueLimits() {
        return empiricalValueLimits;
    }

    public void setEmpiricalValueLimits(Double[] empiricalValueLimits) {
        this.empiricalValueLimits = empiricalValueLimits;
    }

    public Double[] getEmpiricalValueToMoneys() {
        return empiricalValueToMoneys;
    }

    public void setEmpiricalValueToMoneys(Double[] empiricalValueToMoneys) {
        this.empiricalValueToMoneys = empiricalValueToMoneys;
    }
}
