package com.bjike.goddess.dispatchcar.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-28 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DispatchcarInfoCollectSetExcel implements Serializable{
    /**
     * 司机
     */
    @ExcelHeader(name = "司机")
    private String driver;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 项目组
     */
    @ExcelHeader(name = "项目组",notNull = true)
    private String group;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称",notNull = true)
    private String projject;

    /**
     * 公司出车总量
     */
    @ExcelHeader(name = "公司出车总量",notNull = true)
    private Long companyDispatch;

    /**
     * 非公司出车总量
     */
    @ExcelHeader(name = "非公司出车总量",notNull = true)
    private Long uncompanyDispatch;

    /**
     * 本间隔出车量
     */
    @ExcelHeader(name = "本间隔出车量",notNull = true)
    private Long currentDispatch;

    /**
     * 上一间隔出车量
     */
    @ExcelHeader(name = "上一间隔出车量",notNull = true)
    private Long lastDispatch;

    /**
     * 出车量差异对比
     */
    @ExcelHeader(name = "出车量差异对比",notNull = true)
    private Long dispatchSubtract;

    /**
     * 租车费
     */
    @ExcelHeader(name = "租车费",notNull = true)
    private Double leaseCarCost;

    /**
     * 停车费
     */
    @ExcelHeader(name = "停车费",notNull = true)
    private Double parkCarCost;

    /**
     * 过路费
     */
    @ExcelHeader(name = "过路费",notNull = true)
    private Double roadCost;

    /**
     * 餐补费
     */
    @ExcelHeader(name = "餐补费",notNull = true)
    private Double mealCost;

    /**
     * 本间隔费用
     */
    @ExcelHeader(name = "本间隔费用",notNull = true)
    private Double currentCost;

    /**
     * 上一间隔费用
     */
    @ExcelHeader(name = "上一间隔费用",notNull = true)
    private Double lastCost;

    /**
     * 费用差异对比
     */
    @ExcelHeader(name = "费用差异对比",notNull = true)
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

    public String getProjject() {
        return projject;
    }

    public void setProjject(String projject) {
        this.projject = projject;
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
