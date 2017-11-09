package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 日报汇总最底层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayDBO extends BaseBO {
    /**
     * 姓名
     */
    private String name;
    /**
     * 计划任务量
     */
    private Double planNum;
    /**
     * 完成任务量
     */
    private Double actualNum;
    /**
     * 任务时长(小时)
     */
    private Double taskTime;
    /**
     * 实际时长(小时)
     */
    private Double actualTime;
    /**
     * 加班时长(小时)
     */
    private Double outTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Double planNum) {
        this.planNum = planNum;
    }

    public Double getActualNum() {
        return actualNum;
    }

    public void setActualNum(Double actualNum) {
        this.actualNum = actualNum;
    }

    public Double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Double taskTime) {
        this.taskTime = taskTime;
    }

    public Double getActualTime() {
        return actualTime;
    }

    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    public Double getOutTime() {
        return outTime;
    }

    public void setOutTime(Double outTime) {
        this.outTime = outTime;
    }
}
