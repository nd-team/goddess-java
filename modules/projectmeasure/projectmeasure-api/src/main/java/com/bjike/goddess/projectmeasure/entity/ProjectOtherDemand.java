package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 其他需求界面
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_projectotherdemand")
public class ProjectOtherDemand extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 设备费用
     */
    @Column(name = "deviceCharge", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '设备费用'")
    private Double deviceCharge;

    /**
     * 配置费用
     */
    @Column(name = "configCharge", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '配置费用'")
    private Double configCharge;

    /**
     * 车辆费用
     */
    @Column(name = "vehicleCharge", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '车辆费用'")
    private Double vehicleCharge;

    /**
     * 其他费用
     */
    @Column(name = "otherCharge", columnDefinition = "DECIMAL(10,2)   COMMENT '其他费用'")
    private Double otherCharge;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getDeviceCharge() {
        return deviceCharge;
    }

    public void setDeviceCharge(Double deviceCharge) {
        this.deviceCharge = deviceCharge;
    }

    public Double getConfigCharge() {
        return configCharge;
    }

    public void setConfigCharge(Double configCharge) {
        this.configCharge = configCharge;
    }

    public Double getVehicleCharge() {
        return vehicleCharge;
    }

    public void setVehicleCharge(Double vehicleCharge) {
        this.vehicleCharge = vehicleCharge;
    }

    public Double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Double otherCharge) {
        this.otherCharge = otherCharge;
    }
}