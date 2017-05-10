package com.bjike.goddess.projectissuehandle.vo;

import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingResult;
import com.bjike.goddess.projectissuehandle.enums.ProblemRelevantDepartment;

/**
 * 确认问题处理结果表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemHandlingResultVO {

    /**
     * id
     */
    private String id;
    /**
     * 年份
     */
    private String year;
    /**
     * 地区
     */
    private String area;

    /**
     * 合同外部项目名称
     */
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 工程类型
     */
    private String projectType;

    /**
     * 问题受理时间
     */
    private String problemAcceptTime;

    /**
     * 问题具体情况
     */
    private String problemSpecificSituation;

    /**
     * 问题对象
     */
    private String problemObject;


    /**
     * 问题责任人员
     */
    private String problemResponsible;

    /**
     * 问题处理人员
     */
    private String problemHandler;

    /**
     * 问题相关部门
     */
    private ProblemRelevantDepartment problemRelevantDepartment;

    /**
     * 问题发生时间
     */
    private String problemOccurrenceTime;

    /**
     * 问题发生地点
     */
    private String problemOccurrencePlace;

    /**
     * 问题解决时间
     */
    private String problemSolveTime;

    /**
     * 问题处理结果
     */
    private ProblemProcessingResult problemProcessingResult;

    /**
     * 问题总结
     */
    private String problemConclusion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExternalContractProjectName() {
        return externalContractProjectName;
    }

    public void setExternalContractProjectName(String externalContractProjectName) {
        this.externalContractProjectName = externalContractProjectName;
    }

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProblemAcceptTime() {
        return problemAcceptTime;
    }

    public void setProblemAcceptTime(String problemAcceptTime) {
        this.problemAcceptTime = problemAcceptTime;
    }

    public String getProblemSpecificSituation() {
        return problemSpecificSituation;
    }

    public void setProblemSpecificSituation(String problemSpecificSituation) {
        this.problemSpecificSituation = problemSpecificSituation;
    }

    public String getProblemObject() {
        return problemObject;
    }

    public void setProblemObject(String problemObject) {
        this.problemObject = problemObject;
    }

    public String getProblemResponsible() {
        return problemResponsible;
    }

    public void setProblemResponsible(String problemResponsible) {
        this.problemResponsible = problemResponsible;
    }

    public String getProblemHandler() {
        return problemHandler;
    }

    public void setProblemHandler(String problemHandler) {
        this.problemHandler = problemHandler;
    }

    public ProblemRelevantDepartment getProblemRelevantDepartment() {
        return problemRelevantDepartment;
    }

    public void setProblemRelevantDepartment(ProblemRelevantDepartment problemRelevantDepartment) {
        this.problemRelevantDepartment = problemRelevantDepartment;
    }

    public String getProblemOccurrenceTime() {
        return problemOccurrenceTime;
    }

    public void setProblemOccurrenceTime(String problemOccurrenceTime) {
        this.problemOccurrenceTime = problemOccurrenceTime;
    }

    public String getProblemOccurrencePlace() {
        return problemOccurrencePlace;
    }

    public void setProblemOccurrencePlace(String problemOccurrencePlace) {
        this.problemOccurrencePlace = problemOccurrencePlace;
    }

    public String getProblemSolveTime() {
        return problemSolveTime;
    }

    public void setProblemSolveTime(String problemSolveTime) {
        this.problemSolveTime = problemSolveTime;
    }

    public ProblemProcessingResult getProblemProcessingResult() {
        return problemProcessingResult;
    }

    public void setProblemProcessingResult(ProblemProcessingResult problemProcessingResult) {
        this.problemProcessingResult = problemProcessingResult;
    }

    public String getProblemConclusion() {
        return problemConclusion;
    }

    public void setProblemConclusion(String problemConclusion) {
        this.problemConclusion = problemConclusion;
    }
}