package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.CooperationType;

import javax.print.DocFlavor;
import java.time.LocalDateTime;

/**
 * 项目基本信息业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectBasicInfoBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 工作量
     */
    private Integer workload;

    /**
     * 项目开展成本
     */
    private Double projectLaunchCost;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 合作方式
     */
    private CooperationType cooperationType;

    /**
     * 地区
     */
    private String area;

    /**
     * 起始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 经历时间
     */
    private String duration;

    /**
     * 人工
     */
    private Integer labour;

    /**
     * 人员数量
     */
    private Integer numberOfStaff;

    /**
     * 工期
     */
    private String timeLimit;

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

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Double getProjectLaunchCost() {
        return projectLaunchCost;
    }

    public void setProjectLaunchCost(Double projectLaunchCost) {
        this.projectLaunchCost = projectLaunchCost;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CooperationType getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(CooperationType cooperationType) {
        this.cooperationType = cooperationType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getLabour() {
        return labour;
    }

    public void setLabour(Integer labour) {
        this.labour = labour;
    }

    public Integer getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(Integer numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
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