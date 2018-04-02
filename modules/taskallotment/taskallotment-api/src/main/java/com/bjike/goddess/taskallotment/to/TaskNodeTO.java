package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 任务节点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskNodeTO extends BaseTO {

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public Integer getGpriority() {
        return gpriority;
    }

    public void setGpriority(Integer gpriority) {
        this.gpriority = gpriority;
    }

    public TimesType getTimesType() {
        return timesType;
    }

    public void setTimesType(TimesType timesType) {
        this.timesType = timesType;
    }

    public interface INITIATE {
    }
    public interface INCREASE {
    }
    public interface ADDTASK {
    }

    public interface UNCONFIRM {
    }

    public interface WRITE {
    }

    public interface REPORT {
    }

    public interface AGAIN {
    }

    public interface CONFIRM {
    }

    public interface WRITES {
    }

    public interface PASS {
    }

    public interface NOTPASS {
    }

    /**
     * 地区
     */
    @NotBlank(groups = TaskNodeTO.ADDTASK.class, message = "地区不能为空")
    private String area;
    /**
     * 项目组/部门
     */
    @NotBlank(groups = TaskNodeTO.ADDTASK.class, message = "项目组/部门不能为空")
    private String depart;
    /**
     * 项目名称
     */
    @NotBlank(groups = TaskNodeTO.ADDTASK.class, message = "项目名称不能为空")
    private String projectId;
    /**
     * 项目表id
     */
    @NotBlank(groups = {ADD.class}, message = "项目表id不能为空")
    private String tableId;
    /**
     * 项目表
     */
    @NotBlank(groups = TaskNodeTO.ADDTASK.class, message = "项目表不能为空")
    private String table;
    /**
     * 任务名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.CONFIRM.class}, message = "任务名称不能为空")
    private String taskName;

    /**
     * 负责人
     */
    private String charge;

    /**
     * 执行人
     */
    @NotBlank(groups = {TaskNodeTO.AGAIN.class, TaskNodeTO.INITIATE.class, TaskNodeTO.CONFIRM.class}, message = "执行人不能为空")
    private String execute;

    /**
     * 计划执行时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class}, message = "计划执行时间不能为空")
    private String planTime;

    /**
     * 任务类型
     */
    @NotNull(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.CONFIRM.class}, message = "任务类型不能为空")
    private TaskType taskType;

    /**
     * 时长类型
     */
    @NotNull(groups = {ADD.class, TaskNodeTO.INITIATE.class, EDIT.class}, message = "时长类型不能为空")
    private TimesType timesType;

    /**
     * 功能模块
     */
    @NotNull(groups = {ADD.class,TaskNodeTO.INITIATE.class, EDIT.class}, message = "功能模块不能为空")
    private String moudle;

    /**
     * 功能优先级
     */
    @NotNull(groups = {ADD.class,TaskNodeTO.INITIATE.class, EDIT.class}, message = "功能优先级不能为空")
    private Integer gpriority;

    /**
     * 任务内容
     */
    @NotBlank(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.CONFIRM.class}, message = "任务内容不能为空")
    private String content;

    /**
     * `
     * 计划任务量
     */
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "计划任务量必须大于0")
    @NotNull(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "计划任务量")
    private Double planNum;
    /**
     * 完成任务量
     */
    @NotNull(groups = {TaskNodeTO.CONFIRM.class, TaskNodeTO.WRITE.class}, message = "完成任务量不能为空")
    private Double actualNum;

    /**
     * 所需时长
     */
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "所需时长必须大于0")
    @NotNull(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "所需时长不能为空")
    private Double needTime;

    /**
     * 所需时长时间类型
     */
    @NotNull(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.CONFIRM.class, TaskNodeTO.AGAIN.class}, message = "所需时长时间类型不能为空")
    private TimeType needType;

    /**
     * 任务开始时间
     */
    @NotBlank(groups = {TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "任务开始时间不能为空")
    private String startTime;
    /**
     * 任务结束时间
     */
    @NotBlank(groups = {TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class, TaskNodeTO.CONFIRM.class}, message = "任务结束时间不能为空")
    private String endTime;

    /**
     * 备注
     */
    private String remark;
    /**
     * 不确认理由
     */
    @NotBlank(groups = TaskNodeTO.UNCONFIRM.class, message = "不确认理由不能为空")
    private String reason;

    /**
     * 任务是否拆分
     */
    @NotNull(groups = {TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class, TaskNodeTO.AGAIN.class,TaskNodeTO.INCREASE.class}, message = "任务是否拆分不能为空")
    private Boolean split;

    /**
     * 拆分为（天）
     */
    @NotNull(groups = {TaskNodeTO.AGAIN.class}, message = "拆分为（天）不能为空")
    private Double day;
    /**
     * 执行时长
     */
    @DecimalMin(value = "0.00", groups = TaskNodeTO.WRITE.class, message = "执行时长不能小于0")
    @NotNull(groups = TaskNodeTO.WRITE.class, message = "执行时长不能为空")
    private Double executeTime;

    /**
     * 执行时长时间类型
     */
    @NotNull(groups = TaskNodeTO.WRITE.class, message = "执行时长时间类型不能为空")
    private TimeType executeType;
    /**
     * 开始执行时间
     */
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.WRITES.class}, message = "开始执行时间不能为空")
    private String startExecute;
    /**
     * 结束执行时间
     */
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.WRITES.class}, message = "结束执行时间不能为空")
    private String endExecute;
    /**
     * 实际时长
     */
    @DecimalMin(value = "0.00", groups = {TaskNodeTO.WRITE.class, TaskNodeTO.CONFIRM.class, TaskNodeTO.WRITES.class}, message = "实际时长不能小于0")
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.CONFIRM.class, TaskNodeTO.WRITES.class}, message = "实际时长不能为空")
    private Double actualTime;
    /**
     * 实际时长时间类型
     */
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.CONFIRM.class}, message = "实际时长时间类型不能为空")
    private TimeType actualType;
    /**
     * 未完成时长
     */
    private Double undoneTime;
    /**
     * 未完成时长时间类型
     */
    private TimeType undoneType;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 执行人地区
     */
    @NotBlank(groups = {TaskNodeTO.ADDTASK.class}, message = "执行人地区不能为空")
    private String executeArea;

    /**
     * 执行人部门
     */
    @NotBlank(groups = {TaskNodeTO.ADDTASK.class}, message = "执行人部门不能为空")
    private String executeDepart;
    /**
     * 完成状态
     */
    @NotNull(groups = TaskNodeTO.WRITE.class, message = "完成状态不能为空")
    private FinishStatus finishStatus;
    /**
     * 确认状态
     */
    private Boolean confirm;
    /**
     * 是否发生费用报销
     */
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.CONFIRM.class, TaskNodeTO.WRITES.class}, message = "是否发生费用报销不能为空")
    private Boolean reimbursement;

    /**
     * 注意事项
     */
    private String notice;
    /**
     * 是否有附件
     */
    private Boolean attachment;
    /**
     * 是否出现问题
     */
    @NotNull(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.WRITES.class}, message = "是否出现问题不能为空")
    private Boolean question;

    /**
     * 是否上报
     */
    private Boolean report;
    /**
     * 是否延期完成
     */
    @NotNull(groups = TaskNodeTO.REPORT.class, message = "是否延期完成不能为空")
    private Boolean delay;
    /**
     * 延期时长
     */
    @DecimalMin(value = "0.00", groups = {TaskNodeTO.REPORT.class}, message = "延期时长必须大于0")
    @NotNull(groups = TaskNodeTO.REPORT.class, message = "延期时长不能为空")
    private Double delayTime;
    /**
     * 延期时长时间类型
     */
    @NotNull(groups = TaskNodeTO.REPORT.class, message = "延期时长时间类型不能为空")
    private TimeType delayType;
    /**
     * 上报原因
     */
    @NotBlank(groups = TaskNodeTO.REPORT.class, message = "上报原因不能为空")
    private String reportReason;
    /**
     * 商量结果
     */
    @NotBlank(groups = TaskNodeTO.PASS.class, message = "商量结果不能为空")
    private String result;
    /**
     * 不通过理由
     */
    @NotBlank(groups = TaskNodeTO.NOTPASS.class, message = "不通过理由不能为空")
    private String notPassReason;
    /**
     * 任务工作总结
     */
    @NotBlank(groups = {TaskNodeTO.WRITE.class, TaskNodeTO.WRITES.class}, message = "任务工作总结不能为空")
    private String summary;
    /**
     * 任务状态
     */
    @NotNull(groups = {ADD.class, EDIT.class, TaskNodeTO.INITIATE.class, TaskNodeTO.ADDTASK.class}, message = "任务状态不能为空")
    private TaskStatus taskStatus;
    /**
     * 问题集合
     */
    private List<QuestionTO> questions;
    /**
     * 自定义字段集合
     */
    private List<CustomTitleTO> customTitles;

    /**
     * 增加天数
     */
    @NotNull(groups = {TaskNodeTO.INCREASE.class}, message = "增加天数不能为空")
    private Double increase;


    private String initiate;

    private String innerProject;

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getInitiate() {
        return initiate;
    }

    public void setInitiate(String initiate) {
        this.initiate = initiate;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
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

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public Boolean getQuestion() {
        return question;
    }

    public void setQuestion(Boolean question) {
        this.question = question;
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

    public List<QuestionTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionTO> questions) {
        this.questions = questions;
    }

    public List<CustomTitleTO> getCustomTitles() {
        return customTitles;
    }

    public void setCustomTitles(List<CustomTitleTO> customTitles) {
        this.customTitles = customTitles;
    }
}