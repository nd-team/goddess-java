package com.bjike.goddess.materialsummary.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialsummary.type.CollectSendUnit;
import com.bjike.goddess.materialsummary.type.CollectUnit;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 物质购买发送邮件
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialsummary_sendemail")
public class SendEmail extends BaseEntity {

    /**
     * 汇总模块
     */
    @Column(name = "moduleType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总模块'")
    private ModuleType moduleType;

    /**
     * 汇总类型
     */
    @Column(name = "summaryType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总类型'")
    private SummaryType summaryType;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 发送间隔
     */
    @Column(name = "sendNum", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '发送间隔'")
    private Double sendNum;

    /**
     * 发送间隔和单位
     */
    @Column(name = "sendNumAndUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送间隔和单位'")
    private String sendNumAndUnit;

    /**
     * 发送单位
     */
    @Column(name = "collectSendUnit", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '发送单位'")
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    @Column(name = "collectUnit", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总间隔'")
    private CollectUnit collectUnit;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "MEDIUMTEXT   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 上次发送时间
     */
    @Column(name = "lastSendTime", nullable = false, columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastSendTime;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 创建人
     */
    @Column(name = "createPersion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createPersion;


    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public SummaryType getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(SummaryType summaryType) {
        this.summaryType = summaryType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSendNum() {
        return sendNum;
    }

    public void setSendNum(Double sendNum) {
        this.sendNum = sendNum;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public LocalDateTime getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(LocalDateTime lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }
}