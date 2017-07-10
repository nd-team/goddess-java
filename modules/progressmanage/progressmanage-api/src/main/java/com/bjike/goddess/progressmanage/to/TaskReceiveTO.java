package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.progressmanage.enums.TaskStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 任务接收
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskReceiveTO extends BaseTO {

    /**
     * 进度节点
     */
    @NotNull(message = "进度节点不能为空",groups = {ADD.class, EDIT.class})
    private List<String> nodeIds;

    /**
     * 任务状态
     */
    @NotNull(message = "任务状态不能为空",groups = {ADD.class, EDIT.class})
    private TaskStatus taskStatus;

    /**
     * 优先级
     */
    @NotNull(message = "优先级不能为空",groups = {ADD.class, EDIT.class})
    private Integer priority;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 所属项目组或部门
     */
    @NotBlank(message = "所属项目组或部门不能为空",groups = {ADD.class, EDIT.class})
    private String projectId;

    /**
     * 所属模块
     */
    @NotBlank(message = "所属模块不能为空",groups = {ADD.class, EDIT.class})
    private String moduleId;

    /**
     * 任务类型
     */
    @NotBlank(message = "任务类型不能为空",groups = {ADD.class, EDIT.class})
    private String taskType;

    /**
     * 任务详情
     */
    @NotBlank(message = "任务详情不能为空",groups = {ADD.class, EDIT.class})
    private String taskDetail;

    /**
     * 注意事项
     */
    @NotBlank(message = "注意事项不能为空",groups = {ADD.class, EDIT.class})
    private String announcements;

    /**
     * 任务负责人
     */
    @NotBlank(message = "任务负责人不能为空",groups = {ADD.class, EDIT.class})
    private String taskPrincipal;

    /**
     * 任务人
     */
    @NotBlank(message = "任务人不能为空",groups = {ADD.class, EDIT.class})
    private String taskUser;

    /**
     * 所需工时
     */
    @NotNull(message = "所需工时不能为空",groups = {ADD.class, EDIT.class})
    private Double needManHour;

    /**
     * 实际工时
     */
    @NotNull(message = "实际工时不能为空",groups = {ADD.class, EDIT.class})
    private Double actualManHour;

    /**
     * 任务开始时间
     */
    @NotBlank(message = "任务开始时间不能为空",groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 任务结束时间
     */
    @NotBlank(message = "任务结束时间不能为空",groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 执行备注
     */
    private String remark;

    /**
     * 问题量
     */
    @NotNull(message = "问题量不能为空",groups = {ADD.class, EDIT.class})
    private Integer problems;

    /**
     * 是否发生费用报销
     */
    @NotNull(message = "是否发生费用报销不能为空",groups = {ADD.class, EDIT.class})
    private Boolean reimburse;


    public List<String> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<String> nodeIds) {
        this.nodeIds = nodeIds;
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