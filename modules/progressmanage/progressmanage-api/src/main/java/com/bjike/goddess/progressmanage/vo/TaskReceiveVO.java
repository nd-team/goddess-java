package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.progressmanage.enums.TaskStatus;

/**
 * 任务接收表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskReceiveVO {

    /**
     * id
     */
    private String id;

    /**
     * 任务状态
     */
    private TaskStatus taskStatus;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 地区
     */
    private String area;

    /**
     * 所属项目组或部门
     */
    private String projectId;

    /**
     * 所属模块
     */
    private String moduleId;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务详情
     */
    private String taskDetail;

    /**
     * 注意事项
     */
    private String announcements;

    /**
     * 任务负责人
     */
    private String taskPrincipal;

    /**
     * 任务人
     */
    private String taskUser;

    /**
     * 所需工时
     */
    private Double needManHour;

    /**
     * 实际工时
     */
    private Double actualManHour;

    /**
     * 任务开始时间
     */
    private String startTime;

    /**
     * 任务结束时间
     */
    private String endTime;

    /**
     * 执行备注
     */
    private String remark;

    /**
     * 问题量
     */
    private Integer problems;

    /**
     * 是否发生费用报销
     */
    private Boolean reimburse;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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