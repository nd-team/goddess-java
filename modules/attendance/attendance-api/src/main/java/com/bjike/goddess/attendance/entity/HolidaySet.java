package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.HolidayType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 假期设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-20 11:54 ]
 * @Description: [ 假期设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_holidayset")
public class HolidaySet extends BaseEntity {

    /**
     * 假期名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '假期名称'")
    private String name;

    /**
     * 假期天数
     */
    @Column(name = "day", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '假期天数'")
    private Double day;

    /**
     * 假期类型
     */
    @Column(name = "holidayType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '假期类型'")
    private HolidayType holidayType;

    /**
     * 假期开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATE   COMMENT '假期开始时间'")
    private LocalDate startTime;

    /**
     * 假期结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '假期结束时间'")
    private LocalDate endTime;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public HolidayType getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayType holidayType) {
        this.holidayType = holidayType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}