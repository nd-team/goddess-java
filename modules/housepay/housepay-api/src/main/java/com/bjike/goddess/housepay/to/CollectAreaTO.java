package com.bjike.goddess.housepay.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-8-22.
 */
public class CollectAreaTO extends BaseTO{
    /**
     * 地区数组
     */
    private String[] areas;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
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
