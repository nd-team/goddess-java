package com.bjike.goddess.attendance.vo.overtime;

import java.io.Serializable;

/**
 * 补班设置表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExtralOverWorkDayVO implements Serializable {

    /**
     * 补班天数
     */
    private Double overDay;

    public Double getOverDay() {
        return overDay;
    }

    public void setOverDay(Double overDay) {
        this.overDay = overDay;
    }
}