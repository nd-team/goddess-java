package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.FinishStatus;
import com.bjike.goddess.taskallotment.enums.TaskStatus;
import com.bjike.goddess.taskallotment.enums.TaskType;


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
     * 节点名称
     */
    @ExcelHeader(name = "节点名称")
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
    @ExcelHeader(name = "计划执行时间")
    private String planTime;

    /**
     * 任务类型
     */
    @ExcelHeader(name = "任务类型")
    private TaskType taskType;

    /**
     * 任务内容
     */
    @ExcelHeader(name = "任务内容")
    private String content;

    /**
     * 计划任务量
     */
    @ExcelHeader(name = "计划任务量")
    private Double planNum;
    /**
     * 完成任务量
     */
    @ExcelHeader(name = "完成任务量")
    private Double actualNum;

    /**
     * 任务开始时间
     */
    @ExcelHeader(name = "任务开始时间")
    private String startTime;
    /**
     * 任务结束时间
     */
    @ExcelHeader(name = "任务结束时间")
    private String endTime;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    /**
     * 开始执行时间
     */
    @ExcelHeader(name = "开始执行时间")
    private String startExecute;
    /**
     * 结束执行时间
     */
    @ExcelHeader(name = "结束执行时间")
    private String endExecute;
    /**
     * 完成时间
     */
    @ExcelHeader(name = "完成时间")
    private String finishTime;
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
    private Boolean confirm;
    /**
     * 不确认理由
     */
    @ExcelHeader(name = "不确认理由")
    private String reason;
    /**
     * 是否发生费用报销
     */
    @ExcelHeader(name = "是否发生费用报销")
    private Boolean reimbursement;

    /**
     * 注意事项
     */
    @ExcelHeader(name = "注意事项")
    private String notice;
    /**
     * 是否上报
     */
    @ExcelHeader(name = "是否上报")
    private Boolean report;
    /**
     * 是否延期完成
     */
    @ExcelHeader(name = "是否延期完成")
    private Boolean delay;
    /**
     * 上报原因
     */
    @ExcelHeader(name = "上报原因")
    private String reportReason;
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

    public String getStartExecute() {
        return startExecute;
    }

    public void setStartExecute(String startExecute) {
        this.startExecute = startExecute;
    }

    public String getEndExecute() {
        return endExecute;
    }

    public void setEndExecute(String endExecute) {
        this.endExecute = endExecute;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
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

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(Boolean reimbursement) {
        this.reimbursement = reimbursement;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Boolean getDelay() {
        return delay;
    }

    public void setDelay(Boolean delay) {
        this.delay = delay;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
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
}
