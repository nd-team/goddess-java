package com.bjike.goddess.dispatchcar.vo;

/**
 * 出车记录汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午3:07]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DispatchDriverCollectVO {

    /**
     * 司机
     */
    private String driver;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String group;

    /**
     * 公司出车总量
     */
    private Long companyDispatch;

    /**
     * 非公司出车总量
     */
    private Long uncompanyDispatch;

    /**
     * 本间隔出车量
     */
    private Long currentDispatch;

    /**
     * 上一间隔出车量
     */
    private Long lastDispatch;

    /**
     * 出车量差异对比
     */
    private Long dispatchSubtract;

    /**
     * 租车费
     */
    private Double leaseCarCost;

    /**
     * 停车费
     */
    private Double parkCarCost;

    /**
     * 过路费
     */
    private Double roadCost;

    /**
     * 餐补费
     */
    private Double mealCost;

    /**
     * 本间隔费用
     */
    private Double currentCost;

    /**
     * 上一间隔费用
     */
    private Double lastCost;

    /**
     * 费用差异对比
     */
    private Double costSubtract;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getCompanyDispatch() {
        return companyDispatch;
    }

    public void setCompanyDispatch(Long companyDispatch) {
        this.companyDispatch = companyDispatch;
    }

    public Long getUncompanyDispatch() {
        return uncompanyDispatch;
    }

    public void setUncompanyDispatch(Long uncompanyDispatch) {
        this.uncompanyDispatch = uncompanyDispatch;
    }

    public Long getCurrentDispatch() {
        return currentDispatch;
    }

    public void setCurrentDispatch(Long currentDispatch) {
        this.currentDispatch = currentDispatch;
    }

    public Long getLastDispatch() {
        return lastDispatch;
    }

    public void setLastDispatch(Long lastDispatch) {
        this.lastDispatch = lastDispatch;
    }

    public Long getDispatchSubtract() {
        return dispatchSubtract;
    }

    public void setDispatchSubtract(Long dispatchSubtract) {
        this.dispatchSubtract = dispatchSubtract;
    }

    public Double getLeaseCarCost() {
        return leaseCarCost;
    }

    public void setLeaseCarCost(Double leaseCarCost) {
        this.leaseCarCost = leaseCarCost;
    }

    public Double getParkCarCost() {
        return parkCarCost;
    }

    public void setParkCarCost(Double parkCarCost) {
        this.parkCarCost = parkCarCost;
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
}
