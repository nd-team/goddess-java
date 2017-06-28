package com.bjike.goddess.receivable.to;

import com.bjike.goddess.receivable.enums.CompareStatus;
import com.bjike.goddess.receivable.enums.TimeStatus;

import java.io.Serializable;

/**
 * Created by ike on 17-6-20.
 */
public class CollectCompareTO implements Serializable{
    /**
     * 对比条件
     */
    private CompareStatus compareStatus;
    /*
     * 对比时间
     */
    private TimeStatus timeStatus;

    /**
     * 到账开始时间
     */
    private String startTime;
    /**
     * 到账结束时间
     */
    private String endTime;

    public CompareStatus getCompareStatus() {
        return compareStatus;
    }

    public void setCompareStatus(CompareStatus compareStatus) {
        this.compareStatus = compareStatus;
    }

    public TimeStatus getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(TimeStatus timeStatus) {
        this.timeStatus = timeStatus;
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
