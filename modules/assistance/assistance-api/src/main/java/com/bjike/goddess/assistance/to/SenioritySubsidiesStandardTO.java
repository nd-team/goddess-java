package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工龄补助标准
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SenioritySubsidiesStandardTO extends BaseTO {

    /**
     * 年限
     */
    private String yearNum;

    /**
     * 月份
     */
    private String month;

    /**
     * 每月补助金额（元)
     */
    private Double perMonthGrant;


    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getPerMonthGrant() {
        return perMonthGrant;
    }

    public void setPerMonthGrant(Double perMonthGrant) {
        this.perMonthGrant = perMonthGrant;
    }
}