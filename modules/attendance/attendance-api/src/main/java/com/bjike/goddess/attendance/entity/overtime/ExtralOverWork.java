package com.bjike.goddess.attendance.entity.overtime;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 补班设置（针对全体员工）
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-12 04:42 ]
 * @Description: [ 补班设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_extraloverwork")
public class ExtralOverWork extends BaseEntity {

    /**
     * 补班类型
     */
    @Column(name = "overType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补班类型'")
    private String overType;

    /**
     * 补班开始时间
     */
    @Column(name = "overStartTime", nullable = false, columnDefinition = "DATETIME   COMMENT '补班开始时间'")
    private LocalDateTime overStartTime;

    /**
     * 补班结束时间
     */
    @Column(name = "overEndTime", nullable = false, columnDefinition = "DATETIME   COMMENT '补班结束时间'")
    private LocalDateTime overEndTime;

    /**
     * 补班天数
     */
    @Column(name = "overDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补班天数'")
    private Double overDay;

    /**
     * 创建人
     */
    @Column(name = "creator", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creator;

    /**
     * 是否午休
     */
    @Column(name = "lunchBreak", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否午休'")
    private Boolean lunchBreak;

    public Boolean getLunchBreak() {
        return lunchBreak;
    }

    public void setLunchBreak(Boolean lunchBreak) {
        this.lunchBreak = lunchBreak;
    }

    public String getOverType() {
        return overType;
    }

    public void setOverType(String overType) {
        this.overType = overType;
    }

    public LocalDateTime getOverStartTime() {
        return overStartTime;
    }

    public void setOverStartTime(LocalDateTime overStartTime) {
        this.overStartTime = overStartTime;
    }

    public LocalDateTime getOverEndTime() {
        return overEndTime;
    }

    public void setOverEndTime(LocalDateTime overEndTime) {
        this.overEndTime = overEndTime;
    }

    public Double getOverDay() {
        return overDay;
    }

    public void setOverDay(Double overDay) {
        this.overDay = overDay;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}