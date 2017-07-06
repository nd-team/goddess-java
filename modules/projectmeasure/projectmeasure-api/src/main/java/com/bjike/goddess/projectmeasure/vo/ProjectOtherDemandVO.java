package com.bjike.goddess.projectmeasure.vo;

/**
 * 其他需求界面表现层对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOtherDemandVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 设备费用
     */
    private Double deviceCharge;

    /**
     * 配置费用
     */
    private Double configCharge;

    /**
     * 车辆费用
     */
    private Double vehicleCharge;

    /**
     * 其他费用
     */
    private Double otherCharge;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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