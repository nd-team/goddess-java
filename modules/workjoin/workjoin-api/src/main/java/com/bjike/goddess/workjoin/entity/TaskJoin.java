package com.bjike.goddess.workjoin.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 任务交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "workjoin_taskjoin")
public class TaskJoin extends BaseEntity {

    /**
     * 任务名称
     */
    @Column(name = "taskName", columnDefinition = "VARCHAR(255)   COMMENT '任务名称'")
    private String taskName;

    /**
     * 任务要求
     */
    @Column(name = "taskRequire", columnDefinition = "VARCHAR(255)   COMMENT '任务要求'")
    private String taskRequire;

    /**
     * 任务节点
     */
    @Column(name = "taskNode", columnDefinition = "VARCHAR(255)   COMMENT '任务节点'")
    private String taskNode;

    /**
     * 完成情况
     */
    @Column(name = "completion", columnDefinition = "VARCHAR(255)   COMMENT '完成情况'")
    private String completion;

    /**
     * 任务时间
     */
    @Column(name = "taskTime", columnDefinition = "DATETIME   COMMENT '任务时间'")
    private LocalDateTime taskTime;

    /**
     * 文件名称
     */
    @Column(name = "fileName", columnDefinition = "VARCHAR(255)   COMMENT '文件名称'")
    private String fileName;

    /**
     * 文件存储路径
     */
    @Column(name = "filePath", columnDefinition = "VARCHAR(255)   COMMENT '文件存储路径'")
    private String filePath;

    /**
     * 汇报对象
     */
    @Column(name = "reportObject", columnDefinition = "VARCHAR(255)   COMMENT '汇报对象'")
    private String reportObject;

    /**
     * 任务来源路径
     */
    @Column(name = "taskPath", columnDefinition = "VARCHAR(255)   COMMENT '任务来源路径'")
    private String taskPath;


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskRequire() {
        return taskRequire;
    }

    public void setTaskRequire(String taskRequire) {
        this.taskRequire = taskRequire;
    }

    public String getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(String taskNode) {
        this.taskNode = taskNode;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getReportObject() {
        return reportObject;
    }

    public void setReportObject(String reportObject) {
        this.reportObject = reportObject;
    }

    public String getTaskPath() {
        return taskPath;
    }

    public void setTaskPath(String taskPath) {
        this.taskPath = taskPath;
    }
}