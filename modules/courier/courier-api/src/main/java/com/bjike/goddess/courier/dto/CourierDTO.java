package com.bjike.goddess.courier.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 快递收发数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourierDTO extends BaseDTO {
    public interface DAY {
    }

    public interface WEEK {
    }

    public interface MONTH {
    }

    public interface YEAR {
    }

    /**
     * 寄件时间
     */
    @NotBlank(groups = CourierDTO.DAY.class, message = "寄件时间不能为空")
    private String sendTime;

    /**
     * 年份
     */
    @NotNull(groups = {CourierDTO.WEEK.class, CourierDTO.MONTH.class, CourierDTO.YEAR.class}, message = "年份不能为空")
    private Integer year;

    /**
     * 月份
     */
    @NotNull(groups = {CourierDTO.WEEK.class, CourierDTO.MONTH.class}, message = "月份不能为空")
    private Integer month;

    /**
     * 周数
     */
    @NotNull(groups = {CourierDTO.WEEK.class}, message = "周数不能为空")
    private Integer week;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}