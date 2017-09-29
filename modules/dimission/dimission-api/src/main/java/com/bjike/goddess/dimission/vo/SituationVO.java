package com.bjike.goddess.dimission.vo;

/**
 * 离职办理节点情况表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SituationVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 岗位层级
     */
    private String positionLever;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否填写离职申请表
     */
    private Boolean writeApply;

    /**
     * 是否待离职
     */
    private Boolean waitDimission;

    /**
     * 是否已离职
     */
    private Boolean left;

    /**
     * 是否正常离职
     */
    private Boolean normalLeave;

    /**
     * 是否自离
     */
    private Boolean selfLeave;

    /**
     * 是否辞退
     */
    private Boolean dismiss;

    /**
     * 是否冻结账号
     */
    private Boolean freeze;

    /**
     * 是否可挽留
     */
    private Boolean retained;

    /**
     * 是否工作交接
     */
    private Boolean workTransfer;

    /**
     * 是否可提前离职
     */
    private Boolean leaveEarly;

    /**
     * 是否需要延后离职
     */
    private Boolean postponement;

    /**
     * 是否需填写《员工离职工作交接手续表》
     */
    private Boolean handover;

    /**
     * 项目经理需审核是否可提前离职
     */
    private Boolean managerAudit;

    /**
     * 模块负责人需审核是否可提前离职
     */
    private Boolean principalAudit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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