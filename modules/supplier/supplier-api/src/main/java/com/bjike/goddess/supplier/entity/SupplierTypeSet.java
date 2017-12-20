package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 供应商类型设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_suppliertypeset")
public class SupplierTypeSet extends BaseEntity {

    /**
     * 供应商类型
     */
    @Column(name = "supplierType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商类型'")
    private String supplierType;


    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
}