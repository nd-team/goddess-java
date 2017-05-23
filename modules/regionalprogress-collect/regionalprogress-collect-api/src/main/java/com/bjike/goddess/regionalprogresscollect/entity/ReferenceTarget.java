package com.bjike.goddess.regionalprogresscollect.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 参考指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regionalprogresscollect_reference_target")
public class ReferenceTarget extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类别'")
    private String type;

    /**
     * 节点
     */
    @Column(name = "node", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节点'")
    private String node;

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
     * 计划每月任务量
     */
    @Column(name = "monthTask", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每月任务量'")
    private Double monthTask;

    /**
     * 计划每月人工
     */
    @Column(name = "monthArtificial", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每月人工'")
    private Double monthArtificial;

    /**
     * 计划每周任务量
     */
    @Column(name = "weekTask", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每周任务量'")
    private Double weekTask;

    /**
     * 计划每周人工
     */
    @Column(name = "weekArtificial", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每周人工'")
    private Double weekArtificial;

    /**
     * 计划每天任务量
     */
    @Column(name = "dayTask", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每天任务量'")
    private Double dayTask;

    /**
     * 计划每天人工
     */
    @Column(name = "dayArtificial", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划每天人工'")
    private Double dayArtificial;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
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

    public Double getMonthTask() {
        return monthTask;
    }

    public void setMonthTask(Double monthTask) {
        this.monthTask = monthTask;
    }

    public Double getMonthArtificial() {
        return monthArtificial;
    }

    public void setMonthArtificial(Double monthArtificial) {
        this.monthArtificial = monthArtificial;
    }

    public Double getWeekTask() {
        return weekTask;
    }

    public void setWeekTask(Double weekTask) {
        this.weekTask = weekTask;
    }

    public Double getWeekArtificial() {
        return weekArtificial;
    }

    public void setWeekArtificial(Double weekArtificial) {
        this.weekArtificial = weekArtificial;
    }

    public Double getDayTask() {
        return dayTask;
    }

    public void setDayTask(Double dayTask) {
        this.dayTask = dayTask;
    }

    public Double getDayArtificial() {
        return dayArtificial;
    }

    public void setDayArtificial(Double dayArtificial) {
        this.dayArtificial = dayArtificial;
    }
}