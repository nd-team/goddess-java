package com.bjike.goddess.customer.vo;

/**
 * 亲密度因素层权重表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ClosenessFoactorWeightVO {

    /**
     * id
     */
    private String id;
    /**
     * 亲密度类型名称
     */
    private String closenessName;

    /**
     * 亲密度类型权重
     */
    private Double closenessWeight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClosenessName() {
        return closenessName;
    }

    public void setClosenessName(String closenessName) {
        this.closenessName = closenessName;
    }

    public Double getClosenessWeight() {
        return closenessWeight;
    }

    public void setClosenessWeight(Double closenessWeight) {
        this.closenessWeight = closenessWeight;
    }
}