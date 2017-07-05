package com.bjike.goddess.budget.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 地区收入月数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrivalMonthDTO extends BaseDTO {
    /**
     * 地区数组
     */
    private String[] arrivals;

    public String[] getArrivals() {
        return arrivals;
    }

    public void setArrivals(String[] arrivals) {
        this.arrivals = arrivals;
    }
}