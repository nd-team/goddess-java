package com.bjike.goddess.business.excel;

import com.bjike.goddess.business.enums.Status;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 工商注册导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessRegisterImprotTemple extends BaseBO {

    /**
     * 注册公司名称
     */
    @ExcelHeader(name = "注册公司名称",notNull = true)
    private String registerCompanyName;

    /**
     * 注册号/统一社会信用代码
     */
    @ExcelHeader(name = "注册号/统一社会信用代码",notNull = true)
    private String registerNum;

    /**
     * 成立日期
     */
    @ExcelHeader(name = "成立日期",notNull = true)
    private String setUpDate;

    /**
     * 经营期限
     */
    @ExcelHeader(name = "经营期限",notNull = true)
    private String operationPeriod;

    /**
     * 注册类型
     */
    @ExcelHeader(name = "注册类型",notNull = true)
    private String registerType;

    /**
     * 注册资本
     */
    @ExcelHeader(name = "注册资本",notNull = true)
    private String registerCapital;

    /**
     * 经营范围
     */
    @ExcelHeader(name = "经营范围",notNull = true)
    private String operationScope;


    /**
     * 许可经营范围
     */
    @ExcelHeader(name = "许可经营范围")
    private String permittedBusiness;
    /**
     * 法人
     */
    @ExcelHeader(name = "法人",notNull = true)
    private String legalPerson;

    /**
     * 股东:股权比例
     */
    @ExcelHeader(name = "股东:股权比例",notNull = true)
    private String shareholders;


    /**
     * 地址
     */
    @ExcelHeader(name = "地址",notNull = true)
    private String address;



    /**
     * 状态
     */
    @ExcelHeader(name = "状态",notNull = true)
    private String status;

    /**
     * 核发日期
     */
    @ExcelHeader(name = "核发日期",notNull = true)
    private String issuingDate;

    /**
     * 登记机关
     */
    @ExcelHeader(name = "登记机关",notNull = true)
    private String registrationAuthor;

    /**
     * 组织结构成员名称
     */
    @ExcelHeader(name = "组织结构成员名称",notNull = true)
    private String organizationNemName;

    /**
     * 职务
     */
    @ExcelHeader(name = "职务",notNull = true)
    private String position;

    /**
     * 职务产生方式
     */
    @ExcelHeader(name = "职务产生方式")
    private String positionWay;

    /**
     * 是否法定代表人
     */
    @ExcelHeader(name = "是否法定代表人",notNull = true)
    private String representativeLegal;

    /**
     * 网址
     */
    @ExcelHeader(name = "网址",notNull = true)
    private String url;

    /**
     * 相关资料（名称）
     */
    @ExcelHeader(name = "相关资料（名称）",notNull = true)
    private String cursorAdapter;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

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

    public String getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
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

    public String getPermittedBusiness() {
        return permittedBusiness;
    }

    public void setPermittedBusiness(String permittedBusiness) {
        this.permittedBusiness = permittedBusiness;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getRepresentativeLegal() {
        return representativeLegal;
    }

    public void setRepresentativeLegal(String representativeLegal) {
        this.representativeLegal = representativeLegal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}