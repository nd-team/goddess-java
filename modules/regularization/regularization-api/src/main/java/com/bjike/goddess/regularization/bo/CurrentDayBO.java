package com.bjike.goddess.regularization.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 当天任务情况
 * @Author: [chenjunhao]
 * @Date: [2017-09-09 15:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CurrentDayBO extends BaseBO{
    /**
     * 当天时间
     */
    private String time;
    /**
     * 工作内容
     */
    private String content;
    /**
     * 计划任务数量
     */
    private Double planTask;
    /**
     * 计划任务时长
     */
    private Double taskTime;
    /**
     * 计划工作量
     */
    private Double planWork;
    /**
     * 备注
     */
    private String remark;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPlanTask() {
        return planTask;
    }

    public void setPlanTask(Double planTask) {
        this.planTask = planTask;
    }

    public Double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Double taskTime) {
        this.taskTime = taskTime;
    }

    public Double getPlanWork() {
        return planWork;
    }

    public void setPlanWork(Double planWork) {
        this.planWork = planWork;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
