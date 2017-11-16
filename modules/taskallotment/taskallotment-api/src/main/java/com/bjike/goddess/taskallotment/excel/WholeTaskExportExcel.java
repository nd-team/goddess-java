package com.bjike.goddess.taskallotment.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.enums.TaskType;
import com.bjike.goddess.taskallotment.enums.TimeType;

import java.time.LocalDateTime;

/**
 * 整体任务进度导出excel
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-10-28 10:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WholeTaskExportExcel extends BaseTO {
    /**
     * 表id
     */
    private String tableId;
    /**
     * 表名称
     */
    @ExcelHeader(name = "表名称", notNull = true)
    private String name;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private Status status;
    /**
     * 创建人
     */
    @ExcelHeader(name = "创建人", notNull = true)
    private String creater;

    /**
     * 发起人
     */
    @ExcelHeader(name = "发起人")
    private String initiate;
    /**
     * 任务名称
     */
    @ExcelHeader(name = "任务名称", notNull = true)
    private String taskName;

    /**
     * 负责人
     */
    @ExcelHeader(name = "负责人")
    private String charge;

    /**
     * 执行人
     */
    @ExcelHeader(name = "执行人")
    private String execute;

    /**
     * 计划执行时间
     */
    @ExcelHeader(name = "计划执行时间", notNull = true)
    private String planTime;

    /**
     * 任务类型
     */
    @ExcelHeader(name = "任务类型", notNull = true)
    private TaskType taskType;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型", notNull = true)
    private String type;

    /**
     * 任务内容
     */
    @ExcelHeader(name = "任务内容", notNull = true)
    private String content;

    /**
     * 计划任务量
     */
    @ExcelHeader(name = "计划任务量", notNull = true)
    private Double planNum;
    /**
     * 完成任务量
     */
    @ExcelHeader(name = "完成任务量")
    private Double actualNum;

    /**
     * 所需时长
     */
    @ExcelHeader(name = "所需时长", notNull = true)
    private Double needTime;

    /**
     * 所需时长时间类型
     */
    @ExcelHeader(name = "所需时长时间类型", notNull = true)
    private TimeType needType;

    /**
     * 任务开始时间
     */
    @ExcelHeader(name = "任务开始时间")
    private LocalDateTime startTime;
    /**
     * 任务结束时间
     */
    @ExcelHeader(name = "任务结束时间")
    private LocalDateTime endTime;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getInitiate() {
        return initiate;
    }

    public void setInitiate(String initiate) {
        this.initiate = initiate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Double planNum) {
        this.planNum = planNum;
    }

    public Double getActualNum() {
        return actualNum;
    }

    public void setActualNum(Double actualNum) {
        this.actualNum = actualNum;
    }

    public Double getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Double needTime) {
        this.needTime = needTime;
    }

    public TimeType getNeedType() {
        return needType;
    }

    public void setNeedType(TimeType needType) {
        this.needType = needType;
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
}
