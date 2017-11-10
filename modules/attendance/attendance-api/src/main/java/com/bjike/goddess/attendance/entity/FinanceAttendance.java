package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 财务出勤表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_financeattendance")
public class FinanceAttendance extends BaseEntity {

    /**
     * 区域
     */
    @Column(name = "depart", columnDefinition = "VARCHAR(255)   COMMENT '区域'")
    private String depart;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 周数
     */
    @Column(name = "week", columnDefinition = "VARCHAR(255)   COMMENT '周数'")
    private String week;

    /**
     * 申请核对原因
     */
    @Column(name = "reason", columnDefinition = "TEXT   COMMENT '申请核对原因'")
    private String reason;

    /**
     * 申请核对时间
     */
    @Column(name = "applyTime", columnDefinition = "DATE   COMMENT '申请核对时间'")
    private LocalDate applyTime;

    /**
     * 原请假天数
     */
    @Column(name = "vacateDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '请假天数'")
    private Double vacateDay;

    /**
     * 修改后的请假天数
     */
    @Column(name = "vacateDayNew", columnDefinition = "DECIMAL(10,2)   COMMENT '修改后的请假天数'")
    private Double vacateDayNew;

    /**
     * 原旷工天数
     */
    @Column(name = "absenteeismDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '旷工天数'")
    private Double absenteeismDay;

    /**
     * 修改后的旷工天数
     */
    @Column(name = "absenteeismDayNew", columnDefinition = "DECIMAL(10,2)   COMMENT '修改后的旷工天数'")
    private Double absenteeismDayNew;

    /**
     * 原任务完成天数
     */
    @Column(name = "finishDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '任务完成天数'")
    private Double finishDay;

    /**
     * 修改后的任务完成天数
     */
    @Column(name = "finishDayNew", columnDefinition = "DECIMAL(10,2)   COMMENT '修改后的任务完成天数'")
    private Double finishDayNew;

    /**
     * 原当天考勤天数
     */
    @Column(name = "attendanceDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '当天考勤天数'")
    private Double attendanceDay;

    /**
     * 修改后的当天考勤天数
     */
    @Column(name = "attendanceDayNew", columnDefinition = "DECIMAL(10,2)   COMMENT '修改后的当天考勤天数'")
    private Double attendanceDayNew;

    /**
     * 原实际出勤天数
     */
    @Column(name = "actualDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际出勤天数'")
    private Double actualDay;

    /**
     * 修改后的实际出勤天数
     */
    @Column(name = "actualDayNew", columnDefinition = "DECIMAL(10,2)   COMMENT '修改后的实际出勤天数'")
    private Double actualDayNew;

    /**
     * 负责人
     */
    @Column(name = "principal", columnDefinition = "VARCHAR(255)   COMMENT '负责人'")
    private String principal;

    /**
     * 负责人审核意见
     */
    @Column(name = "principalAduit", columnDefinition = "TINYINT(2)   COMMENT '负责人审核意见'")
    private AduitStatus principalAduit;

    /**
     * 负责人审核理由
     */
    @Column(name = "principalReason", columnDefinition = "TEXT   COMMENT '负责人审核理由'")
    private String principalReason;

    /**
     * 负责人审核时间
     */
    @Column(name = "principalTime", columnDefinition = "DATETIME   COMMENT '负责人审核时间'")
    private LocalDateTime principalTime;

    /**
     * 项目经理
     */
    @Column(name = "manager", columnDefinition = "VARCHAR(255)   COMMENT '项目经理'")
    private String manager;

    /**
     * 项目经理审核意见
     */
    @Column(name = "managerAduit", columnDefinition = "TINYINT(2)   COMMENT '项目经理审核意见'")
    private AduitStatus managerAduit;

    /**
     * 项目经理审核理由
     */
    @Column(name = "managerReason", columnDefinition = "TEXT   COMMENT '项目经理审核理由'")
    private String managerReason;

    /**
     * 项目经理审核时间
     */
    @Column(name = "managerTime", columnDefinition = "DATETIME   COMMENT '项目经理审核时间'")
    private LocalDateTime managerTime;

    /**
     * 最终审核意见
     */
    @Column(name = "aduitStatus", columnDefinition = "TINYINT(2)   COMMENT '最终审核意见'")
    private AduitStatus aduitStatus;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public LocalDateTime getPrincipalTime() {
        return principalTime;
    }

    public void setPrincipalTime(LocalDateTime principalTime) {
        this.principalTime = principalTime;
    }

    public LocalDateTime getManagerTime() {
        return managerTime;
    }

    public void setManagerTime(LocalDateTime managerTime) {
        this.managerTime = managerTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDate applyTime) {
        this.applyTime = applyTime;
    }

    public Double getVacateDayNew() {
        return vacateDayNew;
    }

    public void setVacateDayNew(Double vacateDayNew) {
        this.vacateDayNew = vacateDayNew;
    }

    public Double getAbsenteeismDayNew() {
        return absenteeismDayNew;
    }

    public void setAbsenteeismDayNew(Double absenteeismDayNew) {
        this.absenteeismDayNew = absenteeismDayNew;
    }

    public Double getFinishDayNew() {
        return finishDayNew;
    }

    public void setFinishDayNew(Double finishDayNew) {
        this.finishDayNew = finishDayNew;
    }

    public Double getAttendanceDayNew() {
        return attendanceDayNew;
    }

    public void setAttendanceDayNew(Double attendanceDayNew) {
        this.attendanceDayNew = attendanceDayNew;
    }

    public Double getActualDayNew() {
        return actualDayNew;
    }

    public void setActualDayNew(Double actualDayNew) {
        this.actualDayNew = actualDayNew;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public AduitStatus getPrincipalAduit() {
        return principalAduit;
    }

    public void setPrincipalAduit(AduitStatus principalAduit) {
        this.principalAduit = principalAduit;
    }

    public String getPrincipalReason() {
        return principalReason;
    }

    public void setPrincipalReason(String principalReason) {
        this.principalReason = principalReason;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public AduitStatus getManagerAduit() {
        return managerAduit;
    }

    public void setManagerAduit(AduitStatus managerAduit) {
        this.managerAduit = managerAduit;
    }

    public String getManagerReason() {
        return managerReason;
    }

    public void setManagerReason(String managerReason) {
        this.managerReason = managerReason;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Double getVacateDay() {
        return vacateDay;
    }

    public void setVacateDay(Double vacateDay) {
        this.vacateDay = vacateDay;
    }

    public Double getAbsenteeismDay() {
        return absenteeismDay;
    }

    public void setAbsenteeismDay(Double absenteeismDay) {
        this.absenteeismDay = absenteeismDay;
    }

    public Double getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(Double finishDay) {
        this.finishDay = finishDay;
    }

    public Double getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(Double attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public Double getActualDay() {
        return actualDay;
    }

    public void setActualDay(Double actualDay) {
        this.actualDay = actualDay;
    }
}