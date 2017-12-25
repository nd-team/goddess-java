package com.bjike.goddess.progressmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.progressmanage.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


/**
 * 任务接收
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_taskreceive")
public class TaskReceive extends BaseEntity {

    /**
     * 进度节点
     */
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "progressmanage_taskreceive_progressnode",
            joinColumns = {@JoinColumn(name = "taskreceive_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '任务接收'")},
            inverseJoinColumns = {@JoinColumn(name = "progressnode_id", referencedColumnName = "id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '进度节点'")})
    private Set<ProgressNode> progressNodeSet = new HashSet<ProgressNode>();

    /**
     * 任务状态
     */
    @Column(name = "taskStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '任务状态'")
    private TaskStatus taskStatus;

    /**
     * 优先级
     */
    @Column(name = "priority", nullable = false, columnDefinition = "VARCHAR(11)   COMMENT '优先级'")
    private Integer priority;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 所属项目组或部门
     */
    @Column(name = "projectId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属项目组或部门'")
    private String projectId;

    /**
     * 所属模块
     */
    @Column(name = "moduleId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属模块'")
    private String moduleId;

    /**
     * 任务类型
     */
    @Column(name = "taskType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务类型'")
    private String taskType;

    /**
     * 任务详情
     */
    @Column(name = "taskDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务详情'")
    private String taskDetail;

    /**
     * 注意事项
     */
    @Column(name = "announcements", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注意事项'")
    private String announcements;

    /**
     * 任务负责人
     */
    @Column(name = "taskPrincipal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务负责人'")
    private String taskPrincipal;

    /**
     * 任务人
     */
    @Column(name = "taskUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务人'")
    private String taskUser;

    /**
     * 所需工时
     */
    @Column(name = "needManHour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '所需工时'")
    private Double needManHour;

    /**
     * 实际工时
     */
    @Column(name = "actualManHour", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际工时'")
    private Double actualManHour;

    /**
     * 任务开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '任务开始时间'")
    private LocalDateTime startTime;

    /**
     * 任务结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '任务结束时间'")
    private LocalDateTime endTime;

    /**
     * 执行备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '执行备注'")
    private String remark;

    /**
     * 问题量
     */
    @Column(name = "problems", nullable = false, columnDefinition = "INT(11)   COMMENT '问题量'")
    private Integer problems;

    /**
     * 是否发生费用报销
     */
    @Column(name = "is_reimburse", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否发生费用报销'", insertable = false)
    private Boolean reimburse;


    public Set<ProgressNode> getProgressNodeSet() {
        return progressNodeSet;
    }

    public void setProgressNodeSet(Set<ProgressNode> progressNodeSet) {
        this.progressNodeSet = progressNodeSet;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public String getTaskPrincipal() {
        return taskPrincipal;
    }

    public void setTaskPrincipal(String taskPrincipal) {
        this.taskPrincipal = taskPrincipal;
    }

    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }

    public Double getNeedManHour() {
        return needManHour;
    }

    public void setNeedManHour(Double needManHour) {
        this.needManHour = needManHour;
    }

    public Double getActualManHour() {
        return actualManHour;
    }

    public void setActualManHour(Double actualManHour) {
        this.actualManHour = actualManHour;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getProblems() {
        return problems;
    }

    public void setProblems(Integer problems) {
        this.problems = problems;
    }

    public Boolean getReimburse() {
        return reimburse;
    }

    public void setReimburse(Boolean reimburse) {
        this.reimburse = reimburse;
    }
}