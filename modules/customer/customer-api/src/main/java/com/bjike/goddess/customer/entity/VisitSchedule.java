package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 拜访日程表
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_visitschedule")
public class VisitSchedule extends BaseEntity {

    /**
     * 推荐时间
     */
    @Column(name = "recommendDate", nullable = false,columnDefinition = "DATETIME   COMMENT '推荐时间'")
    private LocalDateTime recommendDate;

    /**
     * 年度
     */
    @Column(name = "year", nullable = false, columnDefinition = "TINYINT(11)   COMMENT '年度'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '月份'")
    private Integer month;

    /**
     * 周期
     */
    @Column(name = "week", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '周期'")
    private Integer week;

    /**
     * 周一
     */
    @Column(name = "monday",  columnDefinition = "VARCHAR(255)   COMMENT '周一'")
    private String monday;

    /**
     * 周二
     */
    @Column(name = "tuesday",  columnDefinition = "VARCHAR(255)   COMMENT '周二'")
    private String tuesday;

    /**
     * 周三
     */
    @Column(name = "wednesday",  columnDefinition = "VARCHAR(255)   COMMENT '周三'")
    private String wednesday;

    /**
     * 周四
     */
    @Column(name = "thursday",  columnDefinition = "VARCHAR(255)   COMMENT '周四'")
    private String thursday;

    /**
     * 周五
     */
    @Column(name = "fridays", columnDefinition = "VARCHAR(255)   COMMENT '周五'")
    private String fridays;

    /**
     * 周六
     */
    @Column(name = "saturday",  columnDefinition = "VARCHAR(255)   COMMENT '周六'")
    private String saturday;


    public LocalDateTime getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(LocalDateTime recommendDate) {
        this.recommendDate = recommendDate;
    }

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

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFridays() {
        return fridays;
    }

    public void setFridays(String fridays) {
        this.fridays = fridays;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }
}