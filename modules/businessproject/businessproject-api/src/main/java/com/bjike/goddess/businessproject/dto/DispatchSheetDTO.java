package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务项目派工单信息管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DispatchSheetDTO extends BaseDTO {

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;


    /**
     * 合作方式
     */
    private String businessCooperate;

    /**
     * 总包单位名称
     */
    private String majorCompany;

    /**
     * 分包单位名称
     */
    private String subCompany;


    /**
     * 地区
     */
    private String area;

    /**
     * 派工单名称
     */
    private String dispatchProject;

    /**
     * 派工单编号
     */
    private String dispatchNum;

    /**
     * 内部项目名称数组
     */
    private String[] innerProjects;

    public String[] getInnerProjects() {
        return innerProjects;
    }

    public void setInnerProjects(String[] innerProjects) {
        this.innerProjects = innerProjects;
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

    public String getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(String businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDispatchProject() {
        return dispatchProject;
    }

    public void setDispatchProject(String dispatchProject) {
        this.dispatchProject = dispatchProject;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }
}