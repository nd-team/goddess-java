package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 职权因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FunPowerWeightTO extends BaseTO {

    /**
     * 职权类型名称
     */
    private String funPowerTypeName;

    /**
     * 职权类型权重
     */
    private Double funPowerTypeWeight;


    public String getFunPowerTypeName() {
        return funPowerTypeName;
    }

    public void setFunPowerTypeName(String funPowerTypeName) {
        this.funPowerTypeName = funPowerTypeName;
    }

    public Double getFunPowerTypeWeight() {
        return funPowerTypeWeight;
    }

    public void setFunPowerTypeWeight(Double funPowerTypeWeight) {
        this.funPowerTypeWeight = funPowerTypeWeight;
    }
}