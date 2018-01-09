package com.bjike.goddess.taskallotment.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.*;

import java.time.LocalDateTime;

/**
 * 任务节点excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-28 10:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaskNodeExcel extends BaseTO {
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
    private LocalDateTime planTime;

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

    /**
     * 任务是否拆分
     */
    @ExcelHeader(name = "任务是否拆分")
    private String split;

    /**
     * 拆分为（天）
     */
    @ExcelHeader(name = "拆分为（天）")
    private Double day;
    /**
     * 执行时长
     */
    @ExcelHeader(name = "执行时长")
    private Double executeTime;

    /**
     * 执行时长时间类型
     */
    @ExcelHeader(name = "执行时长时间类型")
    private TimeType executeType;
    /**
     * 开始执行时间
     */
    @ExcelHeader(name = "开始执行时间")
    private LocalDateTime startExecute;
    /**
     * 结束执行时间
     */
    @ExcelHeader(name = "结束执行时间")
    private LocalDateTime endExecute;
    /**
     * 实际时长
     */
    @ExcelHeader(name = "实际时长")
    private Double actualTime;
    /**
     * 实际时长时间类型
     */
    @ExcelHeader(name = "实际时长时间类型")
    private TimeType actualType;
    /**
     * 未完成时长
     */
    @ExcelHeader(name = "未完成时长")
    private Double undoneTime;
    /**
     * 未完成时长时间类型
     */
    @ExcelHeader(name = "未完成时长时间类型")
    private TimeType undoneType;
    /**
     * 完成时间
     */
    @ExcelHeader(name = "完成时间")
    private LocalDateTime finishTime;
    /**
     * 执行人地区
     */
    @ExcelHeader(name = "执行人地区")
    private String executeArea;

    /**
     * 执行人部门
     */
    @ExcelHeader(name = "执行人部门")
    private String executeDepart;
    /**
     * 完成状态
     */
    @ExcelHeader(name = "完成状态")
    private FinishStatus finishStatus;
    /**
     * 确认状态
     */
    @ExcelHeader(name = "确认状态")
    private String confirm;
    /**
     * 不确认理由
     */
    @ExcelHeader(name = "不确认理由")
    private String reason;
    /**
     * 是否发生费用报销
     */
    @ExcelHeader(name = "是否发生费用报销")
    private String reimbursement;

    /**
     * 注意事项
     */
    @ExcelHeader(name = "注意事项")
    private String notice;
    /**
     * 是否上报
     */
    @ExcelHeader(name = "是否上报")
    private String report;
    /**
     * 是否延期完成
     */
    @ExcelHeader(name = "是否延期完成")
    private String delay;
    /**
     * 延期时长
     */
    @ExcelHeader(name = "延期时长")
    private Double delayTime;
    /**
     * 延期时长时间类型
     */
    @ExcelHeader(name = "延期时长时间类型")
    private TimeType delayType;
    /**
     * 上报原因
     */
    @ExcelHeader(name = "上报原因")
    private String reportReason;
    /**
     * 审核上报类型
     */
    @ExcelHeader(name = "审核上报类型")
    private AduitType aduitType;
    /**
     * 商量结果
     */
    @ExcelHeader(name = "商量结果")
    private String result;
    /**
     * 不通过理由
     */
    @ExcelHeader(name = "不通过理由")
    private String notPassReason;
    /**
     * 任务工作总结
     */
    @ExcelHeader(name = "任务工作总结")
    private String summary;
    /**
     * 任务状态
     */
    @ExcelHeader(name = "任务状态")
    private TaskStatus taskStatus;
    /**
     * 优先级
     */
    @ExcelHeader(name = "优先级")
    private Integer priority;
    /**
     * 工作效率
     */
    @ExcelHeader(name = "工作效率")
    private Double efficiency;

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

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
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

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }

    public TimeType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(TimeType executeType) {
        this.executeType = executeType;
    }

    public LocalDateTime getStartExecute() {
        return startExecute;
    }

    public void setStartExecute(LocalDateTime startExecute) {
        this.startExecute = startExecute;
    }

    public LocalDateTime getEndExecute() {
        return endExecute;
    }

    public void setEndExecute(LocalDateTime endExecute) {
        this.endExecute = endExecute;
    }

    public Double getActualTime() {
        return actualTime;
    }

    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    public TimeType getActualType() {
        return actualType;
    }

    public void setActualType(TimeType actualType) {
        this.actualType = actualType;
    }

    public Double getUndoneTime() {
        return undoneTime;
    }

    public void setUndoneTime(Double undoneTime) {
        this.undoneTime = undoneTime;
    }

    public TimeType getUndoneType() {
        return undoneType;
    }

    public void setUndoneType(TimeType undoneType) {
        this.undoneType = undoneType;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getExecuteArea() {
        return executeArea;
    }

    public void setExecuteArea(String executeArea) {
        this.executeArea = executeArea;
    }

    public String getExecuteDepart() {
        return executeDepart;
    }

    public void setExecuteDepart(String executeDepart) {
        this.executeDepart = executeDepart;
    }

    public FinishStatus getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(FinishStatus finishStatus) {
        this.finishStatus = finishStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Double getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Double delayTime) {
        this.delayTime = delayTime;
    }

    public TimeType getDelayType() {
        return delayType;
    }

    public void setDelayType(TimeType delayType) {
        this.delayType = delayType;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public AduitType getAduitType() {
        return aduitType;
    }

    public void setAduitType(AduitType aduitType) {
        this.aduitType = aduitType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNotPassReason() {
        return notPassReason;
    }

    public void setNotPassReason(String notPassReason) {
        this.notPassReason = notPassReason;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(String reimbursement) {
        this.reimbursement = reimbursement;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
