package com.bjike.goddess.customer.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 客户详细信息数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:41:13.468 ]
 * @Description: [ 客户详细信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerDetailDTO extends BaseDTO {


    /**
     * 地区
     */
    private String area;
    /**
     * 客户名
     */
    private String customerName;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}