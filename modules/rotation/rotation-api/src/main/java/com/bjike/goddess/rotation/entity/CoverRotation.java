package com.bjike.goddess.rotation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.rotation.enums.AuditType;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 岗位轮换自荐
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rotation_coverrotation")
public class CoverRotation extends BaseEntity {

    /**
     * 姓名
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String username;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 目前岗位层级
     */
    @Column(name = "arrangement", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '目前岗位层级'")
    private String arrangement;

    /**
     * 申请轮换等级
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "applyLevel_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '申请轮换等级'")
    private SubsidyStandard applyLevel;

    /**
     * 申请轮换原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请轮换原因'")
    private String reason;

    /**
     * 轮换后岗位等级
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "rotationLevel_id", columnDefinition = "VARCHAR(36)   COMMENT '轮换后岗位等级'")
    private SubsidyStandard rotationLevel;

    /**
     * 总经办
     */
    @Column(name = "general", columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String general;

    /**
     * 总经办意见
     */
    @Column(name = "opinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String opinion;

    /**
     * 是否通过
     */
    @Column(name = "audit", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '是否通过'", insertable = false)
    private AuditType audit;

    /**
     * 轮换时间
     */
    @Column(name = "rotationDate", columnDefinition = "DATE   COMMENT '轮换时间'")
    private LocalDate rotationDate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public SubsidyStandard getApplyLevel() {
        return applyLevel;
    }

    public void setApplyLevel(SubsidyStandard applyLevel) {
        this.applyLevel = applyLevel;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public SubsidyStandard getRotationLevel() {
        return rotationLevel;
    }

    public void setRotationLevel(SubsidyStandard rotationLevel) {
        this.rotationLevel = rotationLevel;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public LocalDate getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(LocalDate rotationDate) {
        this.rotationDate = rotationDate;
    }
}