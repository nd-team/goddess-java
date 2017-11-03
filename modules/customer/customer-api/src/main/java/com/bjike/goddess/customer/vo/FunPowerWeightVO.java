package com.bjike.goddess.customer.vo;

/**
 * 职权因素层权重表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FunPowerWeightVO {

    /**
     * id
     */
    private String id;
    /**
     * 职权类型名称
     */
    private String funPowerTypeName;

    /**
     * 职权类型权重
     */
    private Double funPowerTypeWeight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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