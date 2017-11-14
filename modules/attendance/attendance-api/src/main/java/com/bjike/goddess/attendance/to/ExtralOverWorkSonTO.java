package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 补班子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-21 10:32 ]
 * @Description: [ 补班子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExtralOverWorkSonTO extends BaseTO {

    /**
     * 补班id
     */
    private String fatherId;

    /**
     * 补班开始时间
     */
    private String startTime;

    /**
     * 补班结束时间
     */
    private String endTime;


    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

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