package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.businessevaluate.enums.*;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 项目问题受理和处理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_problemdispose")
public class ProblemDispose extends BaseEntity {

    /**
     * 来源
     */
    @Column(name = "source", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '来源'")
    private String source;

    /**
     * 通知方式
     */
    @Column(name = "informWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通知方式'")
    private String informWay;

    /**
     * 影响部门
     */
    @Column(name = "affectDepartment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响部门'")
    private String affectDepartment;

    /**
     * 时间紧急程度
     */
    @Column(name = "urgencyLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '时间紧急程度'")
    private String urgencyLevel;

    /**
     * 责任人员
     */
    @Column(name = "dutyMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '责任人员'")
    private String dutyMan;

    /**
     * 处理人员
     */
    @Column(name = "disposeMan", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '处理人员'")
    private String disposeMan;

    /**
     * 反应类型
     */
    @Column(name = "responseType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '反应类型'")
    private String responseType;

    /**
     * 详细情况
     */
    @Column(name = "detail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '详细情况'")
    private String detail;

    /**
     * 受理开始时间
     */
    @Column(name = "disposeStartTime", nullable = false, columnDefinition = "DATETIME   COMMENT '受理开始时间'")
    private LocalDateTime disposeStartTime;

    /**
     * 受理终止时间
     */
    @Column(name = "disposeEndTime", nullable = false, columnDefinition = "DATETIME   COMMENT '受理终止时间'")
    private LocalDateTime disposeEndTime;

    /**
     * 经验总结
     */
    @Column(name = "sufferSummary", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经验总结'")
    private String sufferSummary;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目信息Id'")
    private String projectInfoId;

    /**
     * 影响模块
     */
    @Column(name = "affectModule", columnDefinition = "TINYINT(2)   COMMENT '影响模块'")
    private String affectModule;

    /**
     * 规定完成时间
     */
    @Column(name = "stipulateFinishTime", columnDefinition = "TINYINT(2)   COMMENT '规定完成时间'")
    private StipulateFinishTime stipulateFinishTime;

    /**
     * 影响部门数
     */
    @Column(name = "affectDeptAmount", columnDefinition = "TINYINT(2)   COMMENT '影响部门数'")
    private AffectDeptAmount affectDeptAmount;

    /**
     * 问题受理难度
     */
    @Column(name = "problemDifficulty", columnDefinition = "TINYINT(2)   COMMENT '问题受理难度'")
    private ProblemDifficulty problemDifficulty;

    /**
     * 是否按时完成
     */
    @Column(name = "finishOnTime", columnDefinition = "TINYINT(2)   COMMENT '是否按时完成'")
    private FinishOnTime finishOnTime;

    /**
     * 错误情况
     */
    @Column(name = "errorCase", columnDefinition = "TINYINT(2)   COMMENT '错误情况'")
    private ErrorCase errorCase;

    /**
     * 错误类型
     */
    @Column(name = "errorType", columnDefinition = "TINYINT(2)   COMMENT '错误类型'")
    private ErrorType errorType;

    /**
     * 指标规定
     */
    @Column(name = "kpiStipulate", columnDefinition = "TINYINT(2)   COMMENT '指标规定'")
    private KpiStipulate kpiStipulate;

    /**
     * 违约金确认单评分
     */
    @Column(name = "grade", columnDefinition = "DECIMAL(10,2)   COMMENT '是否按时完成'")
    private Double grade;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInformWay() {
        return informWay;
    }

    public void setInformWay(String informWay) {
        this.informWay = informWay;
    }

    public String getAffectDepartment() {
        return affectDepartment;
    }

    public void setAffectDepartment(String affectDepartment) {
        this.affectDepartment = affectDepartment;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getDutyMan() {
        return dutyMan;
    }

    public void setDutyMan(String dutyMan) {
        this.dutyMan = dutyMan;
    }

    public String getDisposeMan() {
        return disposeMan;
    }

    public void setDisposeMan(String disposeMan) {
        this.disposeMan = disposeMan;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getDisposeStartTime() {
        return disposeStartTime;
    }

    public void setDisposeStartTime(LocalDateTime disposeStartTime) {
        this.disposeStartTime = disposeStartTime;
    }

    public LocalDateTime getDisposeEndTime() {
        return disposeEndTime;
    }

    public void setDisposeEndTime(LocalDateTime disposeEndTime) {
        this.disposeEndTime = disposeEndTime;
    }

    public String getSufferSummary() {
        return sufferSummary;
    }

    public void setSufferSummary(String sufferSummary) {
        this.sufferSummary = sufferSummary;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getAffectModule() {
        return affectModule;
    }

    public void setAffectModule(String affectModule) {
        this.affectModule = affectModule;
    }

    public StipulateFinishTime getStipulateFinishTime() {
        return stipulateFinishTime;
    }

    public void setStipulateFinishTime(StipulateFinishTime stipulateFinishTime) {
        this.stipulateFinishTime = stipulateFinishTime;
    }

    public AffectDeptAmount getAffectDeptAmount() {
        return affectDeptAmount;
    }

    public void setAffectDeptAmount(AffectDeptAmount affectDeptAmount) {
        this.affectDeptAmount = affectDeptAmount;
    }

    public ProblemDifficulty getProblemDifficulty() {
        return problemDifficulty;
    }

    public void setProblemDifficulty(ProblemDifficulty problemDifficulty) {
        this.problemDifficulty = problemDifficulty;
    }

    public FinishOnTime getFinishOnTime() {
        return finishOnTime;
    }

    public void setFinishOnTime(FinishOnTime finishOnTime) {
        this.finishOnTime = finishOnTime;
    }

    public ErrorCase getErrorCase() {
        return errorCase;
    }

    public void setErrorCase(ErrorCase errorCase) {
        this.errorCase = errorCase;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public KpiStipulate getKpiStipulate() {
        return kpiStipulate;
    }

    public void setKpiStipulate(KpiStipulate kpiStipulate) {
        this.kpiStipulate = kpiStipulate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}