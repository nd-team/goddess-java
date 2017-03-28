package com.bjike.goddess.projectissuehandle.vo;

/**
 * 参与处理人员的任务分配表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvolvedProcessingTaskVO {

    /**
     * id
     */
    private String id;
    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 处理人员
     */
    private String handler;

    /**
     * 每日计划
     */
    private String dailyPlan;

    /**
     * 临时任务
     */
    private String temporaryTask;

    /**
     * 实际完成情况
     */
    private String actualCompletion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(String dailyPlan) {
        this.dailyPlan = dailyPlan;
    }

    public String getTemporaryTask() {
        return temporaryTask;
    }

    public void setTemporaryTask(String temporaryTask) {
        this.temporaryTask = temporaryTask;
    }

    public String getActualCompletion() {
        return actualCompletion;
    }

    public void setActualCompletion(String actualCompletion) {
        this.actualCompletion = actualCompletion;
    }
}