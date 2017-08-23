package com.bjike.goddess.accommodation.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [数据传输对象(条件传输等)]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RentalApplyDTO extends BaseDTO {
    /**
     * 开始时间
     */
    private String startTime;
    /**
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
