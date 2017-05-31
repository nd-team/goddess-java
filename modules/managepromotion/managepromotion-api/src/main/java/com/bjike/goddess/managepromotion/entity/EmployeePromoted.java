package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.managepromotion.enums.AuditStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 员工已晋升情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_employeepromoted")
public class EmployeePromoted extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 岗位
     */
    @Column(name = "jobs", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 渠道
     */
    @Column(name = "channel", columnDefinition = "VARCHAR(255)   COMMENT '渠道'")
    private String channel;

    /**
     * 时间
     */
    @Column(name = "times", columnDefinition = "DATE   COMMENT '时间'")
    private String times;

    /**
     * 晋升前（不包括各项补助）
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '晋升前（不包括各项补助）'")
    private Integer promotionBefore;

    /**
     * 晋升后（不包括各项补助）
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '晋升后（不包括各项补助）'")
    private Integer promotionAfter;

    /**
     * 幅度(晋升后-晋升前)
     */
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '幅度'")
    private Integer extent;

    /**
     * 总幅度
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '总幅度'")
    private Integer totalRange;

    /**
     * 状态（审核中/通过/不通过）
     */
    @Column( columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getPromotionBefore() {
        return promotionBefore;
    }

    public void setPromotionBefore(Integer promotionBefore) {
        this.promotionBefore = promotionBefore;
    }

    public Integer getPromotionAfter() {
        return promotionAfter;
    }

    public void setPromotionAfter(Integer promotionAfter) {
        this.promotionAfter = promotionAfter;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    public Integer getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(Integer totalRange) {
        this.totalRange = totalRange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}