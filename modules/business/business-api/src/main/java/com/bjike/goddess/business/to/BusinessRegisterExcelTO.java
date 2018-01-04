package com.bjike.goddess.business.to;

import com.bjike.goddess.business.enums.Status;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 工商注册导入to
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册导入to ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessRegisterExcelTO extends BaseTO {

    /**
     * 注册公司名称
     */
    @NotBlank(message = "注册公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String registerCompanyName;

    /**
     * 注册号/统一社会信用代码
     */
    @NotBlank(message = "注册号/统一社会信用代码不能为空",groups = {ADD.class, EDIT.class})
    private String registerNum;

    /**
     * 经营期限
     */
    private String operationPeriod;

    /**
     * 注册类型
     */
    @NotBlank(message = "注册类型不能为空",groups = {ADD.class, EDIT.class})
    private String registerType;

    /**
     * 注册资本
     */
    @NotBlank(message = "注册资本不能为空",groups = {ADD.class, EDIT.class})
    private String registerCapital;

    /**
     * 经营范围
     */
    @NotBlank(message = "经营范围不能为空",groups = {ADD.class, EDIT.class})
    private String operationScope;

    /**
     * 法人
     */
    @NotBlank(message = "法人不能为空",groups = {ADD.class, EDIT.class})
    private String legalPerson;


    /**
     * 股东:占股比例
     */
    @NotBlank(message = "股东占股比例不能为空",groups = {ADD.class, EDIT.class})
    private String shareholders;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空",groups = {ADD.class, EDIT.class})
    private String address;

    /**
     * 相关资料（名称）
     */
    @NotBlank(message = "相关资料（名称）不能为空",groups = {ADD.class, EDIT.class})
    private String cursorAdapter;

    /**
     * 备注
     */
    private String remark;
    /**
     * 成立日期
     */
    @NotBlank(message = "成立日期不能为空",groups = {ADD.class, EDIT.class})
    private String setUpDate;

    /**
     * 许可经营范围
     */
    private String permittedBusiness;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空",groups = {ADD.class, EDIT.class})
    private Status status;

    /**
     * 核发日期
     */
    @NotBlank(message = "核发日期不能为空",groups = {ADD.class, EDIT.class})
    private String issuingDate;

    /**
     * 登记机关
     */
    @NotBlank(message = "登记机关不能为空",groups = {ADD.class, EDIT.class})
    private String registrationAuthor;

    /**
     * 组织结构成员名称
     */
    @NotBlank(message = "组织结构成员名称不能为空",groups = {ADD.class, EDIT.class})
    private String organizationNemName;

    /**
     * 职务
     */
    @NotBlank(message = "职务不能为空",groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 职务产生方式
     */
    private String positionWay;

    /**
     * 是否法定代表人
     */
    @NotNull(message = "是否法定代表人不能为空",groups = {ADD.class, EDIT.class})
    private Boolean representativeLegal;

    /**
     * 网址
     */
    @NotBlank(message = "网址不能为空",groups = {ADD.class, EDIT.class})
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

    public String getOperationPeriod() {
        return operationPeriod;
    }

    public void setOperationPeriod(String operationPeriod) {
        this.operationPeriod = operationPeriod;
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

    public String getShareholders() {
        return shareholders;
    }

    public void setShareholders(String shareholders) {
        this.shareholders = shareholders;
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