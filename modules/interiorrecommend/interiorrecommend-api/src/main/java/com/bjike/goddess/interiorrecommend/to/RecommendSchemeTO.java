package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;
import com.bjike.goddess.interiorrecommend.enums.SolutionStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 推荐方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendSchemeTO extends BaseTO {

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 推荐目的
     */
    @NotBlank(message = "目的不能为空", groups = {ADD.class, EDIT.class})
    private String purpose;

    /**
     * 推荐开通时间
     */
    @NotBlank(message = "推荐奖开通时间不能为空", groups = {ADD.class, EDIT.class})
    private String openTime;

    /**
     * 推荐关闭时间
     */
    @NotBlank(message = "推荐奖关闭时间不能为空", groups = {ADD.class, EDIT.class})
    private String closeTime;

    /**
     * 适用范围
     */
    @NotBlank(message = "适用范围不能为空", groups = {ADD.class, EDIT.class})
    private String suitableObj;

    /**
     * 推荐条件
     */
    @NotBlank(message = "推荐条件不能为空", groups = {ADD.class, EDIT.class})
    private String requireDetail;

    /**
     * 招聘负责人职责
     */
    @NotBlank(message = "招聘负责人职责不能为空", groups = {ADD.class, EDIT.class})
    private String hiringDuties;

    /**
     * 推荐人职责
     */
    @NotBlank(message = "推荐人职责不能为空", groups = {ADD.class, EDIT.class})
    private String recommenderDuty;

    /**
     * 推荐类型
     */
    @NotNull(message = "招推荐类型不能为空", groups = {ADD.class, EDIT.class})
    private RecommendType recommendType;

    /**
     * 推荐岗位
     */
    @NotBlank(message = "推荐岗位不能为空", groups = {ADD.class, EDIT.class})
    private String recommendPosition;


    /**
     * 推荐考核内容
     */
    @NotBlank(message = "推荐考核内容不能为空", groups = {ADD.class, EDIT.class})
    private String checkContent;

    /**
     * 内容要求
     */
    @NotBlank(message = "内容要求不能为空", groups = {ADD.class, EDIT.class})
    private String contentRequire;

    /**
     * 方案状态
     */
    @NotNull(message = "方案状态不能为空", groups = {ADD.class, EDIT.class})
    private SolutionStatus status;

    /**
     * 预计推荐奖金额
     */
    @NotNull(message = "预计推荐奖金额", groups = {ADD.class, EDIT.class})
    private Integer predictMoney;

    /**
     * 推荐奖金额
     */
    @NotNull(message = "推荐奖金额", groups = {ADD.class, EDIT.class})
    private Integer awardMoney;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getSuitableObj() {
        return suitableObj;
    }

    public void setSuitableObj(String suitableObj) {
        this.suitableObj = suitableObj;
    }

    public String getRequireDetail() {
        return requireDetail;
    }

    public void setRequireDetail(String requireDetail) {
        this.requireDetail = requireDetail;
    }

    public String getHiringDuties() {
        return hiringDuties;
    }

    public void setHiringDuties(String hiringDuties) {
        this.hiringDuties = hiringDuties;
    }

    public String getRecommenderDuty() {
        return recommenderDuty;
    }

    public void setRecommenderDuty(String recommenderDuty) {
        this.recommenderDuty = recommenderDuty;
    }

    public RecommendType getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(RecommendType recommendType) {
        this.recommendType = recommendType;
    }

    public String getRecommendPosition() {
        return recommendPosition;
    }

    public void setRecommendPosition(String recommendPosition) {
        this.recommendPosition = recommendPosition;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public String getContentRequire() {
        return contentRequire;
    }

    public void setContentRequire(String contentRequire) {
        this.contentRequire = contentRequire;
    }

    public SolutionStatus getStatus() {
        return status;
    }

    public void setStatus(SolutionStatus status) {
        this.status = status;
    }

    public Integer getPredictMoney() {
        return predictMoney;
    }

    public void setPredictMoney(Integer predictMoney) {
        this.predictMoney = predictMoney;
    }

    public Integer getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(Integer awardMoney) {
        this.awardMoney = awardMoney;
    }
}