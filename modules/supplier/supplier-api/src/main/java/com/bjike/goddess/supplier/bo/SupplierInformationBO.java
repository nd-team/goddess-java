package com.bjike.goddess.supplier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 供应商基本信息业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.044 ]
 * @Description: [ 供应商基本信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierInformationBO extends BaseBO {

    /**
     * 供应商编号
     */
    private String serialNumber;

    /**
     * 供应商地区
     */
    private String area;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商类型
     */
    private String type;

    /**
     * 经营范围
     */
    private String scope;

    /**
     * 主要产品和服务
     */
    private String product;

    /**
     * 业务联系人
     */
    private String contacts;

    /**
     * 职务
     */
    private String duties;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 信息完成度
     */
    private Double execution;

    /**
     * 营业执照附件
     */
    private Boolean enclosure;

    /**
     * 公司网址
     */
    private String url;

    /**
     * 法定代表人
     */
    private String representative;

    /**
     * 工商注册号
     */
    private String registrationNumber;

    /**
     * 税务登记证编号
     */
    private String taxNumber;

    /**
     * 组织机构代码
     */
    private String institutionCode;

    /**
     * 注册资本
     */
    private Integer registeredCapital;

    /**
     * 成立时间
     */
    private String establishTime;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 账号
     */
    private String account;

    /**
     * 信用等级
     */
    private String credit;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Double getExecution() {
        return execution;
    }

    public void setExecution(Double execution) {
        this.execution = execution;
    }

    public Boolean getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Boolean enclosure) {
        this.enclosure = enclosure;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public Integer getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Integer registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}