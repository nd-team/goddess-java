package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 需求成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 10:50 ]
 * @Description: [ 需求成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DemandCostTO extends BaseTO {

    /**
     * 设备费用
     */
    private Double equipmentSalary;

    /**
     * 配置费用
     */
    private Double configSalary;

    /**
     * 车辆费用
     */
    private Double carSalary;

    /**
     * 其他费用
     */
    private Double another;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    public Double getEquipmentSalary() {
        return equipmentSalary;
    }

    public void setEquipmentSalary(Double equipmentSalary) {
        this.equipmentSalary = equipmentSalary;
    }

    public Double getConfigSalary() {
        return configSalary;
    }

    public void setConfigSalary(Double configSalary) {
        this.configSalary = configSalary;
    }

    public Double getCarSalary() {
        return carSalary;
    }

    public void setCarSalary(Double carSalary) {
        this.carSalary = carSalary;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}