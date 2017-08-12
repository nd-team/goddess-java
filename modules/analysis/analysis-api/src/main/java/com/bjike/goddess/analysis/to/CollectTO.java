package com.bjike.goddess.analysis.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-7-6.
 */
public class CollectTO extends BaseTO{
    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 地区
     */
    private String area;
    /**
     * 项目组
     */
    private String department;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
