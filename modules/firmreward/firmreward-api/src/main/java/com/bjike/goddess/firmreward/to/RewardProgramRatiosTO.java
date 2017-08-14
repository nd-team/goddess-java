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
    private List<String> rewardPrograms;

    /**
     * 当月侧重程度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "当月侧重程度不能为空")
    private List<String> focusingDegrees;

    /**
     * 当月预算范围
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "当月预算范围不能为空")
    private List<Double> budgetRanges;

    /**
     * 奖金权重
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "奖金权重不能为空")
    private List<String> bonusWeights;

    /**
     * 奖金额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "奖金额度不能为空")
    private List<Double> bonusLimits;

    /**
     * 荣誉衍生奖品权重
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "荣誉衍生奖品权重不能为空")
    private List<String> honorWeights;

    /**
     * 荣誉衍生奖品额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "荣誉衍生奖品额度不能为空")
    private List<Double> honorLimits;

    /**
     * 经验值
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值不能为空")
    private List<String> empiricalValues;

    /**
     * 经验值额度
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值额度不能为空")
    private List<Double> empiricalValueLimits;

    /**
     * 经验值换成金额
     */
    @NotNull(groups = {RewardProgramRatiosTO.IRewardProgramRatio.class}, message = "经验值换成金额不能为空")
    private List<Double> empiricalValueToMoneys;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRewardPrograms() {
        return rewardPrograms;
    }

    public void setRewardPrograms(List<String> rewardPrograms) {
        this.rewardPrograms = rewardPrograms;
    }

    public List<String> getFocusingDegrees() {
        return focusingDegrees;
    }

    public void setFocusingDegrees(List<String> focusingDegrees) {
        this.focusingDegrees = focusingDegrees;
    }

    public List<Double> getBudgetRanges() {
        return budgetRanges;
    }

    public void setBudgetRanges(List<Double> budgetRanges) {
        this.budgetRanges = budgetRanges;
    }

    public List<String> getBonusWeights() {
        return bonusWeights;
    }

    public void setBonusWeights(List<String> bonusWeights) {
        this.bonusWeights = bonusWeights;
    }

    public List<Double> getBonusLimits() {
        return bonusLimits;
    }

    public void setBonusLimits(List<Double> bonusLimits) {
        this.bonusLimits = bonusLimits;
    }

    public List<String> getHonorWeights() {
        return honorWeights;
    }

    public void setHonorWeights(List<String> honorWeights) {
        this.honorWeights = honorWeights;
    }

    public List<Double> getHonorLimits() {
        return honorLimits;
    }

    public void setHonorLimits(List<Double> honorLimits) {
        this.honorLimits = honorLimits;
    }

    public List<String> getEmpiricalValues() {
        return empiricalValues;
    }

    public void setEmpiricalValues(List<String> empiricalValues) {
        this.empiricalValues = empiricalValues;
    }

    public List<Double> getEmpiricalValueLimits() {
        return empiricalValueLimits;
    }

    public void setEmpiricalValueLimits(List<Double> empiricalValueLimits) {
        this.empiricalValueLimits = empiricalValueLimits;
    }

    public List<Double> getEmpiricalValueToMoneys() {
        return empiricalValueToMoneys;
    }

    public void setEmpiricalValueToMoneys(List<Double> empiricalValueToMoneys) {
        this.empiricalValueToMoneys = empiricalValueToMoneys;
    }
}
