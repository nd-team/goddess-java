package com.bjike.goddess.projectcost.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 人工费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArtificialCostTO extends BaseTO {

    /**
     * 地区
     */
    private String area;


    /**
     * 项目组
     */
    private String project;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 目标人工时数
     */
    private Double targetHour;

    /**
     * 实际人工时数
     */
    private Double actualHour;

    /**
     * 单价
     */
    private Double univalent;

    /**
     * 预警
     */
    private Double alert;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getTargetHour() {
        return targetHour;
    }

    public void setTargetHour(Double targetHour) {
        this.targetHour = targetHour;
    }

    public Double getActualHour() {
        return actualHour;
    }

    public void setActualHour(Double actualHour) {
        this.actualHour = actualHour;
    }

    public Double getUnivalent() {
        return univalent;
    }

    public void setUnivalent(Double univalent) {
        this.univalent = univalent;
    }

    public Double getAlert() {
        return alert;
    }

    public void setAlert(Double alert) {
        this.alert = alert;
    }

}