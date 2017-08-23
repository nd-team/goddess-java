package com.bjike.goddess.projectcost.vo;

/**
 * 项目成本控制柱状图业务传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-05-09 17:51]
 * @Description: [ 项目成本控制柱状图业务传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HistogramVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 目标收入
     */
    private Double targetIncome;

    /**
     * 实际收入
     */
    private Double actualIncome;

    /**
     * 目标人工成本
     */
    private Double targetCost;

    /**
     * 实际人工成本
     */
    private Double actualCost;

    /**
     * 目标车辆费用
     */
    private Double targetCar;

    /**
     * 实际车辆费用
     */
    private Double actualCar;

    /**
     * 目标其他费用
     */
    private Double targetOther;

    /**
     * 实际其他费用
     */
    private Double actualOther;

    /**
     * 目标费用合计
     */
    private Double targetTotal;

    /**
     * 实际费用合计
     */
    private Double actualTotal;

    /**
     * 目标利润
     */
    private Double targetProfit;

    /**
     * 实际利润
     */
    private Double actualProfit;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(Double targetIncome) {
        this.targetIncome = targetIncome;
    }

    public Double getActualIncome() {
        return actualIncome;
    }

    public void setActualIncome(Double actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Double getTargetCost() {
        return targetCost;
    }

    public void setTargetCost(Double targetCost) {
        this.targetCost = targetCost;
    }

    public Double getActualCost() {
        return actualCost;
    }

    public void setActualCost(Double actualCost) {
        this.actualCost = actualCost;
    }

    public Double getTargetCar() {
        return targetCar;
    }

    public void setTargetCar(Double targetCar) {
        this.targetCar = targetCar;
    }

    public Double getActualCar() {
        return actualCar;
    }

    public void setActualCar(Double actualCar) {
        this.actualCar = actualCar;
    }

    public Double getTargetOther() {
        return targetOther;
    }

    public void setTargetOther(Double targetOther) {
        this.targetOther = targetOther;
    }

    public Double getActualOther() {
        return actualOther;
    }

    public void setActualOther(Double actualOther) {
        this.actualOther = actualOther;
    }

    public Double getTargetTotal() {
        return targetTotal;
    }

    public void setTargetTotal(Double targetTotal) {
        this.targetTotal = targetTotal;
    }

    public Double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Double getTargetProfit() {
        return targetProfit;
    }

    public void setTargetProfit(Double targetProfit) {
        this.targetProfit = targetProfit;
    }

    public Double getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(Double actualProfit) {
        this.actualProfit = actualProfit;
    }
}
