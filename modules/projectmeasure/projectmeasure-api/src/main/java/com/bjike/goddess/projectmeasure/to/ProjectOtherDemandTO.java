package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 其他需求界面
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectOtherDemandTO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 设备费用
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "设备费用不能为空")
    private Double deviceCharge;

    /**
     * 配置费用
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "配置费用不能为空")
    private Double configCharge;

    /**
     * 车辆费用
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "车辆费用不能为空")
    private Double vehicleCharge;

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