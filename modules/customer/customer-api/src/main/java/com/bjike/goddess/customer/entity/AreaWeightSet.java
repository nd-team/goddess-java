package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 地区权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_areaweightset")
public class AreaWeightSet extends BaseEntity {

    /**
     * 省份
     */
    @Column(name = "provinces", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '省份'")
    private String provinces;

    /**
     * 省份权重
     */
    @Column(name = "provincesWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '省份权重'")
    private Double provincesWeight;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 地区权重
     */
    @Column(name = "areaWeight", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '地区权重'")
    private Double areaWeight;

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public Double getProvincesWeight() {
        return provincesWeight;
    }

    public void setProvincesWeight(Double provincesWeight) {
        this.provincesWeight = provincesWeight;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getAreaWeight() {
        return areaWeight;
    }

    public void setAreaWeight(Double areaWeight) {
        this.areaWeight = areaWeight;
    }
}