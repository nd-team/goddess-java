package com.bjike.goddess.materialinstock.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 库存预警
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StockWarningTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组/部门不能为空")
    private String projectGroup;

    /**
     * 物资类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物资类型不能为空")
    private String materialType;

    /**
     * 设备名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "设备名称不能为空")
    private String deviceName;

    /**
     * 下限值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "下限值不能为空")
    private Double lowerValue;

    /**
     * 上限值
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "上限值不能为空")
    private Double upperValue;

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