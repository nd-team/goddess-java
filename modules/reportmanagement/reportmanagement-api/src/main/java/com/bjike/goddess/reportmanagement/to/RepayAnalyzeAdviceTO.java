package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 偿还能力分析管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:04 ]
 * @Description: [ 偿还能力分析管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RepayAnalyzeAdviceTO extends BaseTO {

    /**
     * 流动率最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "流动率最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "流动率最小值不能小于0")
    private Double flowMin;

    /**
     * 流动率最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "流动率最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "流动率最大值不能小于0")
    private Double flowMax;

    /**
     * 速动率最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "速动率最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "速动率最小值不能小于0")
    private Double rateMin;

    /**
     * 速动率最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "速动率最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "速动率最大值不能小于0")
    private Double rateMax;

    /**
     * 现金率最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "现金率最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "现金率最小值不能小于0")
    private Double moneyMin;

    /**
     * 现金率最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "现金率最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "现金率最大值不能小于0")
    private Double moneyMax;

    /**
     * 资产负债率最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "资产负债率最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "资产负债率最小值不能小于0")
    private Double assestMin;

    /**
     * 资产负债率最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "资产负债率最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "资产负债率最大值不能小于0")
    private Double assestMax;

    /**
     * 产权率最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "产权率最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "产权率最小值不能小于0")
    private Double equityMin;

    /**
     * 产权率最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "产权率最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "产权率最大值不能小于0")
    private Double equityMax;

    /**
     * 管理建议
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "管理建议不能为空")
    private String advice;


    public Double getFlowMin() {
        return flowMin;
    }

    public void setFlowMin(Double flowMin) {
        this.flowMin = flowMin;
    }

    public Double getFlowMax() {
        return flowMax;
    }

    public void setFlowMax(Double flowMax) {
        this.flowMax = flowMax;
    }

    public Double getRateMin() {
        return rateMin;
    }

    public void setRateMin(Double rateMin) {
        this.rateMin = rateMin;
    }

    public Double getRateMax() {
        return rateMax;
    }

    public void setRateMax(Double rateMax) {
        this.rateMax = rateMax;
    }

    public Double getMoneyMin() {
        return moneyMin;
    }

    public void setMoneyMin(Double moneyMin) {
        this.moneyMin = moneyMin;
    }

    public Double getMoneyMax() {
        return moneyMax;
    }

    public void setMoneyMax(Double moneyMax) {
        this.moneyMax = moneyMax;
    }

    public Double getAssestMin() {
        return assestMin;
    }

    public void setAssestMin(Double assestMin) {
        this.assestMin = assestMin;
    }

    public Double getAssestMax() {
        return assestMax;
    }

    public void setAssestMax(Double assestMax) {
        this.assestMax = assestMax;
    }

    public Double getEquityMin() {
        return equityMin;
    }

    public void setEquityMin(Double equityMin) {
        this.equityMin = equityMin;
    }

    public Double getEquityMax() {
        return equityMax;
    }

    public void setEquityMax(Double equityMax) {
        this.equityMax = equityMax;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}