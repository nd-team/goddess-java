package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 时效性因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:52 ]
 * @Description: [ 时效性因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimelinessFactorWeightTO extends BaseTO {

    /**
     * 时效性类型名称
     */
    private String timelinessName;

    /**
     * 时效性类型权重
     */
    private Double timelinessWeight;


    public String getTimelinessName() {
        return timelinessName;
    }

    public void setTimelinessName(String timelinessName) {
        this.timelinessName = timelinessName;
    }

    public Double getTimelinessWeight() {
        return timelinessWeight;
    }

    public void setTimelinessWeight(Double timelinessWeight) {
        this.timelinessWeight = timelinessWeight;
    }
}