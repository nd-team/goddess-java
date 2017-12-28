package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 设备搭建环境信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-28 09:34 ]
 * @Description: [ 设备搭建环境信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquipmentEnvironmentInfoBO extends BaseBO {

    /**
     * 建筑类型
     */
    private String buildType;

    /**
     * 空间大小
     */
    private String size;

    /**
     * 地区供电情况
     */
    private String areaPowerSupply;

    /**
     * 供电价格
     */
    private Double powerSupplyPrice;

    /**
     * 空调排水
     */
    private String airConditionDrainage;

    /**
     * 接地
     */
    private String grounding;

    /**
     * 传输路由
     */
    private String transportRoutes;

    /**
     * 承重
     */
    private String bearing;

    /**
     * 手机网络信号情况
     */
    private String aobilePhoneSignal;

    /**
     * 周围辐射情况
     */
    private String ambientRadiationConditions;
    /**
     * 业主客户信息
     */
    private CustomerAndOwnerInfoBO customerAndOwnerInfoBO;

    public CustomerAndOwnerInfoBO getCustomerAndOwnerInfoBO() {
        return customerAndOwnerInfoBO;
    }

    public void setCustomerAndOwnerInfoBO(CustomerAndOwnerInfoBO customerAndOwnerInfoBO) {
        this.customerAndOwnerInfoBO = customerAndOwnerInfoBO;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAreaPowerSupply() {
        return areaPowerSupply;
    }

    public void setAreaPowerSupply(String areaPowerSupply) {
        this.areaPowerSupply = areaPowerSupply;
    }

    public Double getPowerSupplyPrice() {
        return powerSupplyPrice;
    }

    public void setPowerSupplyPrice(Double powerSupplyPrice) {
        this.powerSupplyPrice = powerSupplyPrice;
    }

    public String getAirConditionDrainage() {
        return airConditionDrainage;
    }

    public void setAirConditionDrainage(String airConditionDrainage) {
        this.airConditionDrainage = airConditionDrainage;
    }

    public String getGrounding() {
        return grounding;
    }

    public void setGrounding(String grounding) {
        this.grounding = grounding;
    }

    public String getTransportRoutes() {
        return transportRoutes;
    }

    public void setTransportRoutes(String transportRoutes) {
        this.transportRoutes = transportRoutes;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getAobilePhoneSignal() {
        return aobilePhoneSignal;
    }

    public void setAobilePhoneSignal(String aobilePhoneSignal) {
        this.aobilePhoneSignal = aobilePhoneSignal;
    }

    public String getAmbientRadiationConditions() {
        return ambientRadiationConditions;
    }

    public void setAmbientRadiationConditions(String ambientRadiationConditions) {
        this.ambientRadiationConditions = ambientRadiationConditions;
    }
}