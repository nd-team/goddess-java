package com.bjike.goddess.taskallotment.vo;

/**
 * 任务提醒表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskRemindVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String project;

    /**
     * 项目表
     */
    private String projectTable;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 第一次提醒时间
     */
    private String firstTime;

    /**
     * 第二次提醒时间
     */
    private String secondTime;

    /**
     * 第三次提醒时间
     */
    private String thridTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectTable() {
        return projectTable;
    }

    public void setProjectTable(String projectTable) {
        this.projectTable = projectTable;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }

    public String getThridTime() {
        return thridTime;
    }

    public void setThridTime(String thridTime) {
        this.thridTime = thridTime;
    }
}