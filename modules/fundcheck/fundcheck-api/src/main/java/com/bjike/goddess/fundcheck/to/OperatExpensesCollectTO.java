package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 营业费用业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:54 ]
 * @Description: [ 营业费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OperatExpensesCollectTO extends BaseTO {
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