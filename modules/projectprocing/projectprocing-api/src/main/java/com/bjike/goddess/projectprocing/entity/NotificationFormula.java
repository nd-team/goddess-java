package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.projectprocing.enums.SendFrequency;
import com.bjike.goddess.projectprocing.enums.SummTableName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 通报机制制定
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_notificationformula")
public class NotificationFormula extends BaseEntity {

    /**
     * 汇总表名
     */
    @Column(name = "summTableName", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总表名'")
    private SummTableName summTableName;

    /**
     * 创建/修改人
     */
    @Column(name = "modifier", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建/修改人'")
    private String modifier;

    /**
     * 发送频率
     */
    @Column(name = "sendFrequency", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '发送频率'")
    private SendFrequency sendFrequency;

    /**
     * 发送时间节点
     */
    @Column(name = "sendTimeNode", nullable = false, columnDefinition = "DATETIME   COMMENT '发送时间节点'")
    private LocalDateTime sendTimeNode;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 是否发送项目组/部门全部人
     */
    @Column(name = "is_sendSectoral", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否发送项目组/部门全部人'", insertable = false)
    private Boolean sendSectoral;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public SummTableName getSummTableName() {
        return summTableName;
    }

    public void setSummTableName(SummTableName summTableName) {
        this.summTableName = summTableName;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public SendFrequency getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(SendFrequency sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public LocalDateTime getSendTimeNode() {
        return sendTimeNode;
    }

    public void setSendTimeNode(LocalDateTime sendTimeNode) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}