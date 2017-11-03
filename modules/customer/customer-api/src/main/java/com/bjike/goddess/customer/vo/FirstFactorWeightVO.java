package com.bjike.goddess.customer.vo;

/**
 * 一层因素层权重表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:28 ]
 * @Description: [ 一层因素层权重表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirstFactorWeightVO {

    /**
     * id
     */
    private String id;
    /**
     * 一级因素层因素名
     */
    private String firstFactorName;

    /**
     * 一级因素层因素权重
     */
    private Double firstFactorWeight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstFactorName() {
        return firstFactorName;
    }

    public void setFirstFactorName(String firstFactorName) {
        this.firstFactorName = firstFactorName;
    }

    public Double getFirstFactorWeight() {
        return firstFactorWeight;
    }

    public void setFirstFactorWeight(Double firstFactorWeight) {
        this.firstFactorWeight = firstFactorWeight;
    }
}