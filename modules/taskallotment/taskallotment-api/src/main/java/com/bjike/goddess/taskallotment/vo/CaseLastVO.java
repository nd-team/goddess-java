package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.TaskType;

/**
 * 完成情况汇总最底层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseLastVO{
    /**
     * 任务类型
     */
    private TaskType taskType;
    /**
     * 计划任务量
     */
    private Double planNum;
    /**
     * 完成任务量
     */
    private Double actualNum;
    /**
     * 未完成量
     */
    private Double unFinishNum;
    /**
     * 计划总工时（小时）
     */
    private Double planTime;
    /**
     * 已完成工时(小时)
     */
    private Double actualTime;
    /**
     * 未完成工时(小时)
     */
    private Double unFinishTime;
    /**
     * 上报未完成工时(小时)
     */
    private Double reportUnFinish;
    /**
     * 未上报未完成工时(小时)
     */
    private Double unReportUnFinish;

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
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

    public Double getUnFinishNum() {
        return unFinishNum;
    }

    public void setUnFinishNum(Double unFinishNum) {
        this.unFinishNum = unFinishNum;
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

    public Double getUnFinishTime() {
        return unFinishTime;
    }

    public void setUnFinishTime(Double unFinishTime) {
        this.unFinishTime = unFinishTime;
    }

    public Double getReportUnFinish() {
        return reportUnFinish;
    }

    public void setReportUnFinish(Double reportUnFinish) {
        this.reportUnFinish = reportUnFinish;
    }

    public Double getUnReportUnFinish() {
        return unReportUnFinish;
    }

    public void setUnReportUnFinish(Double unReportUnFinish) {
        this.unReportUnFinish = unReportUnFinish;
    }
}
