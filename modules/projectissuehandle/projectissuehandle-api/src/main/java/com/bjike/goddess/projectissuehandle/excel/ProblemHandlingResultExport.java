package com.bjike.goddess.projectissuehandle.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 确认问题处理结果导出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-20T20:13:56.346 ]
 * @Description: [ 确认问题处理结果导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemHandlingResultExport extends BaseBO {
    /**
     * 项目问题编号
     */
    @ExcelHeader(name = "项目问题编号",notNull = true)
    private String projectNum;
    /**
     * 年份
     */
    @ExcelHeader(name = "年份",notNull = true)
    private String year;
    /**
     * 地区
     */
    @ExcelHeader(name = "地区",notNull = true)
    private String area;

    /**
     * 合同外部项目名称
     */
    @ExcelHeader(name = "合同外部项目名称",notNull = true)
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称",notNull = true)
    private String internalProjectName;

    /**
     * 工程类型
     */
    @ExcelHeader(name = "工程类型",notNull = true)
    private String projectType;

    /**
     * 问题受理时间
     */
    @ExcelHeader(name = "问题受理时间",notNull = true)
    private String problemAcceptTime;

    /**
     * 问题具体情况
     */
    @ExcelHeader(name = "问题具体情况",notNull = true)
    private String problemSpecificSituation;

    /**
     * 问题对象
     */
    @ExcelHeader(name = "问题对象",notNull = true)
    private String problemObject;

    /**
     * 问题责任人员
     */
    @ExcelHeader(name = "问题责任人员",notNull = true)
    private String problemResponsible;

    /**
     * 问题处理人员
     */
    @ExcelHeader(name = "问题处理人员",notNull = true)
    private String problemHandler;

    /**
     * 问题相关部门
     */
    @ExcelHeader(name = "问题相关部门",notNull = true)
    private String problemRelevantDepartment;

    /**
     * 问题发生时间
     */
    @ExcelHeader(name = "问题发生时间",notNull = true)
    private String problemOccurrenceTime;

    /**
     * 问题发生地点
     */
    @ExcelHeader(name = "问题发生地点",notNull = true)
    private String problemOccurrencePlace;

    /**
     * 问题解决时间
     */
    @ExcelHeader(name = "问题解决时间",notNull = true)
    private String problemSolveTime;

    /**
     * 问题处理结果
     */
    @ExcelHeader(name = "问题处理结果",notNull = true)
    private String problemProcessingResult;

    /**
     * 问题总结
     */
    @ExcelHeader(name = "问题总结",notNull = true)
    private String problemConclusion;

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
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

    public String getProblemRelevantDepartment() {
        return problemRelevantDepartment;
    }

    public void setProblemRelevantDepartment(String problemRelevantDepartment) {
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

    public String getProblemProcessingResult() {
        return problemProcessingResult;
    }

    public void setProblemProcessingResult(String problemProcessingResult) {
        this.problemProcessingResult = problemProcessingResult;
    }

    public String getProblemConclusion() {
        return problemConclusion;
    }

    public void setProblemConclusion(String problemConclusion) {
        this.problemConclusion = problemConclusion;
    }
}