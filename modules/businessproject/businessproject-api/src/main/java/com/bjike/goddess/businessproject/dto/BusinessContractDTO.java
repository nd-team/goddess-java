package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.TaskContract;
import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务项目合同数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessContractDTO extends BaseDTO {
    /**
     * 总包单位名称
     */
    private String majorCompany;
    /**
     * 所属项目组
     */
    private String projectGroup;

    /**
     * 单次合同名称
     */
    private String singleContractName;
    /**
     * 签订时间
     */
    private String signedTime;

    /**
     * 地区
     */
    private String area;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 内部项目名称
     */
    private String innerProject;
    /**
     * 项目负责人
     */
    private String projectCharge;
    /**
     * 是否闭单
     */
    private Boolean closeSingle;
    /**
     * 地区
     */
    private String[] areas;

    /**
     * 内部合同编号
     */
    private String internalContractNum;


    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 合同状态
     */
    private MakeContract contractStatus;

    /**
     * 派工状态
     */
    private TaskContract taskStatus;

    /**
     * 测算分类
     */
    private String measureClassify;

    public String getSingleContractName() {
        return singleContractName;
    }

    public void setSingleContractName(String singleContractName) {
        this.singleContractName = singleContractName;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public MakeContract getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(MakeContract contractStatus) {
        this.contractStatus = contractStatus;
    }

    public TaskContract getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskContract taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMeasureClassify() {
        return measureClassify;
    }

    public void setMeasureClassify(String measureClassify) {
        this.measureClassify = measureClassify;
    }

    public String getInternalContractNum() {
        return internalContractNum;
    }

    public void setInternalContractNum(String internalContractNum) {
        this.internalContractNum = internalContractNum;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public Boolean getCloseSingle() {
        return closeSingle;
    }

    public void setCloseSingle(Boolean closeSingle) {
        this.closeSingle = closeSingle;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

   /* public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }*/


}