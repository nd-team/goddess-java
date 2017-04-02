package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.businessevaluate.enums.CollectIntervalType;
import com.bjike.goddess.businessevaluate.enums.SendIntervalType;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:12 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_businessevaluatecollect")
public class BusinessEvaluateCollect extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 创建人/修改人
     */
    @Column(name = "operateUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人/修改人'")
    private String operateUser;

    /**
     * 上次发送时间
     */
    @Column(name = "lastSendTime", nullable = false, columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastSendTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 发送间隔类型
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送间隔类型'")
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    @Column(name = "sendInterval", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送间隔'")
    private String sendInterval;

    /**
     * 汇总间隔
     */
    @Column(name = "collectInterval", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总间隔'")
    private CollectIntervalType collectInterval;

    /**
     * 发送对象
     */
    @Column(name = "sendUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendUser;

    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;


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

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public LocalDateTime getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(LocalDateTime lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SendIntervalType getSendIntervalType() {
        return sendIntervalType;
    }

    public void setSendIntervalType(SendIntervalType sendIntervalType) {
        this.sendIntervalType = sendIntervalType;
    }

    public String getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(String sendInterval) {
        this.sendInterval = sendInterval;
    }

    public CollectIntervalType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(CollectIntervalType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}