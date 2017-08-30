package com.bjike.goddess.reportmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 资产结构管理建议设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reportmanagement_assetstructureadvice")
public class AssetStructureAdvice extends BaseEntity {

    /**
     * 流动资产比例最小值
     */
    @Column(name = "flowMin", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '流动资产比例最小值'")
    private Double flowMin;

    /**
     * 流动资产比例最大值
     */
    @Column(name = "flowMax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '流动资产比例最大值'")
    private Double flowMax;

    /**
     * 非流动资产比例最小值
     */
    @Column(name = "notFlowMin", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '非流动资产比例最小值'")
    private Double notFlowMin;

    /**
     * 非流动资产比例最大值
     */
    @Column(name = "notFlowMax", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '非流动资产比例最大值'")
    private Double notFlowMax;

    /**
     * 管理建议
     */
    @Column(name = "advice", nullable = false, columnDefinition = "MEDIUMTEXT   COMMENT '管理建议'")
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