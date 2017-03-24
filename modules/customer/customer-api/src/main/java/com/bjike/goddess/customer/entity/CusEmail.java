package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.CustomerCollectUnit;
import com.bjike.goddess.customer.enums.CustomerSendUnit;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 客户邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.875 ]
 * @Description: [ 客户邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_cusemail")
public class CusEmail extends BaseEntity {

    /**
     * 行业
     */
    @Column(name = "work", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '行业'")
    private String work;

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
    @Column(columnDefinition = "TINYINT(1)  COMMENT '发送单位'", nullable = false )
    private CustomerSendUnit customerSendUnit;

    /**
     * 汇总间隔
     */
    @Column(columnDefinition = "TINYINT(1)  COMMENT '汇总间隔'", nullable = false )
    private CustomerCollectUnit customerCollectUnit;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 上次发送时间
     */
    @Column(name = "lastSendTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '上次发送时间'")
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


    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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

    public CustomerSendUnit getCustomerSendUnit() {
        return customerSendUnit;
    }

    public void setCustomerSendUnit(CustomerSendUnit customerSendUnit) {
        this.customerSendUnit = customerSendUnit;
    }

    public CustomerCollectUnit getCustomerCollectUnit() {
        return customerCollectUnit;
    }

    public void setCustomerCollectUnit(CustomerCollectUnit customerCollectUnit) {
        this.customerCollectUnit = customerCollectUnit;
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