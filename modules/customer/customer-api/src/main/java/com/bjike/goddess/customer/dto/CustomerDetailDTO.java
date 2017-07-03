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
     * 地区数组
     */
    private String[] areas;
    /**
     * 客户名数组
     */
    private String[] customerNames;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(String[] customerNames) {
        this.customerNames = customerNames;
    }
}