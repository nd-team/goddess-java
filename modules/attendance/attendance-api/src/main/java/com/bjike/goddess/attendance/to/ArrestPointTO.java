package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.enums.Week;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 驻点设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrestPointTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "地区不能为空")
    private String area;
    /**
     * 驻点地点
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "驻点地点不能为空")
    private String pointArea;

    /**
     * 经度
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "纬度不能为空")
    private Double latitude;

    /**
     * 考勤位置
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "考勤位置不能为空")
    private String location;

    /**
     * 有效范围（米）
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "有效范围（米）不能为空")
    private Integer range;

    /**
     * 打卡工作日
     */
    private Week[] workDays;

    /**
     * 是否启用大小周
     */
    private Boolean week;
    /**
     * 状态
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "状态不能为空")
    private Status status;

    public String getPointArea() {
        return pointArea;
    }

    public void setPointArea(String pointArea) {
        this.pointArea = pointArea;
    }

    public Boolean getWeek() {
        return week;
    }

    public void setWeek(Boolean week) {
        this.week = week;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Week[] getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Week[] workDays) {
        this.workDays = workDays;
    }

}