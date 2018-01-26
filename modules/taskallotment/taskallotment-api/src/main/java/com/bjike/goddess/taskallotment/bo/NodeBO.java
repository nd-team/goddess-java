package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.TaskStatus;
import com.bjike.goddess.taskallotment.enums.TaskType;
import com.bjike.goddess.taskallotment.enums.TimeType;
import com.bjike.goddess.taskallotment.enums.TimesType;

/**
 * 任务节点名称
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-29 15:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NodeBO extends BaseBO {
    /**
     * 节点
     */
    private String taskName;
    /**
     * 执行人
     */
    private String execute;
    /**
     * 计划执行时间
     */
    private String planTime;
    /**
     * 任务类型
     */
    private TaskType taskType;
    /**
     * 时长类型
     */
    private TimesType timesType;
    /**
     * 计划任务量
     */
    private Double planNum;
    /**
     * 所需时长
     */
    private Double needTime;

    /**
     * 所需时长时间类型
     */
    private TimeType needType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 任务状态
     */
    private TaskStatus taskStatus;

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

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

    public Double getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Double needTime) {
        this.needTime = needTime;
    }

    public TimeType getNeedType() {
        return needType;
    }

    public void setNeedType(TimeType needType) {
        this.needType = needType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TimesType getTimesType() {
        return timesType;
    }

    public void setTimesType(TimesType timesType) {
        this.timesType = timesType;
    }
}
