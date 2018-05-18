package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.businessproject.enums.TaskContract;
import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务合同
 * @Author: [caiwenxian]
 * @Date: [2018-02-10 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessContractDTOV2 extends BaseDTO{
    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 单次合同名称
     */
    private String singleContractName;

    /**
     * 签订时间
     */
    private String signedTime;

    /**
     * 测算分类
     */
    private String measureClassify;

    /**
     * 地区
     */
    private String area;

    /**
     * 业务类型
     */
    private String businessType;

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
     * 所属项目组
     */
    private String projectGroup;

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSingleContractName() {
        return singleContractName;
    }

    public void setSingleContractName(String singleContractName) {
        this.singleContractName = singleContractName;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public BusinessContractDTOV2() {
    }

    public BusinessContractDTOV2(String signedTime, String measureClassify, String area, String businessType, String businessSubject, MakeContract contractStatus, TaskContract taskStatus) {
        this.signedTime = signedTime;
        this.measureClassify = measureClassify;
        this.area = area;
        this.businessType = businessType;
        this.businessSubject = businessSubject;
        this.contractStatus = contractStatus;
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "BusinessContractDTOV2{" +
                "signedTime='" + signedTime + '\'' +
                ", area='" + area + '\'' +
                ", businessType='" + businessType + '\'' +
                ", businessSubject='" + businessSubject + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
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

   /* public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }*/
}
