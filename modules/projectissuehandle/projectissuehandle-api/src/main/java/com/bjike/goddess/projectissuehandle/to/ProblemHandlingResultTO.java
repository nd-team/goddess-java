package com.bjike.goddess.projectissuehandle.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.enums.ProblemObject;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingResult;
import com.bjike.goddess.projectissuehandle.enums.ProblemRelevantDepartment;
import com.bjike.goddess.projectissuehandle.enums.ProjectType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 确认问题处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemHandlingResultTO extends BaseTO {
    /**
     * 项目问题编号
     */
    private String projectNum;
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
    @NotBlank(message = "问题受理时间不能为空",groups = {ADD.class, EDIT.class})
    private String problemAcceptTime;

    /**
     * 问题具体情况
     */
    @NotBlank(message = "问题具体情况不能为空",groups = {ADD.class, EDIT.class})
    private String problemSpecificSituation;

    /**
     * 问题对象
     */
    @NotBlank(message = "问题对象不能为空", groups = {ADD.class, EDIT.class})
    private String problemObject;

    /**
     * 问题责任人员
     */
    @NotBlank(message = "问题责任人员不能为空",groups = {ADD.class, EDIT.class})
    private String problemResponsible;

    /**
     * 问题处理人员
     */
    @NotBlank(message = "问题处理人员不能为空",groups = {ADD.class, EDIT.class})
    private String problemHandler;

    /**
     * 问题相关部门
     */
    @NotNull(message = "问题相关部门不能为空",groups = {ADD.class, EDIT.class})
    private ProblemRelevantDepartment problemRelevantDepartment;

    /**
     * 问题发生时间
     */
    @NotBlank(message = "问题发生时间不能为空",groups = {ADD.class, EDIT.class})
    private String problemOccurrenceTime;

    /**
     * 问题发生地点
     */
    @NotBlank(message = "问题发生地点不能为空",groups = {ADD.class, EDIT.class})
    private String problemOccurrencePlace;

    /**
     * 问题解决时间
     */
    @NotBlank(message = "问题解决时间不能为空",groups = {ADD.class, EDIT.class})
    private String problemSolveTime;

    /**
     * 问题处理结果
     */
    @NotNull(message = "问题处理结果不能为空",groups = {ADD.class, EDIT.class})
    private ProblemProcessingResult problemProcessingResult;

    /**
     * 问题总结
     */
    private String problemConclusion;
    /**
     * 项目执行中的问题受理
     */
    //private String problemAcceptId;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

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

//    public String getProblemAcceptId() {
//        return problemAcceptId;
//    }
//
//    public void setProblemAcceptId(String problemAcceptId) {
//        this.problemAcceptId = problemAcceptId;
//    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}