package com.bjike.goddess.materialinstock.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 库存预警业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StockWarningBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 下限值
     */
    private Double lowerValue;

    /**
     * 上限值
     */
    private Double upperValue;

    /**
     * 物资类型
     */
    private String materialType;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Double getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(Double lowerValue) {
        this.lowerValue = lowerValue;
    }

    public Double getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(Double upperValue) {
        this.upperValue = upperValue;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}