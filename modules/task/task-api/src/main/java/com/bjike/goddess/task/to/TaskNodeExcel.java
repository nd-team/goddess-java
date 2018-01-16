package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.FinishStatus;
import com.bjike.goddess.taskallotment.enums.TaskStatus;
import com.bjike.goddess.taskallotment.enums.TaskType;
import com.bjike.goddess.taskallotment.enums.TimeType;


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
    //@ExcelHeader(name = "计划执行时间")
    //private String planTime;

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
    //@ExcelHeader(name = "任务开始时间")
    //private String startTime;
    /**
     * 任务结束时间
     */
    //@ExcelHeader(name = "任务结束时间")
    //private String endTime;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    /**
     * 开始执行时间
     */
    //@ExcelHeader(name = "开始执行时间")
    //private String startExecute;
    /**
     * 结束执行时间
     */
    //@ExcelHeader(name = "结束执行时间")
    //private String endExecute;
    /**
     * 完成时间
     */
    //@ExcelHeader(name = "完成时间")
    //private String finishTime;
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
    //@ExcelHeader(name = "确认状态")
    //private Boolean confirm;
    /**
     * 不确认理由
     */
    //@ExcelHeader(name = "不确认理由")
    //private String reason;
    /**
     * 是否发生费用报销
     */
    //@ExcelHeader(name = "是否发生费用报销")
    //private Boolean reimbursement;

    /**
     * 注意事项
     */
    //@ExcelHeader(name = "注意事项")
    //private String notice;
    /**
     * 是否上报
     */
    //@ExcelHeader(name = "是否上报")
    //private Boolean report;
    /**
     * 是否延期完成
     */
    //@ExcelHeader(name = "是否延期完成")
    //private Boolean delay;
    /**
     * 上报原因
     */
    //@ExcelHeader(name = "上报原因")
    //private String reportReason;
    /**
     * 任务工作总结
     */
    //@ExcelHeader(name = "任务工作总结")
    //private String summary;
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
    //@ExcelHeader(name = "工作效率")
    //private Double efficiency;

    /**
     * ---------------
     * 2018-01-09
     * 新增汇总字段
     * 计划数
     */
    @ExcelHeader(name = "计划数")
    private String count;

    /**
     * 执行时长
     */
    @ExcelHeader(name = "执行时长")
    private String executeTime;
    /**
     * 执行时长时间类型
     */
    @ExcelHeader(name = "执行时长时间类型")
    private TimeType executeType;

    /**
     * 所需时长
     */
    @ExcelHeader(name = "所需时长")
    private String needTime;
    /**
     * 所需时长时间类型
     */
    @ExcelHeader(name = "所需时长时间类型")
    private TimeType needType;

    /**
     * 实际时长
     */
    @ExcelHeader(name = "实际时长")
    private String actualTime;

    /**
     * 实际时长时间类型
     */
    @ExcelHeader(name = "实际时长时间类型")
    private TimeType actualType;

    /**
     * 人数
     */
    @ExcelHeader(name = "人数")
    private String peopleNum;

    /**
     * 总任务量
     */
    @ExcelHeader(name = "总任务量")
    private String taskVolume;

    /**
     * 已完成任务量
     */
    @ExcelHeader(name = "已完成任务量")
    private String fiTaskVolume;

    /**
     * 未完成任务量
     */
    @ExcelHeader(name = "未完成任务量")
    private String noTaskVolume;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTaskVolume() {
        return taskVolume;
    }

    public void setTaskVolume(String taskVolume) {
        this.taskVolume = taskVolume;
    }

    public String getFiTaskVolume() {
        return fiTaskVolume;
    }

    public void setFiTaskVolume(String fiTaskVolume) {
        this.fiTaskVolume = fiTaskVolume;
    }

    public String getNoTaskVolume() {
        return noTaskVolume;
    }

    public void setNoTaskVolume(String noTaskVolume) {
        this.noTaskVolume = noTaskVolume;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public TimeType getExecuteType() {
        return executeType;
    }

    public void setExecuteType(TimeType executeType) {
        this.executeType = executeType;
    }

    public String getNeedTime() {
        return needTime;
    }

    public void setNeedTime(String needTime) {
        this.needTime = needTime;
    }

    public TimeType getNeedType() {
        return needType;
    }

    public void setNeedType(TimeType needType) {
        this.needType = needType;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public TimeType getActualType() {
        return actualType;
    }

    public void setActualType(TimeType actualType) {
        this.actualType = actualType;
    }
}
