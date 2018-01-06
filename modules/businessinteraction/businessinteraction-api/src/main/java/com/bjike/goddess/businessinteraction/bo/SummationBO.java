package com.bjike.goddess.businessinteraction.bo;

import com.bjike.goddess.businessinteraction.enums.Origin;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationBO extends BaseBO {

    /**
     * 业务类型
     */
    private String bussType;

    /**
     * 需求信息数量
     */
    private Integer demandInforNum;

    /**
     * 已反馈数量
     */
    private Integer haveFeedbackNum;

    /**
     * 未反馈数
     */
    private Integer noFeedbackNum;

    /**
     * 已初步分析数
     */
    private Integer preliminaryAnalysisNum;

    /**
     * 未进行初步分析数
     */
    private Integer noPreliminaryAnalysisNum;

    /**
     * 已项目测算数
     */
    private Integer calculatedNum;

    /**
     * 未项目测算数
     */
    private Integer noCalculatedNum;

    /**
     * 项目测算通过数
     */
    private Integer calculatedThroughNum;

    /**
     * 已进行洽谈数量
     */
    private Integer talksMadeNum;

    /**
     * 未进行洽谈数量
     */
    private Integer noTalksMadeNum;

    /**
     * 达成合作数
     */
    private Integer cooperationNum;

    /**
     * 已丢弃数量
     */
    private Integer discardedNum;

    /**
     * 介绍给别的承包商数
     */
    private Integer IntroduceOtherNum;

    /**
     * 服务费用
     */
    private Double serviceFee;

    /**
     * 中介费用
     */
    private Double intermediaryFee;

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public Integer getDemandInforNum() {
        return demandInforNum;
    }

    public void setDemandInforNum(Integer demandInforNum) {
        this.demandInforNum = demandInforNum;
    }

    public Integer getHaveFeedbackNum() {
        return haveFeedbackNum;
    }

    public void setHaveFeedbackNum(Integer haveFeedbackNum) {
        this.haveFeedbackNum = haveFeedbackNum;
    }

    public Integer getNoFeedbackNum() {
        return noFeedbackNum;
    }

    public void setNoFeedbackNum(Integer noFeedbackNum) {
        this.noFeedbackNum = noFeedbackNum;
    }

    public Integer getPreliminaryAnalysisNum() {
        return preliminaryAnalysisNum;
    }

    public void setPreliminaryAnalysisNum(Integer preliminaryAnalysisNum) {
        this.preliminaryAnalysisNum = preliminaryAnalysisNum;
    }

    public Integer getNoPreliminaryAnalysisNum() {
        return noPreliminaryAnalysisNum;
    }

    public void setNoPreliminaryAnalysisNum(Integer noPreliminaryAnalysisNum) {
        this.noPreliminaryAnalysisNum = noPreliminaryAnalysisNum;
    }

    public Integer getCalculatedNum() {
        return calculatedNum;
    }

    public void setCalculatedNum(Integer calculatedNum) {
        this.calculatedNum = calculatedNum;
    }

    public Integer getNoCalculatedNum() {
        return noCalculatedNum;
    }

    public void setNoCalculatedNum(Integer noCalculatedNum) {
        this.noCalculatedNum = noCalculatedNum;
    }

    public Integer getCalculatedThroughNum() {
        return calculatedThroughNum;
    }

    public void setCalculatedThroughNum(Integer calculatedThroughNum) {
        this.calculatedThroughNum = calculatedThroughNum;
    }

    public Integer getTalksMadeNum() {
        return talksMadeNum;
    }

    public void setTalksMadeNum(Integer talksMadeNum) {
        this.talksMadeNum = talksMadeNum;
    }

    public Integer getNoTalksMadeNum() {
        return noTalksMadeNum;
    }

    public void setNoTalksMadeNum(Integer noTalksMadeNum) {
        this.noTalksMadeNum = noTalksMadeNum;
    }

    public Integer getCooperationNum() {
        return cooperationNum;
    }

    public void setCooperationNum(Integer cooperationNum) {
        this.cooperationNum = cooperationNum;
    }

    public Integer getDiscardedNum() {
        return discardedNum;
    }

    public void setDiscardedNum(Integer discardedNum) {
        this.discardedNum = discardedNum;
    }

    public Integer getIntroduceOtherNum() {
        return IntroduceOtherNum;
    }

    public void setIntroduceOtherNum(Integer introduceOtherNum) {
        IntroduceOtherNum = introduceOtherNum;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getIntermediaryFee() {
        return intermediaryFee;
    }

    public void setIntermediaryFee(Double intermediaryFee) {
        this.intermediaryFee = intermediaryFee;
    }
}