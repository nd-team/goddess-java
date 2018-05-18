package com.bjike.goddess.staffwelfare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 个人节日
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffwelfare_personalfestival", uniqueConstraints = {@UniqueConstraint(columnNames = {"userName", "festivalName"})})
public class PersonalFestival extends BaseEntity {

    /**
     * 节日名称
     */
    @Column(name = "festivalName", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节日名称'")
    private String festivalName;

    /**
     * 节日时间
     */
    @Column(name = "festivalDate", nullable = false, columnDefinition = "DATE   COMMENT '节日时间'")
    private LocalDate festivalDate;

    /**
     * 可见人员
     */
    @Column(name = "visibleUsers", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '可见人员'")
    private String visibleUsers;

    /**
     * 提醒时间
     */
    @Column(name = "remindTime", nullable = false, columnDefinition = "DATETIME   COMMENT '提醒时间'")
    private LocalDateTime remindTime;

    /**
     * 提示语
     */
    @Column(name = "remindInfo", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '提示语'")
    private String remindInfo;

    /**
     * 是否开通一声祝福
     */
    @Column(name = "is_openWish", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否开通一声祝福(0否，1是)'")
    private Boolean openWish;

    /**
     * 答谢语
     */
    @Column(name = "thankStatement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '答谢语'")
    private String thankStatement;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 节日人姓名
     */
    @Column(name = "userName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工Id'")
    private String userName;

    /**
     * 节日人Id
     */
    @Column(name = "userId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '员工Id'")
    private String userId;

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public LocalDate getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(LocalDate festivalDate) {
        this.festivalDate = festivalDate;
    }


    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindInfo() {
        return remindInfo;
    }

    public void setRemindInfo(String remindInfo) {
        this.remindInfo = remindInfo;
    }

    public Boolean getOpenWish() {
        return openWish;
    }

    public void setOpenWish(Boolean openWish) {
        this.openWish = openWish;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisibleUsers() {
        return visibleUsers;
    }

    public void setVisibleUsers(String visibleUsers) {
        this.visibleUsers = visibleUsers;
    }
}