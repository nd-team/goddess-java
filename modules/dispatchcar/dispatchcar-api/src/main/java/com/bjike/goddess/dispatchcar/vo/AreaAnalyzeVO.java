package com.bjike.goddess.dispatchcar.vo;

/**
 * 财务出车地区分析
 *
 * @Author: [Jason]
 * @Date: [17-4-18 下午4:59]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaAnalyzeVO {

    /**
     * 地区
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

}
