package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 任务提醒
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_taskremind")
public class TaskRemind extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 项目表
     */
    @Column(name = "projectTable", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目表'")
    private String projectTable;

    /**
     * 任务名
     */
    @Column(name = "taskName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务名'")
    private String taskName;

    /**
     * 第一次提醒时间
     */
    @Column(name = "firstTime", nullable = false, columnDefinition = "DATETIME   COMMENT '第一次提醒时间'")
    private LocalDateTime firstTime;

    /**
     * 第二次提醒时间
     */
    @Column(name = "secondTime",  columnDefinition = "DATETIME   COMMENT '第二次提醒时间'")
    private LocalDateTime secondTime;

    /**
     * 第三次提醒时间
     */
    @Column(name = "thridTime",  columnDefinition = "DATETIME   COMMENT '第三次提醒时间'")
    private LocalDateTime thridTime;


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

    public LocalDateTime getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(LocalDateTime firstTime) {
        this.firstTime = firstTime;
    }

    public LocalDateTime getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(LocalDateTime secondTime) {
        this.secondTime = secondTime;
    }

    public LocalDateTime getThridTime() {
        return thridTime;
    }

    public void setThridTime(LocalDateTime thridTime) {
        this.thridTime = thridTime;
    }
}