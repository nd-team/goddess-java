package com.bjike.goddess.checkfunds.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 银企对账（核对）
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkfunds_bankreconciliation")
public class BankReconciliation extends BaseEntity {

    /**
     * 核对状态
     */
    @Column(name = "aduitStatus", columnDefinition = "VARCHAR(255)   COMMENT '核对状态'")
    private String aduitStatus;

    /**
     * 用户名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用户名'")
    private String name;

    /**
     * 对账年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '对账年份'")
    private Integer year;

    /**
     * 对账月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '对账月份'")
    private Integer month;

    /**
     * 余额核对状态
     */
    @Column(name = "remainAduitStatus", columnDefinition = "VARCHAR(255)   COMMENT '余额核对状态'")
    private String remainAduitStatus;

    /**
     * 余额调节后
     */
    @Column(name = "afterReconciliation", columnDefinition = "VARCHAR(255)   COMMENT '余额调节后'")
    private String afterReconciliation;

    /**
     * 经办时间
     */
    @Column(name = "handleTime", nullable = false, columnDefinition = "DATETIME   COMMENT '经办时间'")
    private LocalDateTime handleTime;

    /**
     * 经办人
     */
    @Column(name = "handler", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经办人'")
    private String handler;

    /**
     * 审批时间
     */
    @Column(name = "examineTime", columnDefinition = "DATETIME   COMMENT '审批时间'")
    private LocalDateTime examineTime;

    /**
     * 审批人
     */
    @Column(name = "examine", columnDefinition = "VARCHAR(255)   COMMENT '审批人'")
    private String examine;

    /**
     * 是否审批过
     */
    @Column(name = "haveExamine", columnDefinition = "TINYINT(1)   COMMENT '是否审批过'")
    private Boolean haveExamine;

    public Boolean getHaveExamine() {
        return haveExamine;
    }

    public void setHaveExamine(Boolean haveExamine) {
        this.haveExamine = haveExamine;
    }

    public String getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(String aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getRemainAduitStatus() {
        return remainAduitStatus;
    }

    public void setRemainAduitStatus(String remainAduitStatus) {
        this.remainAduitStatus = remainAduitStatus;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getAfterReconciliation() {
        return afterReconciliation;
    }

    public void setAfterReconciliation(String afterReconciliation) {
        this.afterReconciliation = afterReconciliation;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public LocalDateTime getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(LocalDateTime examineTime) {
        this.examineTime = examineTime;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }
}