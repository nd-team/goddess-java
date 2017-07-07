package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 期初余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BeginBalanceTO extends BaseTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 期初余额
     */
    private Double beginBalance;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
}