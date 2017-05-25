package com.bjike.goddess.regionalprogresscollect.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 参考指标业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReferenceTargetBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 类别
     */
    private String type;

    /**
     * 节点
     */
    private String node;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 计划每月任务量
     */
    private Double monthTask;

    /**
     * 计划每月人工
     */
    private Double monthArtificial;

    /**
     * 计划每周任务量
     */
    private Double weekTask;

    /**
     * 计划每周人工
     */
    private Double weekArtificial;

    /**
     * 计划每天任务量
     */
    private Double dayTask;

    /**
     * 计划每天人工
     */
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