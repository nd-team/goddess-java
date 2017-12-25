package com.bjike.goddess.interiorrecommend.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.interiorrecommend.enums.RecommendType;
import com.bjike.goddess.interiorrecommend.enums.SolutionStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 推荐方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "interiorrecommend_recommendscheme")
public class RecommendScheme extends BaseEntity {

    /**
     * 方案制作时间
     */
    @Column(name = "makeTime", nullable = false, columnDefinition = "DATE   COMMENT '方案制作时间'")
    private LocalDate makeTime;

    /**
     * 方案名称
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String type;

    /**
     * 目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '目的'")
    private String purpose;

    /**
     * 推荐奖开通时间
     */
    @Column(name = "openTime", nullable = false, columnDefinition = "DATE  COMMENT '推荐奖开通时间'")
    private LocalDate openTime;

    /**
     * 推荐奖关闭时间
     */
    @Column(name = "closeTime", nullable = false, columnDefinition = "DATE   COMMENT '推荐奖关闭时间'")
    private LocalDate closeTime;

    /**
     * 适用范围
     */
    @Column(name = "suitableObj", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '适用范围'")
    private String suitableObj;

    /**
     * 推荐条件
     */
    @Column(name = "requireDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '推荐条件'")
    private String requireDetail;


    /**
     * 招聘负责人职责
     */
    @Column(name = "hiringDuties",nullable = false,columnDefinition = "VARCHAR(255) COMMENT '招聘负责人职责'")
    private String hiringDuties;

    /**
     * 推荐人职责
     */
    @Column(name = "recommenderDuty",nullable = false,columnDefinition = "VARCHAR(255) COMMENT '推荐人职责'")
    private String recommenderDuty;

    /**
     * 推荐类型
     */
    @Column(name = "recommendType",nullable = false,columnDefinition = "TINYINT(3) COMMENT '推荐类型'")
    private RecommendType recommendType;

    /**
     * 推荐岗位
     */
    @Column(name = "recommendPosition",nullable = false,columnDefinition = "VARCHAR(255)  COMMENT '推荐岗位'")
    private String recommendPosition;


    /**
     * 推荐时长
     */
    @Column(name = "recommendTime",nullable = false,columnDefinition = "INT(11)  COMMENT '推荐时长'")
    private Integer recommendTime;


    /**
     * 推荐考核内容
     */
    @Column(name = "checkContent",nullable = false,columnDefinition = "VARCHAR(255)  COMMENT '推荐考核内容'")
    private String checkContent;

    /**
     * 内容要求
     */
    @Column(name = "contentRequire",nullable = false,columnDefinition = "VARCHAR(255)  COMMENT '内容要求'")
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

    public LocalDate getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(LocalDate makeTime) {
        this.makeTime = makeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDate openTime) {
        this.openTime = openTime;
    }

    public LocalDate getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDate closeTime) {
        this.closeTime = closeTime;
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