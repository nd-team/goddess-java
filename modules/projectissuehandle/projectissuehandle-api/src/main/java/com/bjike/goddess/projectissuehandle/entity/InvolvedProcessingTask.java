package com.bjike.goddess.projectissuehandle.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 参与处理人员的任务分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectissuehandle_involvedprocessingtask")
public class InvolvedProcessingTask extends BaseEntity {
    /**
     * 项目问题编号
     */
    @Column(name = "projectNum", columnDefinition = "VARCHAR(255)   COMMENT '项目问题编号'")
    private String projectNum;
    /**
     * 内部项目名称
     */
    @Column(name = "internalProjectName", columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String internalProjectName;

    /**
     * 处理人员
     */
    @Column(name = "handler", columnDefinition = "VARCHAR(255)   COMMENT '处理人员'")
    private String handler;

    /**
     * 每日计划
     */
    @Column(name = "dailyPlan",  columnDefinition = "VARCHAR(255)   COMMENT '每日计划'")
    private String dailyPlan;

    /**
     * 临时任务
     */
    @Column(name = "temporaryTask",  columnDefinition = "VARCHAR(255)   COMMENT '临时任务'")
    private String temporaryTask;

    /**
     * 实际完成情况
     */
    @Column(name = "actualCompletion",  columnDefinition = "VARCHAR(255)   COMMENT '实际完成情况'")
    private String actualCompletion;

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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