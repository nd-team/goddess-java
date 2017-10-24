package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

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
    public interface EXPORT{}
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
     * 总包单位名称
     */
    private String majorCompany;
    /**
     * 所属项目组
     */
    private String projectGroup;
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
    @NotNull(message = "地区不能为空",groups = {BusinessContractDTO.EXPORT.class})
    private String[] areas;

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
}