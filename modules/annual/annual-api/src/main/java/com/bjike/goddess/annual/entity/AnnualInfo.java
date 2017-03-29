package com.bjike.goddess.annual.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 年假信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "annual_annual_info")
public class AnnualInfo extends BaseEntity {

    /**
     * 年度
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年度'")
    private Integer year;

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位层级
     */
    @Column(name = "arrangement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String arrangement;

    /**
     * 入职时间
     */
    @Column(name = "entryTime", nullable = false, columnDefinition = "DATE   COMMENT '入职时间'")
    private LocalDate entryTime;

    /**
     * 工龄
     */
    @Column(name = "seniority", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工龄'")
    private String seniority;

    /**
     * 可休年假天数
     */
    @Column(name = "annual", nullable = false, columnDefinition = "INT(11)   COMMENT '可休年假天数'")
    private Integer annual;

    /**
     * 剩余年假天数
     */
    @Column(name = "surplus", nullable = false, columnDefinition = "DECIMAL(10,2)  COMMENT '剩余年假天数'")
    private Double surplus;

    /**
     * 是否已休假
     */
    @Column(name = "is_already", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已休假'", insertable = false)
    private Boolean already;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public Integer getAnnual() {
        return annual;
    }

    public void setAnnual(Integer annual) {
        this.annual = annual;
    }

    public Double getSurplus() {
        return surplus;
    }

    public void setSurplus(Double surplus) {
        this.surplus = surplus;
    }

    public Boolean isAlready() {
        return already;
    }

    public void isAlready(Boolean already) {
        this.already = already;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}