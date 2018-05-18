package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各渠道汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 各渠道汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ChannelTO extends BaseBO {
    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}