package com.bjike.goddess.outcarfare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资金准备审核
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "outcarfare_moneyready")
public class MoneyReady extends BaseEntity {

    /**
     * 类别
     */
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String category;

    /**
     * 科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subject;

    /**
     * 总准备金
     */
    @Column(name = "totalReserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总准备金'")
    private Double totalReserve;

    /**
     * 项目组
     */
    @Column(name = "groupTeam", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String groupTeam;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 比例分配
     */
    @Column(name = "prorate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例分配'")
    private Double prorate;

    /**
     * 准备金
     */
    @Column(name = "reserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double reserve;

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 是否删除
     */
    @Column(name = "is_del", columnDefinition = "TINYINT(1) COMMENT '是否删除'")
    private Boolean isDel;

    /**
     * 删除时间
     */
    @Column(name = "delTime",columnDefinition = "DATE   COMMENT '删除时间'")
    private LocalDate delTime;

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean del) {
        isDel = del;
    }

    public LocalDate getDelTime() {
        return delTime;
    }

    public void setDelTime(LocalDate delTime) {
        this.delTime = delTime;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(String groupTeam) {
        this.groupTeam = groupTeam;
    }

    public Double getProrate() {
        return prorate;
    }

    public void setProrate(Double prorate) {
        this.prorate = prorate;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
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
}