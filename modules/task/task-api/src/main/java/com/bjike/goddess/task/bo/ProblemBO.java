package com.bjike.goddess.task.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.task.enums.ProblemStatus;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 11:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemBO extends BaseBO {
    /**
     * 录入人
     */
    private String recorder;
    /**
     * 问题编号
     */
    private String number;
    /**
     * 所属地区
     */
    private String area;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 岗位
     */
    private String position;
    /**
     * 问题提出人
     */
    private String claimer;
    /**
     * 问题项目
     */
    private String project;
    /**
     * 背景描述
     */
    private String describes;
    /**
     * 问题描述
     */
    private String details;

    /**
     * 问题类型
     */
    private String type;
    /**
     * 问题类型id
     */
    private String typeId;
    /**
     * 是否上报
     */
    private Boolean report;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 期望受理时间
     */
    private String expectTime;
    /**
     * 协助部门
     */
    private String giveDepartment;
    /**
     * 协助模块
     */
    private String giveModule;
    /**
     * 问题状态
     */
    private ProblemStatus status;

//-----------------------以下为受理人录入

    /**
     * 受理人
     */
    private String accepter;

    /**
     * 问题受理编号
     */
    private String acceptNumber;
    /**
     * 问题受理所属部门
     */
    private String acceptDepartment;
    /**
     * 问题跟进处理计划完成时间
     */
    private String planFinishTime;
    /**
     * 问题跟进处理实际完成时间
     */
    private String finishTime;
    /**
     * 问题处理结果
     */
    private String result;
    /**
     * 是否闭环
     */

    private Boolean closedLoop;
    /**
     * 是否需要协调
     */

    private Boolean needGive;
    /**
     * 协调结果
     */

    private String giveResult;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getGiveDepartment() {
        return giveDepartment;
    }

    public void setGiveDepartment(String giveDepartment) {
        this.giveDepartment = giveDepartment;
    }

    public String getGiveModule() {
        return giveModule;
    }

    public void setGiveModule(String giveModule) {
        this.giveModule = giveModule;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public String getAcceptDepartment() {
        return acceptDepartment;
    }

    public void setAcceptDepartment(String acceptDepartment) {
        this.acceptDepartment = acceptDepartment;
    }

    public String getPlanFinishTime() {
        return planFinishTime;
    }

    public void setPlanFinishTime(String planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(Boolean closedLoop) {
        this.closedLoop = closedLoop;
    }

    public Boolean getNeedGive() {
        return needGive;
    }

    public void setNeedGive(Boolean needGive) {
        this.needGive = needGive;
    }

    public String getGiveResult() {
        return giveResult;
    }

    public void setGiveResult(String giveResult) {
        this.giveResult = giveResult;
    }
}
