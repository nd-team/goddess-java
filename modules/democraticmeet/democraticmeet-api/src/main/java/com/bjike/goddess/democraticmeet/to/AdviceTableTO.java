package com.bjike.goddess.democraticmeet.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 建议表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:26 ]
 * @Description: [ 建议表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AdviceTableTO extends BaseTO {

    /**
     * 他人给予的建议
     */
    private String advice;

    /**
     * 建议人
     */
    private String advicer;




    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAdvicer() {
        return advicer;
    }

    public void setAdvicer(String advicer) {
        this.advicer = advicer;
    }


}