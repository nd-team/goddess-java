package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.TaskType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 日报
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_dayreport")
public class DayReport extends BaseEntity{

    /**
     * 姓名
     */
    @Transient
    private String name;

    /**
     * 项目组
     */
    @Transient
    private String depart;

    /**
     * 岗位
     */
    @Transient
    private String position;

    /**
     * 任务类型
     */
    @Transient
    private TaskType taskType;

    /**
     * 任务内容
     */
    @Transient
    private String content;

    /**
     * 任务时长(小时)
     */
    @Transient
    private Double taskTime;

    /**
     * 时间
     */
    @Transient
    private String time;

    /**
     * 明日工作计划
     */
    @Transient
    private String plan;

    /**
     * 计划时长(小时)
     */
    @Transient
    private Double planTime;


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