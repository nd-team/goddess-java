package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 模块指标表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 02:36 ]
 * @Description: [ 模块指标表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndicatorTO extends BaseTO {

    /**
     * 模块表id
     */
//    @NotBlank(message = "模块表id不能为空", groups = {EDIT.class})
    private String modulesId;

    /**
     * 指标序号
     */
    @NotBlank(message = "指标序号不能为空", groups = {ADD.class, EDIT.class})
    private String number;

    /**
     * 指标类型
     */
    @NotNull(message = "指标类型不能为空", groups = {ADD.class, EDIT.class})
    private Boolean type;

    /**
     * 考核指标
     */
    @NotBlank(message = "考核指标不能为空", groups = {ADD.class, EDIT.class})
    private String assessment;

    /**
     * 目标值
     */
    @NotBlank(message = "目标值不能为空", groups = {ADD.class, EDIT.class})
    private String targetValue;

    /**
     * 体现
     */
    @NotBlank(message = "体现不能为空", groups = {ADD.class, EDIT.class})
    private String reflect;

    /**
     * 达到指标对赌分
     */
    @NotNull(message = "达到指标对赌分不能为空", groups = {ADD.class, EDIT.class})
    private Long gambling;

    /**
     * 未达到指标对赌分
     */
    @NotNull(message = "未达到指标对赌分不能为空", groups = {ADD.class, EDIT.class})
    private Long unGambling;

    /**
     * 是否达到指标
     */
    @NotNull(message = "是否达到指标不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isReach;

    /**
     * 要求方
     */
    @NotBlank(message = "要求方不能为空", groups = {ADD.class, EDIT.class})
    private String requestSide;

    /**
     * 对赌方
     */
    @NotBlank(message = "对赌方不能为空", groups = {ADD.class, EDIT.class})
    private String gamblingSide;

    /**
     * 规则
     */
    @NotBlank(message = "规则不能为空", groups = {ADD.class, EDIT.class})
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