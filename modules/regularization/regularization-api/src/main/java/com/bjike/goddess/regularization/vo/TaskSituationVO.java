package com.bjike.goddess.regularization.vo;

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
public class TaskSituationVO {
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
    private List<CurrentDayVO> currentDay;
    /**
     * 昨天情况
     */
    private List<LastDayVO> lastDay;

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

    public List<CurrentDayVO> getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(List<CurrentDayVO> currentDay) {
        this.currentDay = currentDay;
    }

    public List<LastDayVO> getLastDay() {
        return lastDay;
    }

    public void setLastDay(List<LastDayVO> lastDay) {
        this.lastDay = lastDay;
    }
}
