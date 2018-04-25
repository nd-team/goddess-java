package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 年月存本月和上个月业务传输对象
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-09 11:23 ]
 * @Description: [ 年月存本月和上个月业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearsBO extends BaseBO {

    /**
     * years
     */
    private int years;

    /**
     * month
     */
    private int month;


    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}