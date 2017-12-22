package com.bjike.goddess.supplier.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 供应商信息管理数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierInfoDTO extends BaseDTO {
    /**
     * 供应商名称
     */
    private String supplierName;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}