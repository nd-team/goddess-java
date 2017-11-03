package com.bjike.goddess.customer.vo;

/**
 * 地区权重设置表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AreaWeightSetVO {

    /**
     * id
     */
    private String id;
    /**
     * 省份
     */
    private String provinces;

    /**
     * 省份权重
     */
    private Double provincesWeight;

    /**
     * 地区
     */
    private String area;

    /**
     * 地区权重
     */
    private Double areaWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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