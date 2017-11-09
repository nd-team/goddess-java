package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.attendance.enums.AduitStatus;


/**
 * 财务出勤表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceAttendanceVO {
    /**
     * id
     */
    private String id;

    /**
     * 区域
     */
    private String depart;

    /**
     * 姓名
     */
    private String name;

    /**
     * 时间
     */
    private String time;
    /**
     * 周数
     */
    private String week;

    /**
     * 申请核对原因
     */
    private String reason;

    /**
     * 申请核对时间
     */
    private String applyTime;

    /**
     * 原请假天数
     */
    private Double vacateDay;

    /**
     * 修改后的请假天数
     */
    private Double vacateDayNew;

    /**
     * 原旷工天数
     */
    private Double absenteeismDay;

    /**
     * 修改后的旷工天数
     */
    private Double absenteeismDayNew;

    /**
     * 原任务完成天数
     */
    private Double finishDay;

    /**
     * 修改后的任务完成天数
     */
    private Double finishDayNew;

    /**
     * 原当天考勤天数
     */
    private Double attendanceDay;

    /**
     * 修改后的当天考勤天数
     */
    private Double attendanceDayNew;

    /**
     * 原实际出勤天数
     */
    private Double actualDay;

    /**
     * 修改后的实际出勤天数
     */
    private Double actualDayNew;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 负责人审核意见
     */
    private AduitStatus principalAduit;

    /**
     * 负责人审核理由
     */
    private String principalReason;

    /**
     * 负责人审核时间
     */
    private String principalTime;

    /**
     * 项目经理
     */
    private String manager;

    /**
     * 项目经理审核意见
     */
    private AduitStatus managerAduit;

    /**
     * 项目经理审核理由
     */
    private String managerReason;

    /**
     * 项目经理审核时间
     */
    private String managerTime;

    /**
     * 最终审核意见
     */
    private AduitStatus aduitStatus;

    /**
     * 背景是否为红色
     */
    private Boolean red;

    /**
     * 背景是否为绿色
     */
    private Boolean green;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
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

    public String getPrincipalTime() {
        return principalTime;
    }

    public void setPrincipalTime(String principalTime) {
        this.principalTime = principalTime;
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

    public String getManagerTime() {
        return managerTime;
    }

    public void setManagerTime(String managerTime) {
        this.managerTime = managerTime;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public Boolean getGreen() {
        return green;
    }

    public void setGreen(Boolean green) {
        this.green = green;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean red) {
        this.red = red;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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