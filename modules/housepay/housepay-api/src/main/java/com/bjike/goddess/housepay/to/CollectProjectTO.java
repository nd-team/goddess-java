package com.bjike.goddess.housepay.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-8-22.
 */
public class CollectProjectTO extends BaseTO{
    /**
     * 项目数组
     */
    private String[] projects;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
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
