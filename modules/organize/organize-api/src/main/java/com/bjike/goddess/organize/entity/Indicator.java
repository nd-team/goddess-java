package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 模块指标表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 02:36 ]
 * @Description: [ 模块指标表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "organize_indicator")
public class Indicator extends BaseEntity {

    /**
     * 模块表id
     */
    @Column(name = "modulesId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块表id'")
    private String modulesId;

    /**
     * 指标序号
     */
    @Column(name = "number", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标序号'")
    private String number;

    /**
     * 指标类型
     */
    @Column(name = "is_type", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '指标类型'", insertable = false)
    private Boolean type;

    /**
     * 考核指标
     */
    @Column(name = "assessment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '考核指标'")
    private String assessment;

    /**
     * 目标值
     */
    @Column(name = "targetValue", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '目标值'")
    private String targetValue;

    /**
     * 体现
     */
    @Column(name = "reflect", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体现'")
    private String reflect;

    /**
     * 达到指标对赌分
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '达到指标对赌分'")
    private Long gambling;

    /**
     * 未达到指标对赌分
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未达到指标对赌分'")
    private Long unGambling;

    /**
     * 是否达到指标
     */
    @Column(name = "is_isReach", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否达到指标'", insertable = false)
    private Boolean isReach;

    /**
     * 要求方
     */
    @Column(name = "requestSide", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '要求方'")
    private String requestSide;

    /**
     * 对赌方
     */
    @Column(name = "gamblingSide", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '对赌方'")
    private String gamblingSide;

    /**
     * 规则
     */
    @Column(name = "rule", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '规则'")
    private String rule;


    public String getModulesId() {
        return modulesId;
    }

    public void setModulesId(String modulesId) {
        this.modulesId = modulesId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
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