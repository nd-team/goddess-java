package com.bjike.goddess.dispatchcar.vo;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-27 15:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PayedCollectVO {
    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 加油费
     */
    private Double supplementFee;

    /**
     * 租车单价
     */
    private Double carRentalUtilCost;

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
    private Double total;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getSupplementFee() {
        return supplementFee;
    }

    public void setSupplementFee(Double supplementFee) {
        this.supplementFee = supplementFee;
    }

    public Double getCarRentalUtilCost() {
        return carRentalUtilCost;
    }

    public void setCarRentalUtilCost(Double carRentalUtilCost) {
        this.carRentalUtilCost = carRentalUtilCost;
    }

    public Double getOverWorkTime() {
        return overWorkTime;
    }

    public void setOverWorkTime(Double overWorkTime) {
        this.overWorkTime = overWorkTime;
    }

    public Double getOverWorkCost() {
        return overWorkCost;
    }

    public void setOverWorkCost(Double overWorkCost) {
        this.overWorkCost = overWorkCost;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
