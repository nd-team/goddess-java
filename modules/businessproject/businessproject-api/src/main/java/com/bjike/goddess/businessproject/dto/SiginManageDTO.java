package com.bjike.goddess.businessproject.dto;

import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务项目合同签订与立项管理数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.292 ]
 * @Description: [ 商务项目合同签订与立项管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SiginManageDTO extends BaseDTO {

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    private String businessSubject;

    /**
     * 合作方式
     */
    private BusinessCooperate businessCooperate;

    /**
     * 甲方公司名称
     */
    private String firstCompany;

    /**
     * 乙方公司名称
     */
    private String secondCompany;


    /**
     * 地区
     */
    private String area;

    /**
     * 合同属性
     */
    private ContractProperty contractProperty;

    /**
     * 立项情况
     */
    private String makeProject;

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public String getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(String makeProject) {
        this.makeProject = makeProject;
    }
}