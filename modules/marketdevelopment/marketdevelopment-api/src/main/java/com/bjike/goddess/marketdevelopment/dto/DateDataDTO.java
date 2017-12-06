package com.bjike.goddess.marketdevelopment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 日期数据数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DateDataDTO extends BaseDTO {

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}