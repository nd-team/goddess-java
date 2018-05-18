package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.task.enums.ProblemStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问题编辑数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-21 10:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemEditTO extends BaseTO {

    /**
     * 所属地区
     */
    @NotBlank(message = "所属地区不能为空", groups = {EDIT.class})
    private String area;
    /**
     * 所属部门
     */
    @NotBlank(message = "所属部门不能为空", groups = {EDIT.class})

    private String department;
    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {EDIT.class})

    private String position;
    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空", groups = {EDIT.class})

    private String claimer;
    /**
     * 问题项目
     */
    @NotBlank(message = "问题项目不能为空", groups = {EDIT.class})

    private String project;
    /**
     * 背景描述
     */
    @NotBlank(message = "背景描述不能为空", groups = {EDIT.class})

    private String describes;
    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空", groups = {EDIT.class})

    private String details;

    /**
     * 问题类型
     */
    @NotBlank(message = "问题类型不能为空", groups = {EDIT.class})

    private String typeId;

    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空", groups = {EDIT.class})

    private String auditor;
    /**
     * 期望受理时间
     */
    @NotBlank(message = "期望受理时间不能为空", groups = {EDIT.class})

    private String expectTime;
    /**
     * 协助部门
     */
    @NotBlank(message = "协助部门不能为空", groups = {EDIT.class})

    private String giveDepartment;
    /**
     * 协助模块
     */
    @NotBlank(message = "协助模块不能为空", groups = {EDIT.class})

    private String giveModule;
    /**
     * 问题状态
     */
    @NotNull(message = "问题状态不能为空", groups = {EDIT.class})

    private ProblemStatus status;

//-----------------------以下为受理人录入

    /**
     * 受理人
     */
//    @NotBlank(message = "受理人不能为空", groups = {EDIT.class})

    private String accepter;


    /**
     * 问题受理所属部门
     */
//    @NotBlank(message = "问题受理所属部门不能为空", groups = {EDIT.class})

    private String acceptDepartment;
    /**
     * 问题跟进处理计划完成时间
     */
//    @NotBlank(message = "问题跟进处理计划完成时间不能为空", groups = {EDIT.class})

    private String planFinishTime;
    /**
     * 问题跟进处理实际完成时间
     */
//    @NotBlank(message = "问题跟进处理实际完成时间不能为空", groups = {EDIT.class})

    private String finishTime;
    /**
     * 问题处理结果
     */
//    @NotBlank(message = "问题处理结果不能为空", groups = {EDIT.class})

    private String result;
    /**
     * 是否闭环
     */
//    @NotNull(message = "是否闭环不能为空", groups = {EDIT.class})

    private Boolean closedLoop;
    /**
     * 是否需要协调
     */
//    @NotNull(message = "是否需要协调不能为空", groups = {EDIT.class})

    private Boolean needGive;
    /**
     * 协调结果
     */
//    @NotBlank(message = "协调结果不能为空", groups = {EDIT.class})

    private String giveResult;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
