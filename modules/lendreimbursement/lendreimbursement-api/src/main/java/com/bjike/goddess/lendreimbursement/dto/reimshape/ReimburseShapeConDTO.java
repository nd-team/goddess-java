package com.bjike.goddess.lendreimbursement.dto.reimshape;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimburseShapeStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 报销图形数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 报销图形数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReimburseShapeConDTO extends BaseDTO {


    /**
     * 汇总状态
     */
    private ReimburseShapeStatus reimburseShapeStatus;
    /**
     * 年
     */
    private int year;

    /**
     * 月
     */
    private int month;

    /**
     * 周
     */
    private int week;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 日汇总的时间
     */
    private String time;

    /**
     * 结束时间
     */
    private LocalDate startTime;

    /**
     * 结束时间
     */
    private LocalDate endTime;

    public ReimburseShapeStatus getReimburseShapeStatus() {
        return reimburseShapeStatus;
    }

    public void setReimburseShapeStatus(ReimburseShapeStatus reimburseShapeStatus) {
        this.reimburseShapeStatus = reimburseShapeStatus;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}