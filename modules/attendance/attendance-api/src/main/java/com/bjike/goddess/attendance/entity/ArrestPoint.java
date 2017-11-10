package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 驻点设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:12 ]
 * @Description: [ 驻点设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_arrestpoint")
public class ArrestPoint extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 驻点地点
     */
    @Column(name = "pointArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '驻点地点'")
    private String pointArea;

    /**
     * 经度
     */
    @Column(name = "longitude", nullable = false, columnDefinition = "DECIMAL(10,5)   COMMENT '经度'")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude", nullable = false, columnDefinition = "DECIMAL(10,5)   COMMENT '纬度'")
    private Double latitude;

    /**
     * 考勤位置
     */
    @Column(name = "location", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '考勤位置'")
    private String location;

    /**
     * 有效范围（米）
     */
    @Column(name = "scope", nullable = false, columnDefinition = "INT(11)   COMMENT '有效范围（米）'")
    private Integer range;

    /**
     * 打卡工作日
     */
    @Column(name = "workDay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '打卡工作日'")
    private String workDay;

    /**
     * 创建人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String name;
    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '状态'")
    private Status status;

    public String getPointArea() {
        return pointArea;
    }

    public void setPointArea(String pointArea) {
        this.pointArea = pointArea;
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
}