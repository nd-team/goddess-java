package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.CooperationType;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 项目基本信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_projectbasicinfo")
public class ProjectBasicInfo extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;

    /**
     * 工作量
     */
    @Column(name = "workload", nullable = false, columnDefinition = "INT(11) COMMENT '工作量'")
    private Integer workload;

    /**
     * 项目开展成本
     */
    @Column(name = "projectLaunchCost", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '项目开展成本'")
    private Double projectLaunchCost;

    /**
     * 金额
     */
    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '金额'")
    private Double amount;

    /**
     * 合作方式
     */
    @Column(name = "cooperationType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '合作方式'")
    private CooperationType cooperationType;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 起始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME COMMENT '起始时间'")
    private LocalDateTime startTime;

    /**
     * 经历时间
     */
    @Column(name = "duration", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '经历时间'")
    private String duration;

    /**
     * 人工
     */
    @Column(name = "labour", nullable = false, columnDefinition = "INT(11) COMMENT '人工'")
    private Integer labour;

    /**
     * 人员数量
     */
    @Column(name = "numberOfStaff", nullable = false, columnDefinition = "INT(11) COMMENT '人员数量'")
    private Integer numberOfStaff;

    /**
     * 设备费用
     */
    @Column(name = "deviceCharge", columnDefinition = "DECIMAL(10,2) COMMENT '设备费用'")
    private Double deviceCharge;

    /**
     * 车辆费用
     */
    @Column(name = "vehicleCharge", columnDefinition = "DECIMAL(10,2) COMMENT '车辆费用'")
    private Double vehicleCharge;

    /**
     * 配置费用
     */
    @Column(name = "configCharge", columnDefinition = "DECIMAL(10,2) COMMENT '配置费用'")
    private Double configCharge;

    /**
     * 其他费用
     */
    @Column(name = "otherCharge", columnDefinition = "DECIMAL(10,2) COMMENT '其他费用'")
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
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