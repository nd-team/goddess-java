package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-10-18 11:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ObjectBO extends BaseBO {
    /**
     * 未完成时长
     */
    private Double undoneTime;
    /**
     * 未完成时长时间类型
     */
    private Integer undoneType;
    /**
     * 实际时长
     */
    private Double actualTime;
    /**
     * 实际时长时间类型
     */
    private Integer actualType;
    /**
     * 任务状态
     */
    private Integer taskStatus;
    /**
     * 加班时长
     */
    private Double time;

    public Double getUndoneTime() {
        return undoneTime;
    }

    public void setUndoneTime(Double undoneTime) {
        this.undoneTime = undoneTime;
    }

    public Integer getUndoneType() {
        return undoneType;
    }

    public void setUndoneType(Integer undoneType) {
        this.undoneType = undoneType;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Double getActualTime() {
        return actualTime;
    }

    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    public Integer getActualType() {
        return actualType;
    }

    public void setActualType(Integer actualType) {
        this.actualType = actualType;
    }
}
