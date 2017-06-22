package com.bjike.goddess.allmeeting.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 工作汇总议题准备信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkCollectPrepareBO extends BaseBO {

    /**
     * 会议编号
     */
    private String meetingNum;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 关联功能
     */
    private String relation;

    /**
     * 汇总开始时间
     */
    private String startTime;

    /**
     * 汇总结束时间
     */
    private String endTime;

    /**
     * 模块
     */
    private String module;

    /**
     * 姓名
     */
    private String name;

    /**
     * 计划任务量
     */
    private Integer planTasks;

    /**
     * 已完成任务数量
     */
    private Integer finishTasks;

    /**
     * 未完成任务数量
     */
    private Integer unFinishTasks;

    /**
     * 有交接任务数量
     */
    private Integer handoverTasks;

    /**
     * 未交接任务数量
     */
    private Integer unhandoverTasks;

    /**
     * 问题发现数量
     */
    private Integer problems;

    /**
     * 总人数
     */
    private Integer totalUsers;

    /**
     * 迟到总人数
     */
    private Integer lateUsers;

    /**
     * 早退总人数
     */
    private Integer leaveEarlyUsers;

    /**
     * 请假总人数
     */
    private Integer vacationUsers;

    /**
     * 旷工总人数
     */
    private Integer absenteeismUsers;

    /**
     * 收入总金额
     */
    private Double totalIncome;

    /**
     * 借款总金额
     */
    private Double totalBorrow;

    /**
     * 借款总人数
     */
    private Integer borrowUsers;

    /**
     * 报销总金额
     */
    private Double claimingExpenses;

    /**
     * 报销总人数
     */
    private Integer claimingUsers;

    /**
     * 状态
     */
    private Status status;

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}