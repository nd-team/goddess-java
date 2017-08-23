package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    public interface IRewardProgramRatio{}

    /**
     * 奖励项目
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "奖励项目不能为空")
    private String rewardPrograms;

    /**
     * 当月侧重程度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "当月侧重程度不能为空")
    private String focusingDegrees;

    /**
     * 当月预算范围
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "当月预算范围不能为空")
    private Double budgetRanges;

    /**
     * 奖金权重
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "奖金权重不能为空")
    private String bonusWeights;

    /**
     * 奖金额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "奖金额度不能为空")
    private Double bonusLimits;

    /**
     * 荣誉衍生奖品权重
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "荣誉衍生奖品权重不能为空")
    private String honorWeights;

    /**
     * 荣誉衍生奖品额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "荣誉衍生奖品额度不能为空")
    private Double honorLimits;

    /**
     * 经验值
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值不能为空")
    private String empiricalValues;

    /**
     * 经验值额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值额度不能为空")
    private Double empiricalValueLimits;

    /**
     * 经验值换成金额
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值换成金额不能为空")
    private Double empiricalValueToMoneys;

    public String getRewardPrograms() {
        return rewardPrograms;
    }

    public void setRewardPrograms(String rewardPrograms) {
        this.rewardPrograms = rewardPrograms;
    }

    public String getFocusingDegrees() {
        return focusingDegrees;
    }

    public void setFocusingDegrees(String focusingDegrees) {
        this.focusingDegrees = focusingDegrees;
    }

    public Double getBudgetRanges() {
        return budgetRanges;
    }

    public void setBudgetRanges(Double budgetRanges) {
        this.budgetRanges = budgetRanges;
    }

    public String getBonusWeights() {
        return bonusWeights;
    }

    public void setBonusWeights(String bonusWeights) {
        this.bonusWeights = bonusWeights;
    }

    public Double getBonusLimits() {
        return bonusLimits;
    }

    public void setBonusLimits(Double bonusLimits) {
        this.bonusLimits = bonusLimits;
    }

    public String getHonorWeights() {
        return honorWeights;
    }

    public void setHonorWeights(String honorWeights) {
        this.honorWeights = honorWeights;
    }

    public Double getHonorLimits() {
        return honorLimits;
    }

    public void setHonorLimits(Double honorLimits) {
        this.honorLimits = honorLimits;
    }

    public String getEmpiricalValues() {
        return empiricalValues;
    }

    public void setEmpiricalValues(String empiricalValues) {
        this.empiricalValues = empiricalValues;
    }

    public Double getEmpiricalValueLimits() {
        return empiricalValueLimits;
    }

    public void setEmpiricalValueLimits(Double empiricalValueLimits) {
        this.empiricalValueLimits = empiricalValueLimits;
    }

    public Double getEmpiricalValueToMoneys() {
        return empiricalValueToMoneys;
    }

    public void setEmpiricalValueToMoneys(Double empiricalValueToMoneys) {
        this.empiricalValueToMoneys = empiricalValueToMoneys;
    }
}
