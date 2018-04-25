package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 年月存本月和上个月
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-09 11:23 ]
 * @Description: [ 年月存本月和上个月 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearsTO extends BaseTO {
    /**
     * 年
     */

    private int years;

    /**
     * 月
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