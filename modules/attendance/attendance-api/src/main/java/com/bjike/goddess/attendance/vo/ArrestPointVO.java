package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.attendance.enums.Status;

/**
 * 驻点设置表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArrestPointVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 驻点地点
     */
    private String pointArea;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 考勤位置
     */
    private String location;

    /**
     * 有效范围（米）
     */
    private Integer range;

    /**
     * 打卡工作日
     */
    private String workDay;

    /**
     * 创建人
     */
    private String name;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPointArea() {
        return pointArea;
    }

    public void setPointArea(String pointArea) {
        this.pointArea = pointArea;
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

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}