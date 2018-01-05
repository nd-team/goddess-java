package com.bjike.goddess.interiorrecommend.vo;

import com.bjike.goddess.interiorrecommend.enums.RecommendType;
import com.bjike.goddess.interiorrecommend.enums.SolutionStatus;


/**
 * 推荐方案表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendSchemeVO {

    /**
     * id
     */
    private String id;
    /**
     * 方案制作时间
     */
    private String makeTime;

    /**
     * 方案名称
     */
    private String type;

    /**
     * 目的
     */
    private String purpose;

    /**
     * 推荐奖开通时间
     */
    private String openTime;

    /**
     * 推荐奖关闭时间
     */
    private String closeTime;

    /**
     * 适用范围
     */
    private String suitableObj;

    /**
     * 推荐条件
     */
    private String requireDetail;

    /**
     * 招聘负责人职责
     */
    private String hiringDuties;

    /**
     * 推荐人职责
     */
    private String recommenderDuty;

    /**
     * 推荐类型
     */
    private RecommendType recommendType;

    /**
     * 推荐岗位
     */
    private String recommendPosition;


    /**
     * 推荐时长
     */
    private Integer recommendTime;


    /**
     * 推荐考核内容
     */
    private String checkContent;

    /**
     * 内容要求
     */
    private String contentRequire;

    /**
     * 方案状态
     */
    private SolutionStatus status;

    /**
     * 预计推荐奖金额
     */
    private Integer predictMoney;

    /**
     * 推荐奖金额
     */
    private Integer awardMoney;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

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

    public Integer getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Integer recommendTime) {
        this.recommendTime = recommendTime;
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