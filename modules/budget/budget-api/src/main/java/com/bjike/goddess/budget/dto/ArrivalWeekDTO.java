package com.bjike.goddess.budget.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 地区收入周数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrivalWeekDTO extends BaseDTO {
    /**
     * 地区数组
     */
    private String[] arrivals;

    /**
     * 时间(如2017-05)
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    public String[] getArrivals() {
        return arrivals;
    }

    public void setArrivals(String[] arrivals) {
        this.arrivals = arrivals;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}