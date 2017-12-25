package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 输出评估结果业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 输出评估结果业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectEvaluateResultBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 工作界面
     */
    private String workInterface;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 回款时间
     */
    private String backDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 工期
     */
    private String timeLimit;

    /**
     * 人工成本
     */
    private Double labour;

    /**
     * 耗时费用
     */
    private Double consumptionCosts;

    /**
     * 总成本
     */
    private Double totalCost;

    /**
     * 利润
     */
    private Double profit;

    /**
     * 服务费用
     */
    private Double serviceCharge;

    /**
     * 提成
     */
    private Double royalties;

    /**
     * 招待费
     */
    private Double serveCharge;

    /**
     * 设备费用
     */
    private Double deviceCharge;

    /**
     * 车辆费用
     */
    private Double vehicleCharge;

    /**
     * 配置费用
     */
    private Double configCharge;

    /**
     * 其他费用
     */
    private Double otherCharge;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWorkInterface() {
        return workInterface;
    }

    public void setWorkInterface(String workInterface) {
        this.workInterface = workInterface;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Double getLabour() {
        return labour;
    }

    public void setLabour(Double labour) {
        this.labour = labour;
    }

    public Double getConsumptionCosts() {
        return consumptionCosts;
    }

    public void setConsumptionCosts(Double consumptionCosts) {
        this.consumptionCosts = consumptionCosts;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getRoyalties() {
        return royalties;
    }

    public void setRoyalties(Double royalties) {
        this.royalties = royalties;
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Double getDeviceCharge() {
        return deviceCharge;
    }

    public void setDeviceCharge(Double deviceCharge) {
        this.deviceCharge = deviceCharge;
    }

    public Double getVehicleCharge() {
        return vehicleCharge;
    }

    public void setVehicleCharge(Double vehicleCharge) {
        this.vehicleCharge = vehicleCharge;
    }

    public Double getConfigCharge() {
        return configCharge;
    }

    public void setConfigCharge(Double configCharge) {
        this.configCharge = configCharge;
    }

    public Double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Double otherCharge) {
        this.otherCharge = otherCharge;
    }
}
