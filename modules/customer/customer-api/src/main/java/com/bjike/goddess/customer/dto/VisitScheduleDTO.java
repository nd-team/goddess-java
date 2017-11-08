package com.bjike.goddess.customer.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 拜访日程表数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VisitScheduleDTO extends BaseDTO {
    /**
     * 年度
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 周期
     */
    private Integer week;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}