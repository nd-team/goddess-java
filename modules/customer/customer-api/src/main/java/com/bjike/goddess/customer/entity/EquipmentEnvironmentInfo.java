package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 设备搭建环境信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-28 09:34 ]
 * @Description: [ 设备搭建环境信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_equipmentenvironmentinfo")
public class EquipmentEnvironmentInfo extends BaseEntity {

    /**
     * 建筑类型
     */
    @Column(name = "buildType",  columnDefinition = "VARCHAR(255)   COMMENT '建筑类型'")
    private String buildType;

    /**
     * 空间大小
     */
    @Column(name = "size",  columnDefinition = "VARCHAR(255)   COMMENT '空间大小'")
    private String size;

    /**
     * 地区供电情况
     */
    @Column(name = "areaPowerSupply",  columnDefinition = "VARCHAR(255)   COMMENT '地区供电情况'")
    private String areaPowerSupply;

    /**
     * 供电价格
     */
    @Column(name = "powerSupplyPrice",  columnDefinition = "DECIMAL(10,2)   COMMENT '供电价格'")
    private Double powerSupplyPrice;

    /**
     * 空调排水
     */
    @Column(name = "airConditionDrainage",  columnDefinition = "VARCHAR(255)   COMMENT '空调排水'")
    private String airConditionDrainage;

    /**
     * 接地
     */
    @Column(name = "grounding",  columnDefinition = "VARCHAR(255)   COMMENT '接地'")
    private String grounding;

    /**
     * 传输路由
     */
    @Column(name = "transportRoutes",  columnDefinition = "VARCHAR(255)   COMMENT '传输路由'")
    private String transportRoutes;

    /**
     * 承重
     */
    @Column(name = "bearing",  columnDefinition = "VARCHAR(255)   COMMENT '承重'")
    private String bearing;

    /**
     * 手机网络信号情况
     */
    @Column(name = "aobilePhoneSignal",  columnDefinition = "VARCHAR(255)   COMMENT '手机网络信号情况'")
    private String aobilePhoneSignal;

    /**
     * 周围辐射情况
     */
    @Column(name = "ambientRadiationConditions",  columnDefinition = "VARCHAR(255)   COMMENT '周围辐射情况'")
    private String ambientRadiationConditions;
    /**
     * 客户基本信息
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customerBaseInfo_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '客户级别'")
    private CustomerBaseInfo customerBaseInfo;

    public CustomerBaseInfo getCustomerBaseInfo() {
        return customerBaseInfo;
    }

    public void setCustomerBaseInfo(CustomerBaseInfo customerBaseInfo) {
        this.customerBaseInfo = customerBaseInfo;
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