package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.ProblemStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 问题
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 09:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_problem")
public class Problem extends BaseEntity {
    /**
     * 录入人
     */
    @Column(columnDefinition = "VARCHAR(36) comment '录入人' ", nullable = false)
    private String recorder;
    /**
     * 问题编号
     */
    @Column(columnDefinition = "VARCHAR(15) comment '问题编号' ", nullable = false)
    private String number;
    /**
     * 所属地区
     */
    @Column(columnDefinition = "VARCHAR(50) comment '所属地区' ")
    private String area;
    /**
     * 所属部门
     */
    @Column(columnDefinition = "VARCHAR(36) comment '所属部门' ")
    private String department;
    /**
     * 岗位
     */
    @Column(columnDefinition = "VARCHAR(36) comment '岗位' ", nullable = false)
    private String position;
    /**
     * 问题提出人
     */
    @Column(columnDefinition = "VARCHAR(36) comment '问题提出人' ", nullable = false)
    private String claimer;
    /**
     * 问题项目
     */
    @Column(columnDefinition = "VARCHAR(36) comment '问题项目' ", nullable = false)
    private String project;
    /**
     * 背景描述
     */
    @Column(columnDefinition = "VARCHAR(255) comment '背景描述' ")
    private String describes;
    /**
     * 问题描述
     */
    @Column(columnDefinition = "TEXT comment '问题描述' ")
    private String details;

    /**
     * 问题类型
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", columnDefinition = "VARCHAR(36) COMMENT '问题类型' ", nullable = false)
    private ProblemType type;
    /**
     * 是否上报
     */
    @Column(name = "is_report", columnDefinition = "TINYINT(1)  COMMENT '是否上报'", nullable = false)
    private Boolean report;
    /**
     * 审核人
     */
    @Column(columnDefinition = "VARCHAR(36) comment '审核人' ")
    private String auditor;
    /**
     * 期望受理时间
     */
    @Column(columnDefinition = "DATETIME  COMMENT '期望受理时间'", nullable = false)
    private LocalDateTime expectTime;
    /**
     * 协助部门
     */
    @Column(columnDefinition = "VARCHAR(36) comment '协助部门' ")
    private String giveDepartment;
    /**
     * 协助模块
     */
    @Column(columnDefinition = "VARCHAR(36) comment '协助模块' ")
    private String giveModule;

    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '问题状态' ", nullable = false, insertable = false)
    private ProblemStatus status;

//-----------------------以下为受理人录入

    /**
     * 受理人
     */
    @Column(columnDefinition = "VARCHAR(36) comment '受理人' ")
    private String accepter;

    /**
     * 问题受理编号
     */
    @Column(columnDefinition = "VARCHAR(15) comment '问题受理编号' ")
    private String acceptNumber;
    /**
     * 问题受理所属部门
     */
    @Column(columnDefinition = "VARCHAR(36) comment '问题受理所属部门' ")
    private String acceptDepartment;
    /**
     * 问题跟进处理计划完成时间
     */
    @Column(columnDefinition = "DATETIME  COMMENT '问题跟进处理计划完成时间'")
    private LocalDateTime planFinishTime;
    /**
     * 问题跟进处理实际完成时间
     */
    @Column(columnDefinition = "DATETIME  COMMENT '问题跟进处理实际完成时间'")
    private LocalDateTime finishTime;
    /**
     * 问题处理结果
     */
    @Column(columnDefinition = "VARCHAR(36) comment '问题处理结果' ")
    private String result;
    /**
     * 是否闭环
     */
    @Column(name = "is_closedLoop", columnDefinition = "TINYINT(1)  COMMENT '是否闭环'")

    private boolean closedLoop;
    /**
     * 是否需要协调
     */
    @Column(name = "is_needGive", columnDefinition = "TINYINT(1)  COMMENT '是否需要协调'")

    private boolean needGive;
    /**
     * 协调结果
     */
    @Column(columnDefinition = "TEXT  COMMENT '协调结果'")

    private String giveResult;

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

    public ProblemType getType() {
        return type;
    }

    public void setType(ProblemType type) {
        this.type = type;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
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

    public LocalDateTime getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(LocalDateTime expectTime) {
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

    public LocalDateTime getPlanFinishTime() {
        return planFinishTime;
    }

    public void setPlanFinishTime(LocalDateTime planFinishTime) {
        this.planFinishTime = planFinishTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isClosedLoop() {
        return closedLoop;
    }

    public void setClosedLoop(boolean closedLoop) {
        this.closedLoop = closedLoop;
    }

    public boolean isNeedGive() {
        return needGive;
    }

    public void setNeedGive(boolean needGive) {
        this.needGive = needGive;
    }

    public String getGiveResult() {
        return giveResult;
    }

    public void setGiveResult(String giveResult) {
        this.giveResult = giveResult;
    }
}
