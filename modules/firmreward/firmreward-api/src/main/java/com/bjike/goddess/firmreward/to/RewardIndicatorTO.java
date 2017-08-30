package com.bjike.goddess.firmreward.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 奖励指标
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardIndicatorTO extends BaseTO {

    /**
     * 奖励开放日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励开放时间不能为空")
    private String prizeOpeningTime;

    /**
     * 奖励结束日期
     */
    private String prizeEndTime;

    /**
     * 奖励频率
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励频率不能为空")
    private String prizeFrequency;

    /**
     * 奖励项目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励项目不能为空")
    private String prizeItem;

    /**
     * 参与对象
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "参与对象不能为空")
    private String participator;

    /**
     * 指标名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "指标名称不能为空")
    private String nameOfIndex;

    /**
     * 考核方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "考核方式不能为空")
    private String evaluationMode;

    /**
     * 目标值
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "目标值不能为空")
    private String targetValue;

    /**
     * 指标来源
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "指标来源不能为空")
    private String indexSource;

    /**
     * 设定排名总人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "设定排名总人数不能为空")
    private Integer totalNumber;

    /**
     * 奖励对象
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励对象不能为空")
    private String prizeObject;

    /**
     * 颁奖日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "颁奖时间不能为空")
    private String awardTime;

    /**
     * 奖励方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "奖励方式不能为空")
    private String rewardRule;

    /**
     * 预算额度
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "预算额度不能为空")
    private Double budgetLimit;

    /**
     * 特殊处理
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "特殊处理不能为空")
    private String specialHandling;

    /**
     * 总经办意见
     */
    private String zjbOpinion;

    /**
     * 备注
     */
    private String comment;


    public String getPrizeOpeningTime() {
        return prizeOpeningTime;
    }

    public void setPrizeOpeningTime(String prizeOpeningTime) {
        this.prizeOpeningTime = prizeOpeningTime;
    }

    public String getPrizeEndTime() {
        return prizeEndTime;
    }

    public void setPrizeEndTime(String prizeEndTime) {
        this.prizeEndTime = prizeEndTime;
    }

    public String getPrizeFrequency() {
        return prizeFrequency;
    }

    public void setPrizeFrequency(String prizeFrequency) {
        this.prizeFrequency = prizeFrequency;
    }

    public String getPrizeItem() {
        return prizeItem;
    }

    public void setPrizeItem(String prizeItem) {
        this.prizeItem = prizeItem;
    }

    public String getParticipator() {
        return participator;
    }

    public void setParticipator(String participator) {
        this.participator = participator;
    }

    public String getNameOfIndex() {
        return nameOfIndex;
    }

    public void setNameOfIndex(String nameOfIndex) {
        this.nameOfIndex = nameOfIndex;
    }

    public String getEvaluationMode() {
        return evaluationMode;
    }

    public void setEvaluationMode(String evaluationMode) {
        this.evaluationMode = evaluationMode;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getIndexSource() {
        return indexSource;
    }

    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getPrizeObject() {
        return prizeObject;
    }

    public void setPrizeObject(String prizeObject) {
        this.prizeObject = prizeObject;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public String getRewardRule() {
        return rewardRule;
    }

    public void setRewardRule(String rewardRule) {
        this.rewardRule = rewardRule;
    }

    public Double getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(Double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public String getSpecialHandling() {
        return specialHandling;
    }

    public void setSpecialHandling(String specialHandling) {
        this.specialHandling = specialHandling;
    }

    public String getZjbOpinion() {
        return zjbOpinion;
    }

    public void setZjbOpinion(String zjbOpinion) {
        this.zjbOpinion = zjbOpinion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}