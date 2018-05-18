package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 地区权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AreaWeightSetTO extends BaseTO {

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空",groups = {ADD.class, EDIT.class})
    private String provinces;

    /**
     * 省份权重
     */
    @NotNull(message = "省份权重不能为空",groups = {ADD.class, EDIT.class})
    private Double provincesWeight;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 地区权重
     */
    @NotNull(message = "地区权重不能为空",groups = {ADD.class, EDIT.class})
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