package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 账上余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountBalanceCollectTO extends BaseTO {

    /*
     * 开始时间
     */
    private String startTime;
    /*
     * 结束时间
     */
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}