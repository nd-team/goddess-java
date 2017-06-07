package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 奖金预算
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BonusBudgetTO extends BaseTO {

    /**
     * 月份
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    private String month;

    /**
     * 总预算
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "总预算不能为空")
    private Double totalBudget;

    /**
     * 奖金:经验值
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖金:经验值不能为空")
    private String ratio;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

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