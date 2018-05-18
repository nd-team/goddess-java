package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 客户接触阶段权重设置业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerContactWeightSetBO extends BaseBO {

    /**
     * 客户接触阶段类型
     */
    private String customerContactType;

    /**
     * 客户接触阶段类型权重
     */
    private Double customerContactTypeWeight;


    public String getCustomerContactType() {
        return customerContactType;
    }

    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }

    public Double getCustomerContactTypeWeight() {
        return customerContactTypeWeight;
    }

    public void setCustomerContactTypeWeight(Double customerContactTypeWeight) {
        this.customerContactTypeWeight = customerContactTypeWeight;
    }
}