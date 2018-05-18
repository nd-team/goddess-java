package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-6.
 */
public class DateTO {
    public interface Collect {
    }
    /**
     * 开始资金进入时间
     */
    private String startTime;

    /**
     * 结束资金进入时间
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
