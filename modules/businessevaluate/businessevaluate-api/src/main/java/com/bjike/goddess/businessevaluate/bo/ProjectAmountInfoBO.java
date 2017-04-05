package com.bjike.goddess.businessevaluate.bo;

import java.io.Serializable;

/**
 * 项目金额信息
 *
 * @Author: [Jason]
 * @Date: [17-3-29 下午4:27]
 * @Description: [项目金额信息]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectAmountInfoBO implements Serializable {

    /**
     * 成本
     */
    private Double cost;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 管理费
     */
    private Double manageFee;

    /**
     * 费用
     */
    private Double fee;

    /**
     * 利润
     */
    private Double profit;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
