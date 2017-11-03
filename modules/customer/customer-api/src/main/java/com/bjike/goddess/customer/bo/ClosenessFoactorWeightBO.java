package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 亲密度因素层权重业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:09 ]
 * @Description: [ 亲密度因素层权重业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ClosenessFoactorWeightBO extends BaseBO {

    /**
     * 亲密度类型名称
     */
    private String closenessName;

    /**
     * 亲密度类型权重
     */
    private Double closenessWeight;


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