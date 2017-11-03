package com.bjike.goddess.customer.vo;

/**
 * 客户接触阶段权重设置表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerContactWeightSetVO {

    /**
     * id
     */
    private String id;
    /**
     * 客户接触阶段类型
     */
    private String customerContactType;

    /**
     * 客户接触阶段类型权重
     */
    private String customerContactTypeWeight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerContactType() {
        return customerContactType;
    }

    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }

    public String getCustomerContactTypeWeight() {
        return customerContactTypeWeight;
    }

    public void setCustomerContactTypeWeight(String customerContactTypeWeight) {
        this.customerContactTypeWeight = customerContactTypeWeight;
    }
}