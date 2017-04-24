package com.bjike.goddess.materialinstock.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 库存预警
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialinstock_stockwarning")
public class StockWarning extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 下限值
     */
    @Column(name = "lowerValue", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '下限值'")
    private Double lowerValue;

    /**
     * 上限值
     */
    @Column(name = "upperValue", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '上限值'")
    private Double upperValue;

    /**
     * 物资类型
     */
    @Column(name = "materialType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资类型'")
    private String materialType;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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