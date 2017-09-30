package com.bjike.goddess.dimission.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 离职办理节点情况
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dimission_situation")
public class Situation extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 岗位层级
     */
    @Column(name = "positionLever", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位层级'")
    private String positionLever;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 是否填写离职申请表
     */
    @Column(name = "is_writeApply", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否填写离职申请表'")
    private Boolean writeApply;

    /**
     * 是否待离职
     */
    @Column(name = "is_waitDimission", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否待离职'")
    private Boolean waitDimission;

    /**
     * 是否已离职
     */
    @Column(name = "is_left", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否已离职'")
    private Boolean left;

    /**
     * 是否正常离职
     */
    @Column(name = "is_normalLeave", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否正常离职'")
    private Boolean normalLeave;

    /**
     * 是否自离
     */
    @Column(name = "is_selfLeave", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否自离'")
    private Boolean selfLeave;

    /**
     * 是否辞退
     */
    @Column(name = "is_dismiss", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否辞退'")
    private Boolean dismiss;

    /**
     * 是否冻结账号
     */
    @Column(name = "is_freeze", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否冻结账号'")
    private Boolean freeze;

    /**
     * 是否可挽留
     */
    @Column(name = "is_retained", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可挽留'")
    private Boolean retained;

    /**
     * 是否工作交接
     */
    @Column(name = "is_workTransfer", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否工作交接'")
    private Boolean workTransfer;

    /**
     * 是否可提前离职
     */
    @Column(name = "is_leaveEarly", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可提前离职'")
    private Boolean leaveEarly;

    /**
     * 是否需要延后离职
     */
    @Column(name = "is_postponement", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需要延后离职'")
    private Boolean postponement;

    /**
     * 是否需填写《员工离职工作交接手续表》
     */
    @Column(name = "is_handover", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否需填写《员工离职工作交接手续表》'")
    private Boolean handover;

    /**
     * 项目经理需审核是否可提前离职
     */
    @Column(name = "is_managerAudit", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '项目经理需审核是否可提前离职'")
    private Boolean managerAudit;

    /**
     * 模块负责人需审核是否可提前离职
     */
    @Column(name = "is_principalAudit", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '模块负责人需审核是否可提前离职'")
    private Boolean principalAudit;


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

    public String getPositionLever() {
        return positionLever;
    }

    public void setPositionLever(String positionLever) {
        this.positionLever = positionLever;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWriteApply() {
        return writeApply;
    }

    public void setWriteApply(Boolean writeApply) {
        this.writeApply = writeApply;
    }

    public Boolean getWaitDimission() {
        return waitDimission;
    }

    public void setWaitDimission(Boolean waitDimission) {
        this.waitDimission = waitDimission;
    }

    public Boolean getLeft() {
        return left;
    }

    public void setLeft(Boolean left) {
        this.left = left;
    }

    public Boolean getNormalLeave() {
        return normalLeave;
    }

    public void setNormalLeave(Boolean normalLeave) {
        this.normalLeave = normalLeave;
    }

    public Boolean getSelfLeave() {
        return selfLeave;
    }

    public void setSelfLeave(Boolean selfLeave) {
        this.selfLeave = selfLeave;
    }

    public Boolean getDismiss() {
        return dismiss;
    }

    public void setDismiss(Boolean dismiss) {
        this.dismiss = dismiss;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }

    public Boolean getRetained() {
        return retained;
    }

    public void setRetained(Boolean retained) {
        this.retained = retained;
    }

    public Boolean getWorkTransfer() {
        return workTransfer;
    }

    public void setWorkTransfer(Boolean workTransfer) {
        this.workTransfer = workTransfer;
    }

    public Boolean getLeaveEarly() {
        return leaveEarly;
    }

    public void setLeaveEarly(Boolean leaveEarly) {
        this.leaveEarly = leaveEarly;
    }

    public Boolean getPostponement() {
        return postponement;
    }

    public void setPostponement(Boolean postponement) {
        this.postponement = postponement;
    }

    public Boolean getHandover() {
        return handover;
    }

    public void setHandover(Boolean handover) {
        this.handover = handover;
    }

    public Boolean getManagerAudit() {
        return managerAudit;
    }

    public void setManagerAudit(Boolean managerAudit) {
        this.managerAudit = managerAudit;
    }

    public Boolean getPrincipalAudit() {
        return principalAudit;
    }

    public void setPrincipalAudit(Boolean principalAudit) {
        this.principalAudit = principalAudit;
    }
}