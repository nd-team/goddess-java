package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 资产结构管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetStructureAdviceTO extends BaseTO {

    /**
     * 流动资产比例最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "流动资产比例最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "流动资产比例最小值不能小于0")
    private Double flowMin;

    /**
     * 流动资产比例最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "流动资产比例最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "流动资产比例最大值不能小于0")
    private Double flowMax;

    /**
     * 非流动资产比例最小值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "非流动资产比例最小值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "非流动资产比例最小值不能小于0")
    private Double notFlowMin;

    /**
     * 非流动资产比例最大值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "非流动资产比例最大值不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "非流动资产比例最大值不能小于0")
    private Double notFlowMax;

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

    public Double getNotFlowMin() {
        return notFlowMin;
    }

    public void setNotFlowMin(Double notFlowMin) {
        this.notFlowMin = notFlowMin;
    }

    public Double getNotFlowMax() {
        return notFlowMax;
    }

    public void setNotFlowMax(Double notFlowMax) {
        this.notFlowMax = notFlowMax;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}