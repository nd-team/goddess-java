package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.OverTimesType;
import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 加班次数计算数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班次数计算数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverTimesDTO extends BaseDTO {
    public interface COUNT{}
    /**
     * 加班人员
     */
    private String overWorker;

    /**
     * 时间汇总类型
     */
    private OverTimesType overTimesType;


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
     * 季度
     */
    private int quart;


    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }

    public OverTimesType getOverTimesType() {
        return overTimesType;
    }

    public void setOverTimesType(OverTimesType overTimesType) {
        this.overTimesType = overTimesType;
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

    public int getQuart() {
        return quart;
    }

    public void setQuart(int quart) {
        this.quart = quart;
    }
}