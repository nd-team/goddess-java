package com.bjike.goddess.projectissuehandle.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectissuehandle.enums.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目执行中的问题受理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemAcceptTO extends BaseTO {
    /**
     * 项目问题编号
     */
    private String projectNum;

    /**
     * 年份
     */
    @NotBlank(message = "年份不能为空",groups = {ADD.class, EDIT.class})
    private String year;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 合同外部编号
     */
    @NotBlank(message = "合同外部编号不能为空",groups = {ADD.class, EDIT.class})
    private String externalContractNum;

    /**
     * 合同外部项目名称
     */
    @NotBlank(message = "合同外部项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String externalContractProjectName;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String internalProjectName;

    /**
     * 内部项目编号
     */
    @NotBlank(message = "内部项目编号不能为空",groups = {ADD.class, EDIT.class})
    private String internalNum;

    /**
     * 工程类型
     */
    @NotBlank(message = "工程类型不能为空",groups = {ADD.class, EDIT.class})
    private String projectType;

    /**
     * 通知方式
     */
    @NotNull(message = "通知方式不能为空",groups = {ADD.class, EDIT.class})
    private NoticeWay noticeWay;

    /**
     * 问题具体内容
     */
    @NotBlank(message = "问题具体内容不能为空",groups = {ADD.class, EDIT.class})
    private String problemSpecificContent;

    /**
     * 问题类型
     */
    @NotNull(message = "问题类型不能为空",groups = {ADD.class, EDIT.class})
    private ProblemTypes problemTypes;

    /**
     * 解决方式
     */
    @NotBlank(message = "解决方式不能为空",groups = {ADD.class, EDIT.class})
    private String solution;

    /**
     * 问题处理时间
     */
    @NotNull(message = "问题处理时间不能为空",groups = {ADD.class, EDIT.class})
    private ProblemProcessingTime problemProcessingTime;

    /**
     * 受影响部门
     */
    @NotNull(message = "受影响部门不能为空",groups = {ADD.class, EDIT.class})
    private AffectedDepartment affectedDepartment;
    /**
     * 问题紧急程度
     */
    private String problemEmergencyDegree;


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

    public NoticeWay getNoticeWay() {
        return noticeWay;
    }

    public void setNoticeWay(NoticeWay noticeWay) {
        this.noticeWay = noticeWay;
    }

    public String getProblemSpecificContent() {
        return problemSpecificContent;
    }

    public void setProblemSpecificContent(String problemSpecificContent) {
        this.problemSpecificContent = problemSpecificContent;
    }

    public ProblemTypes getProblemTypes() {
        return problemTypes;
    }

    public void setProblemTypes(ProblemTypes problemTypes) {
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

    public ProblemProcessingTime getProblemProcessingTime() {
        return problemProcessingTime;
    }

    public void setProblemProcessingTime(ProblemProcessingTime problemProcessingTime) {
        this.problemProcessingTime = problemProcessingTime;
    }

    public AffectedDepartment getAffectedDepartment() {
        return affectedDepartment;
    }

    public void setAffectedDepartment(AffectedDepartment affectedDepartment) {
        this.affectedDepartment = affectedDepartment;
    }

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