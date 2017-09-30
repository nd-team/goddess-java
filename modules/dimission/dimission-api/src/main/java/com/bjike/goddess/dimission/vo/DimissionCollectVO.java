package com.bjike.goddess.dimission.vo;

/**
 * 汇总
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DimissionCollectVO {

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
     * 岗位层级
     */
    private String positionLever;

    /**
     * 申请离职人数
     */
    private Integer writeApplyNum;

    /**
     * 待离职人数（受理离职人数）
     */
    private Integer waitDimissionNum;

    /**
     * 已离职人数
     */
    private Integer leftNum;

    /**
     * 自离人数
     */
    private Integer selfNum;

    /**
     * 需冻结账号数
     */
    private Integer freezeNum;

    /**
     * 辞退人数
     */
    private Integer dismissNum;

    /**
     * 可挽留人数
     */
    private Integer retainedNum;

    /**
     * 需项目经理离职面谈数
     */
    private Integer managerInterviewNum;

    /**
     * 需模块负责离职面谈数
     */
    private Integer principalInterviewNum;

    /**
     * 需福利模块离职面谈数
     */
    private Integer welfareInterviewNum;

    /**
     * 需总经办离职面谈数
     */
    private Integer officeInterviewNum;

    /**
     * 可提前离职数
     */
    private Integer leaveEarlyNum;

    /**
     * 项目经理需审核是否可提前离职的审核量
     */
    private Integer managerAuditNum;

    /**
     * 模块负责人需审核是否可提前离职的审核量
     */
    private Integer principalAuditNum;

    /**
     * 需延后离职的人数
     */
    private Integer postponementNum;

    /**
     * 工作交接数
     */
    private Integer workTransferNum;

    /**
     * 需填写《员工离职工作交接手续表》人数
     */
    private Integer handoverNum;

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

    public String getPositionLever() {
        return positionLever;
    }

    public void setPositionLever(String positionLever) {
        this.positionLever = positionLever;
    }

    public Integer getWriteApplyNum() {
        return writeApplyNum;
    }

    public void setWriteApplyNum(Integer writeApplyNum) {
        this.writeApplyNum = writeApplyNum;
    }

    public Integer getWaitDimissionNum() {
        return waitDimissionNum;
    }

    public void setWaitDimissionNum(Integer waitDimissionNum) {
        this.waitDimissionNum = waitDimissionNum;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getSelfNum() {
        return selfNum;
    }

    public void setSelfNum(Integer selfNum) {
        this.selfNum = selfNum;
    }

    public Integer getFreezeNum() {
        return freezeNum;
    }

    public void setFreezeNum(Integer freezeNum) {
        this.freezeNum = freezeNum;
    }

    public Integer getDismissNum() {
        return dismissNum;
    }

    public void setDismissNum(Integer dismissNum) {
        this.dismissNum = dismissNum;
    }

    public Integer getRetainedNum() {
        return retainedNum;
    }

    public void setRetainedNum(Integer retainedNum) {
        this.retainedNum = retainedNum;
    }

    public Integer getManagerInterviewNum() {
        return managerInterviewNum;
    }

    public void setManagerInterviewNum(Integer managerInterviewNum) {
        this.managerInterviewNum = managerInterviewNum;
    }

    public Integer getPrincipalInterviewNum() {
        return principalInterviewNum;
    }

    public void setPrincipalInterviewNum(Integer principalInterviewNum) {
        this.principalInterviewNum = principalInterviewNum;
    }

    public Integer getWelfareInterviewNum() {
        return welfareInterviewNum;
    }

    public void setWelfareInterviewNum(Integer welfareInterviewNum) {
        this.welfareInterviewNum = welfareInterviewNum;
    }

    public Integer getOfficeInterviewNum() {
        return officeInterviewNum;
    }

    public void setOfficeInterviewNum(Integer officeInterviewNum) {
        this.officeInterviewNum = officeInterviewNum;
    }

    public Integer getLeaveEarlyNum() {
        return leaveEarlyNum;
    }

    public void setLeaveEarlyNum(Integer leaveEarlyNum) {
        this.leaveEarlyNum = leaveEarlyNum;
    }

    public Integer getManagerAuditNum() {
        return managerAuditNum;
    }

    public void setManagerAuditNum(Integer managerAuditNum) {
        this.managerAuditNum = managerAuditNum;
    }

    public Integer getPrincipalAuditNum() {
        return principalAuditNum;
    }

    public void setPrincipalAuditNum(Integer principalAuditNum) {
        this.principalAuditNum = principalAuditNum;
    }

    public Integer getPostponementNum() {
        return postponementNum;
    }

    public void setPostponementNum(Integer postponementNum) {
        this.postponementNum = postponementNum;
    }

    public Integer getWorkTransferNum() {
        return workTransferNum;
    }

    public void setWorkTransferNum(Integer workTransferNum) {
        this.workTransferNum = workTransferNum;
    }

    public Integer getHandoverNum() {
        return handoverNum;
    }

    public void setHandoverNum(Integer handoverNum) {
        this.handoverNum = handoverNum;
    }
}