package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.RecommInfoUpdateFreq;
import com.bjike.goddess.customer.enums.ReminderVisit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 拜访推荐设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_visitrecommset")
public class VisitRecommSet extends BaseEntity {

    /**
     * 创建/修改人
     */
    @Column(name = "createPersion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建/修改人'")
    private String createPersion;

    /**
     * 更新时间
     */
    @Column(name = "updateDate", nullable = false, columnDefinition = "DATETIME   COMMENT '更新时间'")
    private LocalDateTime updateDate;

    /**
     * 推荐更新信息时间
     */
    @Column(name = "RecommInfoUpdateTime", nullable = false, columnDefinition = "DATETIME   COMMENT '推荐更新信息时间'")
    private LocalDateTime RecommInfoUpdateTime;
    /**
     * 推荐信息更新频率
     */
    @Column(name = "recommInfoUpdateFreq", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '推荐信息更新频率'")
    private RecommInfoUpdateFreq recommInfoUpdateFreq;


    /**
     * 推荐间隔
     */
    @Column(name = "recommendInterval", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '推荐间隔'")
    private RecommInfoUpdateFreq recommendInterval;
    /**
     * 推荐时间
     */
    @Column(name = "recommendDate", nullable = false, columnDefinition = "DATETIME   COMMENT '推荐时间'")
    private LocalDateTime recommendDate;

    /**
     * 推荐提醒时间
     */
    @Column(name = "reminderVisit", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '提醒拜访时间'")
    private ReminderVisit reminderVisit;

    /**
     * 发送对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '发送对象'")
    private String sendObject;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getRecommInfoUpdateTime() {
        return RecommInfoUpdateTime;
    }

    public void setRecommInfoUpdateTime(LocalDateTime recommInfoUpdateTime) {
        RecommInfoUpdateTime = recommInfoUpdateTime;
    }

    public RecommInfoUpdateFreq getRecommInfoUpdateFreq() {
        return recommInfoUpdateFreq;
    }

    public void setRecommInfoUpdateFreq(RecommInfoUpdateFreq recommInfoUpdateFreq) {
        this.recommInfoUpdateFreq = recommInfoUpdateFreq;
    }

    public RecommInfoUpdateFreq getRecommendInterval() {
        return recommendInterval;
    }

    public void setRecommendInterval(RecommInfoUpdateFreq recommendInterval) {
        this.recommendInterval = recommendInterval;
    }

    public LocalDateTime getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(LocalDateTime recommendDate) {
        this.recommendDate = recommendDate;
    }

    public ReminderVisit getReminderVisit() {
        return reminderVisit;
    }

    public void setReminderVisit(ReminderVisit reminderVisit) {
        this.reminderVisit = reminderVisit;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}