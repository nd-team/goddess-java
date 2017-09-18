package com.bjike.goddess.regularization.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 任务情况
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-09 15:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaskSituationBO extends BaseBO {
    /**
     * 人员安排
     */
    private String user;
    /**
     * 类型
     */
    private String type;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 当天情况
     */
    private List<CurrentDayBO> currentDay;
    /**
     * 昨天情况
     */
    private List<LastDayBO> lastDay;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public List<CurrentDayBO> getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(List<CurrentDayBO> currentDay) {
        this.currentDay = currentDay;
    }

    public List<LastDayBO> getLastDay() {
        return lastDay;
    }

    public void setLastDay(List<LastDayBO> lastDay) {
        this.lastDay = lastDay;
    }
}
