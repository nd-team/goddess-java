package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 利润垂直分析
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-29 13:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProfitVerticalBO extends BaseBO {
    /**
     * 项目
     */
    private String project;
    /**
     * 起始时间
     */
    private Double startTime;
    /**
     * 结束时间
     */
    private Double endTime;
    /**
     * 变动情况
     */
    private Double change;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }
}
