package com.bjike.goddess.firmreward.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 奖励指标
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "firmreward_rewardindicator")
public class RewardIndicator extends BaseEntity {

    /**
     * 奖励开放时间
     */
    @Column(name = "prizeOpeningTime", nullable = false, columnDefinition = "DATE COMMENT '奖励开放时间'")
    private LocalDate prizeOpeningTime;

    /**
     * 奖励结束时间
     */
    @Column(name = "prizeEndTime", nullable = false, columnDefinition = "DATE COMMENT '奖励结束时间'")
    private LocalDate prizeEndTime;

    /**
     * 奖励频率
     */
    @Column(name = "prizeFrequency", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励频率'")
    private String prizeFrequency;

    /**
     * 奖励项目
     */
    @Column(name = "prizeItem", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励项目'")
    private String prizeItem;

    /**
     * 参与对象
     */
    @Column(name = "participator", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '参与对象'")
    private String participator;

    /**
     * 指标名称
     */
    @Column(name = "nameOfIndex", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '指标名称'")
    private String nameOfIndex;

    /**
     * 考核方式
     */
    @Column(name = "evaluationMode", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '考核方式'")
    private String evaluationMode;

    /**
     * 目标值
     */
    @Column(name = "targetValue", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '目标值'")
    private String targetValue;

    /**
     * 指标来源
     */
    @Column(name = "indexSource", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '指标来源'")
    private String indexSource;

    /**
     * 设定排名总人数
     */
    @Column(name = "totalNumber", nullable = false, columnDefinition = "INT(11) COMMENT '设定排名总人数'")
    private Integer totalNumber;

    /**
     * 奖励对象
     */
    @Column(name = "prizeObject", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励对象'")
    private String prizeObject;

    /**
     * 颁奖时间
     */
    @Column(name = "awardTime", nullable = false, columnDefinition = "DATE COMMENT '颁奖时间'")
    private LocalDate awardTime;

    /**
     * 奖励方式
     */
    @Column(name = "rewardRule", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖励方式'")
    private String rewardRule;

    /**
     * 预算额度
     */
    @Column(name = "budgetLimit", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '预算额度'")
    private Double budgetLimit;

    /**
     * 特殊处理
     */
    @Column(name = "specialHandling", columnDefinition = "VARCHAR(255) COMMENT '特殊处理'")
    private String specialHandling;

    /**
     * 总经办意见
     */
    @Column(name = "zjbOpinion", columnDefinition = "VARCHAR(255) COMMENT '总经办意见'")
    private String zjbOpinion;

    /**
     * 备注
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String comment;


    public LocalDate getPrizeOpeningTime() {
        return prizeOpeningTime;
    }

    public void setPrizeOpeningTime(LocalDate prizeOpeningTime) {
        this.prizeOpeningTime = prizeOpeningTime;
    }

    public LocalDate getPrizeEndTime() {
        return prizeEndTime;
    }

    public void setPrizeEndTime(LocalDate prizeEndTime) {
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

    public LocalDate getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(LocalDate awardTime) {
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