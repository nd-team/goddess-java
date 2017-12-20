package com.bjike.goddess.businessprojectmanage.entity;

import com.bjike.goddess.businessprojectmanage.enums.CollectSendUnit;
import com.bjike.goddess.businessprojectmanage.enums.CollectUnit;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 通报机制制定
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessprojectmanage_notificationformula")
public class NotificationFormula extends BaseEntity {

    /**
     * 汇总类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(255)   COMMENT '汇总类型'")
    private String type;

    /**
     * 创建人
     */
    @Column(name = "creator", columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creator;

    /**
     * 修改人
     */
    @Column(name = "modifier", columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String modifier;

    /**
     * 发送间隔
     */
    @Column(name = "sendFrequency", columnDefinition = "DECIMAL(10,2)   COMMENT '发送间隔'")
    private Double sendFrequency;

    /**
     * 发送时间节点
     */
    @Column(name = "sendTimeNode", columnDefinition = "DATE   COMMENT '发送时间节点'")
    private LocalDate sendTimeNode;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 是否发送项目组/部门全部人
     */
    @Column(name = "is_sendSectoral", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否发送项目组/部门全部人'", insertable = false)
    private Boolean sendSectoral;

    /**
     * 汇总条件
     */
    @Column(name = "conditions", columnDefinition = "VARCHAR(255)   COMMENT '汇总条件'")
    private String conditions;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 发送间隔和单位
     */
    @Column(name = "sendNumAndUnit", columnDefinition = "VARCHAR(255)   COMMENT '发送间隔和单位'")
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    @Column(name = "collectSendUnit", columnDefinition = "VARCHAR(255)   COMMENT '发送单位'")
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    @Column(name = "collectUnit", columnDefinition = "VARCHAR(255)   COMMENT '汇总间隔'")
    private CollectUnit collectUnit;

    /**
     * 上次发送时间
     */
    @Column(name = "lastSendTime", columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastSendTime;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private Status status;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Double getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(Double sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public LocalDate getSendTimeNode() {
        return sendTimeNode;
    }

    public void setSendTimeNode(LocalDate sendTimeNode) {
        this.sendTimeNode = sendTimeNode;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public Boolean getSendSectoral() {
        return sendSectoral;
    }

    public void setSendSectoral(Boolean sendSectoral) {
        this.sendSectoral = sendSectoral;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendNumAndUnit() {
        return sendNumAndUnit;
    }

    public void setSendNumAndUnit(String sendNumAndUnit) {
        this.sendNumAndUnit = sendNumAndUnit;
    }

    public CollectSendUnit getCollectSendUnit() {
        return collectSendUnit;
    }

    public void setCollectSendUnit(CollectSendUnit collectSendUnit) {
        this.collectSendUnit = collectSendUnit;
    }

    public CollectUnit getCollectUnit() {
        return collectUnit;
    }

    public void setCollectUnit(CollectUnit collectUnit) {
        this.collectUnit = collectUnit;
    }

    public LocalDateTime getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(LocalDateTime lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}