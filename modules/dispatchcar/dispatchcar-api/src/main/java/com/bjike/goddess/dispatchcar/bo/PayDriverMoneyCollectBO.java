package com.bjike.goddess.dispatchcar.bo;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 14:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PayDriverMoneyCollectBO extends BaseTO{
    /**
     * 司机
     */
    private String driver;

    /**
     * 日期
     */
    private String payDate;

    /**
     * 租车费
     */
    private Double carRentalCost;

    /**
     * 加班单价
     */
    private Double overUnitCost;


    /**
     * 加班时长
     */
    private Double overWorkTime;

    /**
     * 加班费
     */
    private Double overWorkCost;

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
     * 合计
     */
    private Double totalCost;


    public Double getOverWorkCost() {
        return overWorkCost;
    }

    public void setOverWorkCost(Double overWorkCost) {
        this.overWorkCost = overWorkCost;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Double getCarRentalCost() {
        return carRentalCost;
    }

    public void setCarRentalCost(Double carRentalCost) {
        this.carRentalCost = carRentalCost;
    }

    public Double getOverUnitCost() {
        return overUnitCost;
    }

    public void setOverUnitCost(Double overUnitCost) {
        this.overUnitCost = overUnitCost;
    }

    public Double getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Double overWorkTime) {
        this.overWorkTime = overWorkTime;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }


    public PayDriverMoneyCollectBO(String driver,String payDate,Double carRentalCost,Double overUnitCost,Double overWorkTime,Double overWorkCost,Double parkCost,Double roadCost,Double mealCost,String id){
        this.driver = driver;
        this.payDate = payDate;
        this.carRentalCost = carRentalCost;
        this.overUnitCost = overUnitCost;
        this.overWorkTime = overWorkTime;
        this.parkCost = parkCost;
        this.roadCost = roadCost;
        this.mealCost = mealCost;
        this.id= id;
    }
}
