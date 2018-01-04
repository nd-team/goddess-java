package com.bjike.goddess.business.bo;

import com.bjike.goddess.business.enums.Status;
import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
import java.util.List;

/**
 * 工商注册业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessRegisterBO extends BaseBO {

    /**
     * 注册公司名称
     */
    private String registerCompanyName;

    /**
     * 注册号/统一社会信用代码
     */
    private String registerNum;

    /**
     * 经营期限开始
     */
    private String startOperationPeriod;
    /**
     * 经营期限结束
     */
    private String endOperationPeriod;

    /**
     * 注册类型
     */
    private String registerType;

    /**
     * 注册资本
     */
    private String registerCapital;

    /**
     * 经营范围
     */
    private String operationScope;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 股东:股权比例
     */
    private List<ShareholdersBO> shareholdersBOS;

    /**
     * 地址
     */
    private String address;

    /**
     * 相关资料（名称）
     */
    private String cursorAdapter;

    /**
     * 备注
     */
    private String remark;

    /**
     * 成立日期
     */
    private String setUpDate;

    /**
     * 许可经营范围
     */
    private String permittedBusiness;

    /**
     * 状态
     */
    private Status status;

    /**
     * 核发日期
     */
    private String issuingDate;

    /**
     * 登记机关
     */
    private String registrationAuthor;

    /**
     * 组织结构成员名称
     */
    private String organizationNemName;

    /**
     * 职务
     */
    private String position;

    /**
     * 职务产生方式
     */
    private String positionWay;

    /**
     * 是否法定代表人
     */
    private Boolean representativeLegal;

    /**
     * 网址
     */
    private String url;


    public String getRegisterCompanyName() {
        return registerCompanyName;
    }

    public void setRegisterCompanyName(String registerCompanyName) {
        this.registerCompanyName = registerCompanyName;
    }

    public String getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
    }

    public String getStartOperationPeriod() {
        return startOperationPeriod;
    }

    public void setStartOperationPeriod(String startOperationPeriod) {
        this.startOperationPeriod = startOperationPeriod;
    }

    public String getEndOperationPeriod() {
        return endOperationPeriod;
    }

    public void setEndOperationPeriod(String endOperationPeriod) {
        this.endOperationPeriod = endOperationPeriod;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getOperationScope() {
        return operationScope;
    }

    public void setOperationScope(String operationScope) {
        this.operationScope = operationScope;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public List<ShareholdersBO> getShareholdersBOS() {
        return shareholdersBOS;
    }

    public void setShareholdersBOS(List<ShareholdersBO> shareholdersBOS) {
        this.shareholdersBOS = shareholdersBOS;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCursorAdapter() {
        return cursorAdapter;
    }

    public void setCursorAdapter(String cursorAdapter) {
        this.cursorAdapter = cursorAdapter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
    }

    public String getPermittedBusiness() {
        return permittedBusiness;
    }

    public void setPermittedBusiness(String permittedBusiness) {
        this.permittedBusiness = permittedBusiness;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getRegistrationAuthor() {
        return registrationAuthor;
    }

    public void setRegistrationAuthor(String registrationAuthor) {
        this.registrationAuthor = registrationAuthor;
    }

    public String getOrganizationNemName() {
        return organizationNemName;
    }

    public void setOrganizationNemName(String organizationNemName) {
        this.organizationNemName = organizationNemName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionWay() {
        return positionWay;
    }

    public void setPositionWay(String positionWay) {
        this.positionWay = positionWay;
    }

    public Boolean getRepresentativeLegal() {
        return representativeLegal;
    }

    public void setRepresentativeLegal(Boolean representativeLegal) {
        this.representativeLegal = representativeLegal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}