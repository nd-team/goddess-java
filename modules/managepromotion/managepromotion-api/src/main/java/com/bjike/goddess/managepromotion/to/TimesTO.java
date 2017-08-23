package com.bjike.goddess.managepromotion.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 员工已晋升情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimesTO extends BaseTO {
    public interface collect{}

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