package com.bjike.goddess.projectissuehandle.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.projectissuehandle.enums.AffectedDepartment;
import com.bjike.goddess.projectissuehandle.enums.NoticeWay;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingTime;
import com.bjike.goddess.projectissuehandle.enums.ProblemTypes;

/**
 * 项目执行中的问题导出
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-20T20:13:56.346 ]
 * @Description: [ 项目执行中的问题导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemAcceptExport extends BaseBO {
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
     * 合同外部编号
     */
    @ExcelHeader(name = "合同外部编号",notNull = true)
    private String externalContractNum;

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
     * 内部项目编号
     */
    @ExcelHeader(name = "内部项目编号",notNull = true)
    private String internalNum;

    /**
     * 工程类型
     */
    @ExcelHeader(name = "工程类型",notNull = true)
    private String projectType;

    /**
     * 通知方式
     */
    @ExcelHeader(name = "通知方式",notNull = true)
    private String noticeWay;

    /**
     * 问题具体内容
     */
    @ExcelHeader(name = "问题具体内容",notNull = true)
    private String problemSpecificContent;

    /**
     * 问题类型
     */
    @ExcelHeader(name = "问题类型",notNull = true)
    private String problemTypes;

    /**
     * 解决方式
     */
    @ExcelHeader(name = "解决方式",notNull = true)
    private String solution;

    /**
     * 问题紧急程度
     */
    @ExcelHeader(name = "问题紧急程度",notNull = true)
    private String problemEmergencyDegree;
    /**
     * 问题处理时间
     */
    @ExcelHeader(name = "问题处理时间",notNull = true)
    private String problemProcessingTime;

    /**
     * 受影响部门
     */
    @ExcelHeader(name = "受影响部门",notNull = true)
    private String affectedDepartment;

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

    public String getExternalContractNum() {
        return externalContractNum;
    }

    public void setExternalContractNum(String externalContractNum) {
        this.externalContractNum = externalContractNum;
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

    public String getInternalNum() {
        return internalNum;
    }

    public void setInternalNum(String internalNum) {
        this.internalNum = internalNum;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getNoticeWay() {
        return noticeWay;
    }

    public void setNoticeWay(String noticeWay) {
        this.noticeWay = noticeWay;
    }

    public String getProblemSpecificContent() {
        return problemSpecificContent;
    }

    public void setProblemSpecificContent(String problemSpecificContent) {
        this.problemSpecificContent = problemSpecificContent;
    }

    public String getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(String problemTypes) {
        this.problemTypes = problemTypes;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getProblemEmergencyDegree() {
        return problemEmergencyDegree;
    }

    public void setProblemEmergencyDegree(String problemEmergencyDegree) {
        this.problemEmergencyDegree = problemEmergencyDegree;
    }

    public String getProblemProcessingTime() {
        return problemProcessingTime;
    }

    public void setProblemProcessingTime(String problemProcessingTime) {
        this.problemProcessingTime = problemProcessingTime;
    }

    public String getAffectedDepartment() {
        return affectedDepartment;
    }

    public void setAffectedDepartment(String affectedDepartment) {
        this.affectedDepartment = affectedDepartment;
    }
}