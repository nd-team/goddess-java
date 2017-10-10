package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.taskallotment.enums.TaskType;

/**
 * 日报表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayReportVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 项目组
     */
    private String depart;

    /**
     * 岗位
     */
    private String position;

    /**
     * 任务类型
     */
    private TaskType taskType;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 任务时长(小时)
     */
    private Double taskTime;

    /**
     * 时间
     */
    private String time;

    /**
     * 明日工作计划
     */
    private String plan;

    /**
     * 计划时长(小时)
     */
    private Double planTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Double taskTime) {
        this.taskTime = taskTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Double planTime) {
        this.planTime = planTime;
    }
}