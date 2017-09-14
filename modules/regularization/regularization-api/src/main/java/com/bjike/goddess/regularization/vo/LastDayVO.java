package com.bjike.goddess.regularization.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 昨天任务情况
 * @Author: [chenjunhao]
 * @Date: [2017-09-09 15:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LastDayVO{
    /**
     * 昨天时间
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
     * 实际完成任务数量
     */
    private Double actualTask;
    /**
     * 差异任务量
     */
    private Double taskDiffer;
    /**
     * 计划任务时长
     */
    private Double planTime;
    /**
     * 实际任务时长
     */
    private Double actualTime;
    /**
     * 差异任务时长
     */
    private Double timeDiffer;
    /**
     * 计划工作量
     */
    private Double planWork;
    /**
     * 实际工作量
     */
    private Double actualWork;
    /**
     * 差异工作量
     */
    private Double workDiffer;
    /**
     * 备注
     */
    private String remark;
    /**
     * 人工
     */
    private Double artificial;

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

    public Double getActualTask() {
        return actualTask;
    }

    public void setActualTask(Double actualTask) {
        this.actualTask = actualTask;
    }

    public Double getTaskDiffer() {
        return taskDiffer;
    }

    public void setTaskDiffer(Double taskDiffer) {
        this.taskDiffer = taskDiffer;
    }

    public Double getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Double planTime) {
        this.planTime = planTime;
    }

    public Double getActualTime() {
        return actualTime;
    }

    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    public Double getTimeDiffer() {
        return timeDiffer;
    }

    public void setTimeDiffer(Double timeDiffer) {
        this.timeDiffer = timeDiffer;
    }

    public Double getPlanWork() {
        return planWork;
    }

    public void setPlanWork(Double planWork) {
        this.planWork = planWork;
    }

    public Double getActualWork() {
        return actualWork;
    }

    public void setActualWork(Double actualWork) {
        this.actualWork = actualWork;
    }

    public Double getWorkDiffer() {
        return workDiffer;
    }

    public void setWorkDiffer(Double workDiffer) {
        this.workDiffer = workDiffer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getArtificial() {
        return artificial;
    }

    public void setArtificial(Double artificial) {
        this.artificial = artificial;
    }
}
