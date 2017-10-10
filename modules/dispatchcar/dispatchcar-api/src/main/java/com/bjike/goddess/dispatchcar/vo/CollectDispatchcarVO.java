package com.bjike.goddess.dispatchcar.vo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-07 15:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDispatchcarVO {
    /**
     * 名称
     */
    private String name;

    /**
     * 日期
     */
    private String date;

    /**
     * 租车单价
     */
    private Double rentcarUnitCost;

    /**
     * 加班时长
     */
    private Double overTimeWork;

    /**
     * 加班费
     */
    private Double overTimeWorkPrice;

    /**
     * 停车费
     */
    private Double parkCost;

    /**
     * 过路费
     */
    private Double roadCost;

    /**
     * 餐费补贴
     */
    private Double mealCost;

    /**
     * 合计1
     */
    private Double totalOne;


    /**
     * 加油费
     */
    private Double addOilAmountMoney;

    /**
     * 合计2
     */
    private Double totalTwo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRentcarUnitCost() {
        return rentcarUnitCost;
    }

    public void setRentcarUnitCost(Double rentcarUnitCost) {
        this.rentcarUnitCost = rentcarUnitCost;
    }

    public Double getOverTimeWork() {
        return overTimeWork;
    }

    public void setOverTimeWork(Double overTimeWork) {
        this.overTimeWork = overTimeWork;
    }

    public Double getOverTimeWorkPrice() {
        return overTimeWorkPrice;
    }

    public void setOverTimeWorkPrice(Double overTimeWorkPrice) {
        this.overTimeWorkPrice = overTimeWorkPrice;
    }

    public Double getParkCost() {
        return parkCost;
    }

    public void setParkCost(Double parkCost) {
        this.parkCost = parkCost;
    }

    public Double getRoadCost() {
        return roadCost;
    }

    public void setRoadCost(Double roadCost) {
        this.roadCost = roadCost;
    }

    public Double getMealCost() {
        return mealCost;
    }

    public void setMealCost(Double mealCost) {
        this.mealCost = mealCost;
    }

    public Double getTotalOne() {
        return totalOne;
    }

    public void setTotalOne(Double totalOne) {
        this.totalOne = totalOne;
    }

    public Double getAddOilAmountMoney() {
        return addOilAmountMoney;
    }

    public void setAddOilAmountMoney(Double addOilAmountMoney) {
        this.addOilAmountMoney = addOilAmountMoney;
    }

    public Double getTotalTwo() {
        return totalTwo;
    }

    public void setTotalTwo(Double totalTwo) {
        this.totalTwo = totalTwo;
    }
}

