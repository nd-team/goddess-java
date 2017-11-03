package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 业务类型权重设置业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BussTypeWeightSetBO extends BaseBO {

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务类型权重
     */
    private Double businessTypeWeight;

    /**
     * 业务方向
     */
    private String businessWay;

    /**
     * 业务方向权重
     */
    private Double businessWayWeight;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getBusinessTypeWeight() {
        return businessTypeWeight;
    }

    public void setBusinessTypeWeight(Double businessTypeWeight) {
        this.businessTypeWeight = businessTypeWeight;
    }

    public String getBusinessWay() {
        return businessWay;
    }

    public void setBusinessWay(String businessWay) {
        this.businessWay = businessWay;
    }

    public Double getBusinessWayWeight() {
        return businessWayWeight;
    }

    public void setBusinessWayWeight(Double businessWayWeight) {
        this.businessWayWeight = businessWayWeight;
    }
}