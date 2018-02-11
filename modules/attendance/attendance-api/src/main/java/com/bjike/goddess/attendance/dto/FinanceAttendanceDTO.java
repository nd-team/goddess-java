package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 财务出勤表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FinanceAttendanceDTO extends BaseDTO {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 姓名
     */
    private String[] names;

    private String id;
    private String name;
    private String time;
    private String week;

    private Double absenteeismDay;
    private Double actualDay;
    private Double finishDay;
    private Double vacateDay;
    private Double attendanceDay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Double getAbsenteeismDay() {
        return absenteeismDay;
    }

    public void setAbsenteeismDay(Double absenteeismDay) {
        this.absenteeismDay = absenteeismDay;
    }

    public Double getActualDay() {
        return actualDay;
    }

    public void setActualDay(Double actualDay) {
        this.actualDay = actualDay;
    }

    public Double getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(Double finishDay) {
        this.finishDay = finishDay;
    }

    public Double getVacateDay() {
        return vacateDay;
    }

    public void setVacateDay(Double vacateDay) {
        this.vacateDay = vacateDay;
    }

    public Double getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(Double attendanceDay) {
        this.attendanceDay = attendanceDay;
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

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}