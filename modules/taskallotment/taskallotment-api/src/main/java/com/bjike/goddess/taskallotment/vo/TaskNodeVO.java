package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.taskallotment.bo.CustomTitleBO;
import com.bjike.goddess.taskallotment.bo.QuestionBO;
import com.bjike.goddess.taskallotment.enums.FinishStatus;
import com.bjike.goddess.taskallotment.enums.TaskStatus;
import com.bjike.goddess.taskallotment.enums.TaskType;
import com.bjike.goddess.taskallotment.enums.TimeType;

import java.util.List;

/**
 * 任务节点表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskNodeVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;
    /**
     * 项目表id
     */
    private String tableId;
    /**
     * 部门
     */
    private String depart;
    /**
     * 项目名
     */
    private String project;
    /**
     * 内部项目名称
     */
    private String innerProject;
    /**
     * 项目表
     */
    private String table;
    /**
     * 发起人
     */
    private String initiate;
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 负责人
     */
    private String charge;

    /**
     * 执行人
     */
    private String execute;

    /**
     * 计划执行时间
     */
    private String planTime;

    /**
     * 任务创建时间
     */
    private String createTime;

    /**
     * 任务类型
     */
    private TaskType taskType;

    /**
     * 类型
     */
    private String type;

    /**
     * 任务内容
     */
    private String content;

    /**
     * 计划任务量
     */
    private Double planNum;
    /**
     * 完成任务量
     */
    private Double actualNum;

    /**
     * 所需时长
     */
    private String needTime;
    /**
     * 所需时长
     */
    private Double needTime1;
    /**
     * 所需时长时间类型
     */
    private TimeType needType;
    /**
     * 任务开始时间
     */
    private String startTime;
    /**
     * 任务结束时间
     */
    private String endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 任务是否拆分
     */
    private Boolean split;

    /**
     * 拆分为（天）
     */
    private Double day;
    /**
     * 执行时长
     */
    private Double executeTime1;

    /**
     * 执行时长时间类型
     */
    private TimeType executeType;
    /**
     * 执行时长
     */
    private String executeTime;

    /**
     * 开始执行时间
     */
    private String startExecute;
    /**
     * 结束执行时间
     */
    private String endExecute;
    /**
     * 实际时长
     */
    private Double actualTime1;
    /**
     * 实际时长时间类型
     */
    private TimeType actualType;
    /**
     * 未完成时长
     */
    private Double undoneTime1;
    /**
     * 未完成时长时间类型
     */
    private TimeType undoneType;
    /**
     * 实际时长
     */
    private String actualTime;
    /**
     * 未完成时长
     */
    private String undoneTime;
    /**
     * 完成时间
     */
    private String finishTime;
    /**
     * 执行人地区
     */
    private String executeArea;

    /**
     * 执行人部门
     */
    private String executeDepart;
    /**
     * 完成状态
     */
    private FinishStatus finishStatus;
    /**
     * 确认状态
     */
    private Boolean confirm;
    /**
     * 是否发生费用报销
     */
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
    private Boolean question;

    /**
     * 是否上报
     */
    private Boolean report;
    /**
     * 是否延期完成
     */
    private Boolean delay;
    /**
     * 延期时长
     */
    private String delayTime;
    /**
     * 延期时长
     */
    private Double delayTime1;
    /**
     * 延期时长时间类型
     */
    private TimeType delayType;
    /**
     * 上报原因
     */
    private String reportReason;
    /**
     * 任务工作总结
     */
    private String summary;
    /**
     * 任务状态
     */
    private TaskStatus taskStatus;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 工作效率
     */
    private Double efficiency;

    /**
     * 自定义字段信息
     */
    private List<CustomTitleBO> customTitles;
    /**
     * 问题信息
     */
    private List<QuestionBO> questions;
    /**
     * 图片路径
     */
    private List<String> photos;

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getDelayTime1() {
        return delayTime1;
    }

    public void setDelayTime1(Double delayTime1) {
        this.delayTime1 = delayTime1;
    }

    public TimeType getDelayType() {
        return delayType;
    }

    public void setDelayType(TimeType delayType) {
        this.delayType = delayType;
    }

    public Double getNeedTime1() {
        return needTime1;
    }

    public void setNeedTime1(Double needTime1) {
        this.needTime1 = needTime1;
    }

    public TimeType getNeedType() {
        return needType;
    }

    public void setNeedType(TimeType needType) {
        this.needType = needType;
    }

    public Double getExecuteTime1() {
        return executeTime1;
    }

    public void setExecuteTime1(Double executeTime1) {
        this.executeTime1 = executeTime1;
    }

    public TimeType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(TimeType executeType) {
        this.executeType = executeType;
    }

    public Double getActualTime1() {
        return actualTime1;
    }

    public void setActualTime1(Double actualTime1) {
        this.actualTime1 = actualTime1;
    }

    public TimeType getActualType() {
        return actualType;
    }

    public void setActualType(TimeType actualType) {
        this.actualType = actualType;
    }

    public Double getUndoneTime1() {
        return undoneTime1;
    }

    public void setUndoneTime1(Double undoneTime1) {
        this.undoneTime1 = undoneTime1;
    }

    public TimeType getUndoneType() {
        return undoneType;
    }

    public void setUndoneType(TimeType undoneType) {
        this.undoneType = undoneType;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
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

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
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

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
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

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getUndoneTime() {
        return undoneTime;
    }

    public void setUndoneTime(String undoneTime) {
        this.undoneTime = undoneTime;
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

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
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

    public List<CustomTitleBO> getCustomTitles() {
        return customTitles;
    }

    public void setCustomTitles(List<CustomTitleBO> customTitles) {
        this.customTitles = customTitles;
    }

    public List<QuestionBO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionBO> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}