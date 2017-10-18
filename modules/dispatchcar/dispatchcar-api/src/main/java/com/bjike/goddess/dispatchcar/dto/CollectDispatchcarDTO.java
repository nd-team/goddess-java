package com.bjike.goddess.dispatchcar.dto;

import com.bjike.goddess.dispatchcar.enums.CollectDateType;
import com.bjike.goddess.dispatchcar.enums.CollectDispatchcarType;

import java.io.Serializable;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-07 16:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDispatchcarDTO implements Serializable{
    /**
     * 汇总类型
     */
    private CollectDispatchcarType collectDispatchcarType;

    /**
     * 汇总时间类型
     */
    private CollectDateType collectDateType;

    /**
     * 开始日期
     */
    private String startTime;

    /**
     * 结束日期
     */
    private String endTime;

    public CollectDispatchcarType getCollectDispatchcarType() {
        return collectDispatchcarType;
    }

    public void setCollectDispatchcarType(CollectDispatchcarType collectDispatchcarType) {
        this.collectDispatchcarType = collectDispatchcarType;
    }

    public CollectDateType getCollectDateType() {
        return collectDateType;
    }

    public void setCollectDateType(CollectDateType collectDateType) {
        this.collectDateType = collectDateType;
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
