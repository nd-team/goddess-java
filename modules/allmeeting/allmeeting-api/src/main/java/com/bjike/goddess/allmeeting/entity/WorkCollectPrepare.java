package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 工作汇总议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_workcollectprepare")
public class WorkCollectPrepare extends BaseEntity {

    /**
     * 会议编号
     */
    @Column(name = "meetingNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会议编号'")
    private String meetingNum;

    /**
     * 汇总开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '汇总开始时间'")
    private LocalDateTime startTime;

    /**
     * 汇总结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '汇总结束时间'")
    private LocalDateTime endTime;

    /**
     * 模块
     */
    @Column(name = "module", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '模块'")
    private String module;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 计划任务量
     */
    @Column(name = "planTasks", nullable = false, columnDefinition = "INT(11)   COMMENT '计划任务量'")
    private Integer planTasks;

    /**
     * 已完成任务数量
     */
    @Column(name = "finishTasks", nullable = false, columnDefinition = "INT(11)   COMMENT '已完成任务数量'")
    private Integer finishTasks;

    /**
     * 未完成任务数量
     */
    @Column(name = "unFinishTasks", nullable = false, columnDefinition = "INT(11)   COMMENT '未完成任务数量'")
    private Integer unFinishTasks;

    /**
     * 有交接任务数量
     */
    @Column(name = "handoverTasks", nullable = false, columnDefinition = "INT(11)   COMMENT '有交接任务数量'")
    private Integer handoverTasks;

    /**
     * 未交接任务数量
     */
    @Column(name = "unhandoverTasks", nullable = false, columnDefinition = "INT(11)   COMMENT '未交接任务数量'")
    private Integer unhandoverTasks;

    /**
     * 问题发现数量
     */
    @Column(name = "problems", nullable = false, columnDefinition = "INT(11)   COMMENT '问题发现数量'")
    private Integer problems;

    /**
     * 总人数
     */
    @Column(name = "totalUsers", nullable = false, columnDefinition = "INT(11)   COMMENT '总人数'")
    private Integer totalUsers;

    /**
     * 迟到总人数
     */
    @Column(name = "lateUsers", nullable = false, columnDefinition = "INT(11)   COMMENT '迟到总人数'")
    private Integer lateUsers;

    /**
     * 早退总人数
     */
    @Column(name = "leaveEarlyUsers", nullable = false, columnDefinition = "INT(11)   COMMENT '早退总人数'")
    private Integer leaveEarlyUsers;

    /**
     * 请假总人数
     */
    @Column(name = "vacationUsers", nullable = false, columnDefinition = "INT(11)  COMMENT '请假总人数'")
    private Integer vacationUsers;

    /**
     * 旷工总人数
     */
    @Column(name = "absenteeismUsers", nullable = false, columnDefinition = "INT(11)  COMMENT '旷工总人数'")
    private Integer absenteeismUsers;

    /**
     * 收入总金额
     */
    @Column(name = "totalIncome", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '收入总金额'")
    private Double totalIncome;

    /**
     * 借款总金额
     */
    @Column(name = "totalBorrow", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '借款总金额'")
    private Double totalBorrow;

    /**
     * 借款总人数
     */
    @Column(name = "borrowUsers", nullable = false, columnDefinition = "INT(11)   COMMENT '借款总人数'")
    private Integer borrowUsers;

    /**
     * 报销总金额
     */
    @Column(name = "claimingExpenses", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '报销总金额'")
    private Double claimingExpenses;

    /**
     * 报销总人数
     */
    @Column(name = "claimingUsers", nullable = false, columnDefinition = "INT(11)   COMMENT '报销总人数'")
    private Integer claimingUsers;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public String getMeetingNum() {
        return meetingNum;
    }

    public void setMeetingNum(String meetingNum) {
        this.meetingNum = meetingNum;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}