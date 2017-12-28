package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-7-3.
 */
public class PayStockCollectTO extends BaseTO{
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
