package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-09-23 14:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SecureCaseCollectTO extends BaseTO {
    /**
     * 地区
     */
    private String[] area;
    /**
     * 项目组
     */
    private String[] projectGroup;

    /**
     * 参保单位
     */
    private String[] unit;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String[] getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String[] projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String[] getUnit() {
        return unit;
    }

    public void setUnit(String[] unit) {
        this.unit = unit;
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
