package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 离职办理节点情况
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SituationTO extends BaseTO {

//    /**
//     * 地区
//     */
//    private String area;
//
//    /**
//     * 项目组/部门
//     */
//    private String department;
//
//    /**
//     * 岗位
//     */
//    private String position;
//
//    /**
//     * 岗位层级
//     */
//    private String positionLever;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 是否填写离职申请表
     */
    @NotNull(message = "是否填写离职申请表不能为空", groups = {ADD.class, EDIT.class})
    private Boolean writeApply;

    /**
     * 是否待离职
     */
    @NotNull(message = "是否待离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean waitDimission;

    /**
     * 是否已离职
     */
    @NotNull(message = "是否已离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean left;

    /**
     * 是否正常离职
     */
    @NotNull(message = "是否正常离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean normalLeave;

    /**
     * 是否自离
     */
    @NotNull(message = "是否自离不能为空", groups = {ADD.class, EDIT.class})
    private Boolean selfLeave;

    /**
     * 是否辞退
     */
    @NotNull(message = "是否辞退不能为空", groups = {ADD.class, EDIT.class})
    private Boolean dismiss;

    /**
     * 是否冻结账号
     */
    @NotNull(message = "是否冻结账号不能为空", groups = {ADD.class, EDIT.class})
    private Boolean freeze;

    /**
     * 是否可挽留
     */
    @NotNull(message = "是否可挽留不能为空", groups = {ADD.class, EDIT.class})
    private Boolean retained;

    /**
     * 是否工作交接
     */
    @NotNull(message = "是否工作交接不能为空", groups = {ADD.class, EDIT.class})
    private Boolean workTransfer;

    /**
     * 是否可提前离职
     */
    @NotNull(message = "是否可提前离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean leaveEarly;

    /**
     * 是否需要延后离职
     */
    @NotNull(message = "是否需要延后离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean postponement;

    /**
     * 是否需填写《员工离职工作交接手续表》
     */
    @NotNull(message = "是否需填写《员工离职工作交接手续表》不能为空", groups = {ADD.class, EDIT.class})
    private Boolean handover;

    /**
     * 项目经理需审核是否可提前离职
     */
    @NotNull(message = "项目经理需审核是否可提前离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean managerAudit;

    /**
     * 模块负责人需审核是否可提前离职
     */
    @NotNull(message = "模块负责人需审核是否可提前离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean principalAudit;

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