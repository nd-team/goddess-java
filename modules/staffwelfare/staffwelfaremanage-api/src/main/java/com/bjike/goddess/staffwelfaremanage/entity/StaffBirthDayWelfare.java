package com.bjike.goddess.staffwelfaremanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 员工生日福利记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfaremanage_staffbirthdaywelfare")
public class StaffBirthDayWelfare extends BaseEntity {

    /**
     * 生日年月
     */
    @Column(name = "birthday", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '生日年月'")
    private String birthday;

    /**
     * 员工姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工姓名'")
    private String name;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 发放时间
     */
    @Column(name = "sendTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发放时间'")
    private String sendTime;

    /**
     * 发放内容
     */
    @Column(name = "sendDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发放内容'")
    private String sendDetail;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 员工Id
     */
    @Column(name = "userId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工Id'")
    private String userId;


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendDetail() {
        return sendDetail;
    }

    public void setSendDetail(String sendDetail) {
        this.sendDetail = sendDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}