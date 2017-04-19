package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 财务分析
 *
 * @Author: [Jason]
 * @Date: [17-4-18 下午3:32]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FinanceAnalyzeBO extends BaseBO {

    /**
     * 分析公共字段
     */
    private String analyzeField;

    /**
     * 本月费用
     */
    private Double currentCost;

    /**
     * 上月费用
     */
    private Double lastCost;

    /**
     * 差额
     */
    private Double costSubtract;

    /**
     * 增长比
     */
    private Double growRate;

    /**
     * 百分比
     */
    private String percent;

    public String getAnalyzeField() {
        return analyzeField;
    }

    public void setAnalyzeField(String analyzeField) {
        this.analyzeField = analyzeField;
    }

    public Double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(Double currentCost) {
        this.currentCost = currentCost;
    }

    public Double getLastCost() {
        return lastCost;
    }

    public void setLastCost(Double lastCost) {
        this.lastCost = lastCost;
    }

    public Double getCostSubtract() {
        return costSubtract;
    }

    public void setCostSubtract(Double costSubtract) {
        this.costSubtract = costSubtract;
    }

    public Double getGrowRate() {
        return growRate;
    }

    public void setGrowRate(Double growRate) {
        this.growRate = growRate;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public FinanceAnalyzeBO() {
    }

    public FinanceAnalyzeBO(String analyzeField, Double currentCost, Double lastCost, Double costSubtract, Double growRate, String percent) {
        this.analyzeField = analyzeField;
        this.currentCost = currentCost;
        this.lastCost = lastCost;
        this.costSubtract = costSubtract;
        this.growRate = growRate;
        this.percent = percent;
    }
}
