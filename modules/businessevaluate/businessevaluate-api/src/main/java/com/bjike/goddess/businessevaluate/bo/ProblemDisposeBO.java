package com.bjike.goddess.businessevaluate.bo;

import com.bjike.goddess.businessevaluate.enums.*;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目问题受理和处理业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-29 02:11 ]
 * @Description: [ 项目问题受理和处理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemDisposeBO extends BaseBO {

    /**
     * 来源
     */
    private String source;

    /**
     * 通知方式
     */
    private String informWay;

    /**
     * 影响部门
     */
    private String affectDepartment;

    /**
     * 时间紧急程度
     */
    private String urgencyLevel;

    /**
     * 责任人员
     */
    private String dutyMan;

    /**
     * 处理人员
     */
    private String disposeMan;

    /**
     * 反应类型
     */
    private String responseType;

    /**
     * 详细情况
     */
    private String detail;

    /**
     * 受理开始时间
     */
    private String disposeStartTime;

    /**
     * 受理终止时间
     */
    private String disposeEndTime;

    /**
     * 经验总结
     */
    private String sufferSummary;

    /**
     * 项目信息Id
     */
    private String projectInfoId;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 工期开始时间
     */
    private String startTime;

    /**
     * 工期结束时间
     */
    private String endTime;

    /**
     * 影响模块
     */
    private String affectModule;

    /**
     * 规定完成时间
     */
    private StipulateFinishTime stipulateFinishTime;

    /**
     * 影响部门数
     */
    private AffectDeptAmount affectDeptAmount;

    /**
     * 问题受理难度
     */
    private ProblemDifficulty problemDifficulty;

    /**
     * 是否按时完成
     */
    private FinishOnTime finishOnTime;

    /**
     * 错误情况
     */
    private ErrorCase errorCase;

    /**
     * 错误类型
     */
    private ErrorType errorType;

    /**
     * 指标规定
     */
    private KpiStipulate kpiStipulate;

    /**
     * 违约金确认单评分
     */
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

    public String getDisposeStartTime() {
        return disposeStartTime;
    }

    public void setDisposeStartTime(String disposeStartTime) {
        this.disposeStartTime = disposeStartTime;
    }

    public String getDisposeEndTime() {
        return disposeEndTime;
    }

    public void setDisposeEndTime(String disposeEndTime) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}