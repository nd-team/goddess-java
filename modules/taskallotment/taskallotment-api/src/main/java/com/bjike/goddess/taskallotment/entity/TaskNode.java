package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 任务节点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_tasknode")
public class TaskNode extends BaseEntity {
    /**
     * 父id（用于拆分任务识别）
     */
    @Column(name = "father_id", columnDefinition = "VARCHAR(36)   COMMENT '父id'")
    private String fatherId;

    /**
     * 是否有子记录（用于拆分任务识别）
     */
    @Column(name = "haveSon", columnDefinition = "TINYINT(1)   COMMENT '是否有子记录'")
    private Boolean haveSon;
    /**
     * 发起人
     */
    @Column(name = "initiate", columnDefinition = "VARCHAR(255)   COMMENT '发起人'")
    private String initiate;
    /**
     * 任务名称
     */
    @Column(name = "taskName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务名称'")
    private String taskName;

    /**
     * 负责人
     */
    @Column(name = "charge", columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String charge;

    /**
     * 执行人
     */
    @Column(name = "execute", columnDefinition = "VARCHAR(255)   COMMENT '执行人'")
    private String execute;

    /**
     * 计划执行时间
     */
    @Column(name = "planTime", nullable = false, columnDefinition = "DATETIME   COMMENT '计划执行时间'")
    private LocalDateTime planTime;

    /**
     * 任务类型
     */
    @Column(name = "taskType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '任务类型'")
    private TaskType taskType;
    /**
     * 时长类型
     */
    @Column(name = "timesType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '任务类型'")
    private TimesType timesType;

    /**
     * 功能模块
     */
    @Column(name = "moudle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '功能模块'")
    private String moudle;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;

    /**
     * 任务内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "TEXT   COMMENT '任务内容'")
    private String content;

    /**
     * 计划任务量
     */
    @Column(name = "planNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划任务量'")
    private Double planNum;
    /**
     * 完成任务量
     */
    @Column(name = "actualNum", columnDefinition = "DECIMAL(10,2)   COMMENT '完成任务量'")
    private Double actualNum;

    /**
     * 所需时长
     */
    @Column(name = "needTime", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '所需时长'")
    private Double needTime;

    /**
     * 所需时长时间类型
     */
    @Column(name = "needType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '所需时长时间类型'")
    private TimeType needType;

    /**
     * 任务开始时间
     */
    @Column(name = "startTime", columnDefinition = "DATETIME   COMMENT '任务开始时间'")
    private LocalDateTime startTime;
    /**
     * 任务结束时间
     */
    @Column(name = "endTime", columnDefinition = "DATETIME   COMMENT '任务结束时间'")
    private LocalDateTime endTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 任务是否拆分
     */
    @Column(name = "is_split", columnDefinition = "TINYINT(1)   COMMENT '时间类型'")
    private Boolean split;

    /**
     * 拆分为（天）
     */
    @Column(name = "day", columnDefinition = "DECIMAL(10,2)   COMMENT '拆分为（天）'")
    private Double day;
    /**
     * 执行时长
     */
    @Column(name = "executeTime", columnDefinition = "DECIMAL(10,2)   COMMENT '执行时长'")
    private Double executeTime;

    /**
     * 执行时长时间类型
     */
    @Column(name = "executeType", columnDefinition = "TINYINT(2)   COMMENT '执行时长时间类型'")
    private TimeType executeType;
    /**
     * 实际开始执行时间
     */
    @Column(name = "startExecute", columnDefinition = "DATETIME   COMMENT '开始执行时间'")
    private LocalDateTime startExecute;
    /**
     * 实际结束执行时间
     */
    @Column(name = "endExecute", columnDefinition = "DATETIME   COMMENT '结束执行时间'")
    private LocalDateTime endExecute;
    /**
     * 实际时长
     */
    @Column(name = "actualTime", columnDefinition = "DECIMAL(10,2)   COMMENT '实际时长'")
    private Double actualTime;
    /**
     * 实际时长时间类型
     */
    @Column(name = "actualType", columnDefinition = "TINYINT(2)   COMMENT '实际时长时间类型'")
    private TimeType actualType;
    /**
     * 未完成时长
     */
    @Column(name = "undoneTime", columnDefinition = "DECIMAL(10,2)   COMMENT '未完成时长'")
    private Double undoneTime;
    /**
     * 未完成时长时间类型
     */
    @Column(name = "undoneType", columnDefinition = "TINYINT(2)   COMMENT '未完成时长时间类型'")
    private TimeType undoneType;
    /**
     * 完成时间
     */
    @Column(name = "finishTime", columnDefinition = "DATETIME   COMMENT '完成时间'")
    private LocalDateTime finishTime;
    /**
     * 执行人地区
     */
    @Column(name = "executeArea", columnDefinition = "VARCHAR(255)   COMMENT '执行人地区'")
    private String executeArea;

    /**
     * 执行人部门
     */
    @Column(name = "executeDepart", columnDefinition = "VARCHAR(255)   COMMENT '执行人部门'")
    private String executeDepart;
    /**
     * 完成状态
     */
    @Column(name = "finishStatus", columnDefinition = "TINYINT(2)   COMMENT '完成状态'")
    private FinishStatus finishStatus;
    /**
     * 确认状态
     */
    @Column(name = "is_confirm", columnDefinition = "TINYINT(1)   COMMENT '确认状态'")
    private Boolean confirm;
    /**
     * 不确认理由
     */
    @Column(name = "reason", columnDefinition = "TEXT   COMMENT '不确认理由'")
    private String reason;
    /**
     * 是否发生费用报销
     */
    @Column(name = "is_reimbursement", columnDefinition = "TINYINT(1)   COMMENT '是否发生费用报销'")
    private Boolean reimbursement;

    /**
     * 注意事项
     */
    @Column(name = "notice", columnDefinition = "TEXT   COMMENT '注意事项'")
    private String notice;
    /**
     * 是否有附件
     */
    @Column(name = "is_attachment", columnDefinition = "TINYINT(1)   COMMENT '是否有附件'")
    private Boolean attachment;
    /**
     * 是否出现问题
     */
    @Column(name = "is_question", columnDefinition = "TINYINT(1)   COMMENT '是否出现问题'")
    private Boolean question;

    /**
     * 是否上报
     */
    @Column(name = "is_report", columnDefinition = "TINYINT(1)   COMMENT '是否上报'")
    private Boolean report;
    /**
     * 是否延期完成
     */
    @Column(name = "is_delay", columnDefinition = "TINYINT(1)   COMMENT '是否延期完成'")
    private Boolean delay;
    /**
     * 延期时长
     */
    @Column(name = "delayTime", columnDefinition = "DECIMAL(10,2)    COMMENT '延期时长'")
    private Double delayTime;
    /**
     * 延期时长时间类型
     */
    @Column(name = "delayType", columnDefinition = "TINYINT(2)   COMMENT '延期时长时间类型'")
    private TimeType delayType;
    /**
     * 上报原因
     */
    @Column(name = "reportReason", columnDefinition = "TEXT   COMMENT '上报原因'")
    private String reportReason;
    /**
     * 审核上报类型
     */
    @Column(name = "aduitType", columnDefinition = "TINYINT(2)   COMMENT '审核上报类型'")
    private AduitType aduitType;
    /**
     * 商量结果
     */
    @Column(name = "result", columnDefinition = "TEXT   COMMENT '商量结果'")
    private String result;
    /**
     * 不通过理由
     */
    @Column(name = "notPassReason", columnDefinition = "TEXT   COMMENT '不通过理由'")
    private String notPassReason;
    /**
     * 任务工作总结
     */
    @Column(name = "summary", columnDefinition = "VARCHAR(255)   COMMENT '任务工作总结'")
    private String summary;
    /**
     * 任务状态
     */
    @Column(name = "taskStatus", columnDefinition = "TINYINT(2)   COMMENT '任务状态'")
    private TaskStatus taskStatus;
    /**
     * 优先级
     */
    @Column(name = "priority", columnDefinition = "INT(11)   COMMENT '优先级'")
    private Integer priority;
    /**
     * 功能优先级
     */
    @Column(name = "gpriority", columnDefinition = "INT(11)   COMMENT '优先级'")
    private Integer gpriority;

    /**
     * 工作效率
     */
    @Column(name = "efficiency", columnDefinition = "DECIMAL(10,2)    COMMENT '工作效率'")
    private Double efficiency;
    /**
     * 项目表信息
     */
    @Column(name = "table_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目表信息'")
    private String tableId;

    /**
     * 分发时间
     */
    @Column(name = "time", columnDefinition = "DATETIME   COMMENT '分发时间'")
    private LocalDateTime time;

//    /**
//     * 自定义字段信息
//     */
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.REFRESH,CascadeType.PERSIST}, mappedBy = "taskNode")
//    private List<CustomTitle> customTitles;
//    /**
//     * 问题信息
//     */
//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "taskNode")
//    private List<Question> questions;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public AduitType getAduitType() {
        return aduitType;
    }

    public void setAduitType(AduitType aduitType) {
        this.aduitType = aduitType;
    }

    public TimeType getNeedType() {
        return needType;
    }

    public void setNeedType(TimeType needType) {
        this.needType = needType;
    }

    public TimeType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(TimeType executeType) {
        this.executeType = executeType;
    }

    public TimeType getActualType() {
        return actualType;
    }

    public void setActualType(TimeType actualType) {
        this.actualType = actualType;
    }

    public TimeType getUndoneType() {
        return undoneType;
    }

    public void setUndoneType(TimeType undoneType) {
        this.undoneType = undoneType;
    }

    public TimeType getDelayType() {
        return delayType;
    }

    public void setDelayType(TimeType delayType) {
        this.delayType = delayType;
    }

    public Double getActualNum() {
        return actualNum;
    }

    public void setActualNum(Double actualNum) {
        this.actualNum = actualNum;
    }

    public Double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getInitiate() {
        return initiate;
    }

    public void setInitiate(String initiate) {
        this.initiate = initiate;
    }

//    public List<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<Question> questions) {
//        this.questions = questions;
//    }

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

    public Double getUndoneTime() {
        return undoneTime;
    }

    public void setUndoneTime(Double undoneTime) {
        this.undoneTime = undoneTime;
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

//    public List<CustomTitle> getCustomTitles() {
//        return customTitles;
//    }

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

//    public void setCustomTitles(List<CustomTitle> customTitles) {
//        this.customTitles = customTitles;
//    }

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

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
        this.planTime = planTime;
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

    public Double getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Double needTime) {
        this.needTime = needTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getHaveSon() {
        return haveSon;
    }

    public void setHaveSon(Boolean haveSon) {
        this.haveSon = haveSon;
    }

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
}