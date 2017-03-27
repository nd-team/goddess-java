package com.bjike.goddess.market.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.market.enums.MarketCollectUnit;
import com.bjike.goddess.market.enums.MarketSendUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 市场邮件发送定制
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-22T19:08:18.875 ]
 * @Description: [ 市场邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "market_marketemail")
public class MarketEmail extends BaseEntity {

    /**
     * 行业
     */
    @Column(name = "work", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '行业'")
    private String work;

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
    @Column(columnDefinition = "TINYINT(1)  COMMENT '发送单位'", nullable = false)
    private MarketSendUnit marketSendUnit;

    /**
     * 汇总间隔
     */
    @Column(columnDefinition = "TINYINT(1)  COMMENT '汇总间隔'", nullable = false)
    private MarketCollectUnit marketCollectUnit;

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
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '状态'", nullable = false, insertable = false)
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

    public MarketSendUnit getMarketSendUnit() {
        return marketSendUnit;
    }

    public void setMarketSendUnit(MarketSendUnit customerSendUnit) {
        this.marketSendUnit = marketSendUnit;
    }

    public MarketCollectUnit getMarketCollectUnit() {
        return marketCollectUnit;
    }

    public void setMarketCollectUnit(MarketCollectUnit marketCollectUnit) {
        this.marketCollectUnit = marketCollectUnit;
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