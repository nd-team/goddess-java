package com.bjike.goddess.system.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 问题
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionTO extends BaseTO {

    /**
     * 问题描述
     */
    private String rate;



    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}