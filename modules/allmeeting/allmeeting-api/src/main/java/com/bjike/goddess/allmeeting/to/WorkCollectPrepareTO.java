package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 工作汇总议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkCollectPrepareTO extends BaseTO {

    /**
     * 会议编号
     */
    @NotBlank(message = "会议编号不能为空",groups = {ADD.class, EDIT.class})
    private String meetingNum;

    /**
     * 汇总开始时间
     */
    @NotBlank(message = "汇总开始时间不能为空",groups = {ADD.class,EDIT.class})
    private String startTime;

    /**
     * 汇总结束时间
     */
    @NotBlank(message = "汇总结束时间不能为空",groups = {ADD.class,EDIT.class})
    private String endTime;

    /**
     * 模块
     */
    @NotBlank(message = "模块不能为空",groups = {ADD.class,EDIT.class})
    private String module;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class,EDIT.class})
    private String name;

    /**
     * 计划任务量
     */
    @NotNull(message = "计划任务量不能为空",groups = {ADD.class,EDIT.class})
    private Integer planTasks;

    /**
     * 已完成任务数量
     */
    @NotNull(message = "已完成任务数量不能为空",groups = {ADD.class,EDIT.class})
    private Integer finishTasks;

    /**
     * 未完成任务数量
     */
    @NotNull(message = "未完成任务数量不能为空",groups = {ADD.class,EDIT.class})
    private Integer unFinishTasks;

    /**
     * 有交接任务数量
     */
    @NotNull(message = "有交接任务数量不能为空",groups = {ADD.class,EDIT.class})
    private Integer handoverTasks;

    /**
     * 未交接任务数量
     */
    @NotNull(message = "未交接任务数量不能为空",groups = {ADD.class,EDIT.class})
    private Integer unhandoverTasks;

    /**
     * 问题发现数量
     */
    @NotNull(message = "问题发现数量不能为空",groups = {ADD.class,EDIT.class})
    private Integer problems;

    /**
     * 总人数
     */
    @NotNull(message = "总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer totalUsers;

    /**
     * 迟到总人数
     */
    @NotNull(message = "迟到总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer lateUsers;

    /**
     * 早退总人数
     */
    @NotNull(message = "早退总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer leaveEarlyUsers;

    /**
     * 请假总人数
     */
    @NotNull(message = "请假总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer vacationUsers;

    /**
     * 旷工总人数
     */
    @NotNull(message = "旷工总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer absenteeismUsers;

    /**
     * 收入总金额
     */
    @NotNull(message = "收入总金额不能为空",groups = {ADD.class,EDIT.class})
    private Double totalIncome;

    /**
     * 借款总金额
     */
    @NotNull(message = "借款总金额不能为空",groups = {ADD.class,EDIT.class})
    private Double totalBorrow;

    /**
     * 借款总人数
     */
    @NotNull(message = "借款总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer borrowUsers;

    /**
     * 报销总金额
     */
    @NotNull(message = "报销总金额不能为空",groups = {ADD.class,EDIT.class})
    private Double claimingExpenses;

    /**
     * 报销总人数
     */
    @NotNull(message = "报销总人数不能为空",groups = {ADD.class,EDIT.class})
    private Integer claimingUsers;


    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlanTasks() {
        return planTasks;
    }

    public void setPlanTasks(Integer planTasks) {
        this.planTasks = planTasks;
    }

    public Integer getFinishTasks() {
        return finishTasks;
    }

    public void setFinishTasks(Integer finishTasks) {
        this.finishTasks = finishTasks;
    }

    public Integer getUnFinishTasks() {
        return unFinishTasks;
    }

    public void setUnFinishTasks(Integer unFinishTasks) {
        this.unFinishTasks = unFinishTasks;
    }

    public Integer getHandoverTasks() {
        return handoverTasks;
    }

    public void setHandoverTasks(Integer handoverTasks) {
        this.handoverTasks = handoverTasks;
    }

    public Integer getUnhandoverTasks() {
        return unhandoverTasks;
    }

    public void setUnhandoverTasks(Integer unhandoverTasks) {
        this.unhandoverTasks = unhandoverTasks;
    }

    public Integer getProblems() {
        return problems;
    }

    public void setProblems(Integer problems) {
        this.problems = problems;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Integer getLateUsers() {
        return lateUsers;
    }

    public void setLateUsers(Integer lateUsers) {
        this.lateUsers = lateUsers;
    }

    public Integer getLeaveEarlyUsers() {
        return leaveEarlyUsers;
    }

    public void setLeaveEarlyUsers(Integer leaveEarlyUsers) {
        this.leaveEarlyUsers = leaveEarlyUsers;
    }

    public Integer getVacationUsers() {
        return vacationUsers;
    }

    public void setVacationUsers(Integer vacationUsers) {
        this.vacationUsers = vacationUsers;
    }

    public Integer getAbsenteeismUsers() {
        return absenteeismUsers;
    }

    public void setAbsenteeismUsers(Integer absenteeismUsers) {
        this.absenteeismUsers = absenteeismUsers;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalBorrow() {
        return totalBorrow;
    }

    public void setTotalBorrow(Double totalBorrow) {
        this.totalBorrow = totalBorrow;
    }

    public Integer getBorrowUsers() {
        return borrowUsers;
    }

    public void setBorrowUsers(Integer borrowUsers) {
        this.borrowUsers = borrowUsers;
    }

    public Double getClaimingExpenses() {
        return claimingExpenses;
    }

    public void setClaimingExpenses(Double claimingExpenses) {
        this.claimingExpenses = claimingExpenses;
    }

    public Integer getClaimingUsers() {
        return claimingUsers;
    }

    public void setClaimingUsers(Integer claimingUsers) {
        this.claimingUsers = claimingUsers;
    }

}