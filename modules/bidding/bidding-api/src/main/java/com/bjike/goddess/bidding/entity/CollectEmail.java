package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.bidding.enums.CollectSendUnit;
import com.bjike.goddess.bidding.enums.CollectUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 招投标信息邮件发送定制
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.875 ]
 * @Description: [ 招投标信息邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_collectemail")
public class CollectEmail extends BaseEntity {

    /**
     * 汇总类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总类型'")
    private String type;


    /**
     * 汇总条件
     */
    @Column(name = "condi", nullable = false, columnDefinition = "TEXT   COMMENT '汇总条件'")
    private String condi;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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
    @Column(columnDefinition = "TINYINT(2)  COMMENT '发送单位'", nullable = false )
    private CollectSendUnit collectSendUnit;

    /**
     * 汇总间隔
     */
    @Column(columnDefinition = "TINYINT(2)  COMMENT '汇总间隔'" )
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
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 创建人
     */
    @Column(name = "createPersion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createPersion;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondi() {
        return condi;
    }

    public void setCondi(String condi) {
        this.condi = condi;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

}