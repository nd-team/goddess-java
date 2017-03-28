package com.bjike.goddess.headcount.vo;

/**
 * 薪资区间信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-10 09:34 ]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class IntervalInfoVO{
    /**
     * 薪资区间
     */
    private String intervals;
    /**
     * 计划人数
     */
    private Integer planNumber;
    /**
     * 计划成本
     */
    
    private Double planMoney;
    /**
     * 现有人数
     */
    private Integer currentNumber;
    /**
     * 现有成本
     */
    private Double currentlyMoney;
    /**
     * 人工成本计划id
     */
    private String planCosts_id;

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public Integer getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(Integer planNumber) {
        this.planNumber = planNumber;
    }


    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Double getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Double planMoney) {
        this.planMoney = planMoney;
    }

    public Double getCurrentlyMoney() {
        return currentlyMoney;
    }

    public void setCurrentlyMoney(Double currentlyMoney) {
        this.currentlyMoney = currentlyMoney;
    }

    public String getPlanCosts_id() {
        return planCosts_id;
    }

    public void setPlanCosts_id(String planCosts_id) {
        this.planCosts_id = planCosts_id;
    }
}
