package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 模块指标表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 02:36 ]
 * @Description: [ 模块指标表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndicatorImportTempBO extends BaseBO {

    /**
     * 指标序号
     */
    private String number;

    /**
     * 指标类型
     */
    private String type;

    /**
     * 考核指标
     */
    private String assessment;

    /**
     * 目标值
     */
    private String targetValue;

    /**
     * 体现
     */
    private String reflect;

    /**
     * 达到指标对赌分
     */
    private Long gambling;

    /**
     * 未达到指标对赌分
     */
    private Long unGambling;

    /**
     * 是否达到指标
     */
    private Boolean isReach;

    /**
     * 要求方
     */
    private String requestSide;

    /**
     * 对赌方
     */
    private String gamblingSide;

    /**
     * 规则
     */
    private String rule;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getReflect() {
        return reflect;
    }

    public void setReflect(String reflect) {
        this.reflect = reflect;
    }

    public Long getGambling() {
        return gambling;
    }

    public void setGambling(Long gambling) {
        this.gambling = gambling;
    }

    public Long getUnGambling() {
        return unGambling;
    }

    public void setUnGambling(Long unGambling) {
        this.unGambling = unGambling;
    }

    public Boolean getReach() {
        return isReach;
    }

    public void setReach(Boolean reach) {
        isReach = reach;
    }

    public String getRequestSide() {
        return requestSide;
    }

    public void setRequestSide(String requestSide) {
        this.requestSide = requestSide;
    }

    public String getGamblingSide() {
        return gamblingSide;
    }

    public void setGamblingSide(String gamblingSide) {
        this.gamblingSide = gamblingSide;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}