package com.bjike.goddess.reportmanagement.vo;

/**
 * 资产结构管理建议设计表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetStructureAdviceVO {

    /**
     * id
     */
    private String id;
    /**
     * 流动资产比例最小值
     */
    private Double flowMin;

    /**
     * 流动资产比例最大值
     */
    private Double flowMax;

    /**
     * 非流动资产比例最小值
     */
    private Double notFlowMin;

    /**
     * 非流动资产比例最大值
     */
    private Double notFlowMax;

    /**
     * 管理建议
     */
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