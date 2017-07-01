package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 利润增减率分析管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitRegulationAdviceTO extends BaseTO {

    /**
     * 净利润增减最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "净利润增减最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "净利润增减最小值不能小于0")
    private Double netProfitMin;

    /**
     * 净利润增减最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "净利润增减最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "净利润增减最大值不能小于0")
    private Double netProfitMax;

    /**
     * 利润总额增减最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "利润总额增减最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "利润总额增减最小值不能小于0")
    private Double profitMin;

    /**
     * 利润总额增减最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "利润总额增减最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "利润总额增减最大值不能小于0")
    private Double profitMax;

    /**
     * 营业利润增减最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "营业利润增减最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "营业利润增减最小值不能小于0")
    private Double incomeMin;

    /**
     * 营业利润增减最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "营业利润增减最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "营业利润增减最大值不能小于0")
    private Double incomeMax;

    /**
     * 管理建议
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "管理建议不能为空")
    private String advice;


    public Double getNetProfitMin() {
        return netProfitMin;
    }

    public void setNetProfitMin(Double netProfitMin) {
        this.netProfitMin = netProfitMin;
    }

    public Double getNetProfitMax() {
        return netProfitMax;
    }

    public void setNetProfitMax(Double netProfitMax) {
        this.netProfitMax = netProfitMax;
    }

    public Double getProfitMin() {
        return profitMin;
    }

    public void setProfitMin(Double profitMin) {
        this.profitMin = profitMin;
    }

    public Double getProfitMax() {
        return profitMax;
    }

    public void setProfitMax(Double profitMax) {
        this.profitMax = profitMax;
    }

    public Double getIncomeMin() {
        return incomeMin;
    }

    public void setIncomeMin(Double incomeMin) {
        this.incomeMin = incomeMin;
    }

    public Double getIncomeMax() {
        return incomeMax;
    }

    public void setIncomeMax(Double incomeMax) {
        this.incomeMax = incomeMax;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}