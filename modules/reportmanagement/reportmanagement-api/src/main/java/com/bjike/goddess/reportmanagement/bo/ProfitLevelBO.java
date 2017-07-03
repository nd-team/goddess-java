package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 利润水平分析
 * @Author: [chenjunhao]
 * @Date: [2017-06-26 17:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProfitLevelBO extends BaseBO{
    /**
     * 项目
     */
    private String project;
    /**
     * 起始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 增减额
     */
    private Double change;
    /**
     * 增减率
     */
    private String changeScale;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public String getChangeScale() {
        return changeScale;
    }

    public void setChangeScale(String changeScale) {
        this.changeScale = changeScale;
    }
}
