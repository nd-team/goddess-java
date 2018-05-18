package com.bjike.goddess.materialbuy.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.materialbuy.enums.DateType;

/**
 * 物资购买数据传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialBuyDTO extends BaseDTO {

    /**
     * 年份（yyyy）
     */
    private Integer year;

    /**
     * 月份(MM)
     */
    private Integer month;

    /**
     * 周数(1-4)
     */
    private Integer week;

    /**
     * 天数  01|10|27
     */
    private Integer day;

    /**
     * 季度(1-4)
     */
    private Integer quarter;

    /**
     * 日期类型
     */
    private DateType dateType;

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}