package com.bjike.goddess.competitormanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.CollectIntervalType;
import com.bjike.goddess.competitormanage.enums.SendIntervalType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 竞争对手汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-23 11:27 ]
 * @Description: [ 竞争对手汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "competitormanage_competitorcollect")
public class CompetitorCollect extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 创建人/修改人
     */
    @Column(name = "operateUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人/修改人'")
    private String operateUser;

    /**
     * 上次发送时间
     */
    @Column(name = "lastSendTime", columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastSendTime;

    /**
     * 汇总间隔
     */
    @Column(name = "collectInterval", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总间隔'")
    private CollectIntervalType collectInterval;

    /**
     * 发送间隔类型
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送间隔类型'")
    private SendIntervalType sendIntervalType;

    /**
     * 发送间隔
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送间隔'")
    private Integer sendInterval;

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

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public CollectIntervalType getCollectInterval() {
        return collectInterval;
    }

    public void setCollectInterval(CollectIntervalType collectInterval) {
        this.collectInterval = collectInterval;
    }

    public SendIntervalType getSendIntervalType() {
        return sendIntervalType;
    }

    public void setSendIntervalType(SendIntervalType sendIntervalType) {
        this.sendIntervalType = sendIntervalType;
    }

    public Integer getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(Integer sendInterval) {
        this.sendInterval = sendInterval;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}