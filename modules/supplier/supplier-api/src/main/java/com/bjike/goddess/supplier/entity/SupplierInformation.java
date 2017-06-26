package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 供应商基本信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.039 ]
 * @Description: [ 供应商基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_supplier_information")
public class SupplierInformation extends BaseEntity {

    /**
     * 供应商编号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(30)   COMMENT '供应商编号'")
    private String serialNumber;

    /**
     * 供应商地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(20)   COMMENT '供应商地区'")
    private String area;

    /**
     * 供应商名称
     */
    @Column(name = "supplierName", nullable = false, columnDefinition = "VARCHAR(50)   COMMENT '供应商名称'")
    private String supplierName;

    /**
     * 供应商类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(30)   COMMENT '供应商类型'")
    private String type;

    /**
     * 经营范围
     */
    @Column(name = "scope", nullable = false, columnDefinition = "VARCHAR(100)   COMMENT '经营范围'")
    private String scope;

    /**
     * 主要产品和服务
     */
    @Column(name = "product", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主要产品和服务'")
    private String product;

    /**
     * 业务联系人
     */
    @Column(name = "contacts", columnDefinition = "VARCHAR(50)   COMMENT '业务联系人'")
    private String contacts;

    /**
     * 职务
     */
    @Column(name = "duties", columnDefinition = "VARCHAR(50)   COMMENT '职务'")
    private String duties;

    /**
     * 联系电话
     */
    @Column(name = "telephone", columnDefinition = "VARCHAR(20)   COMMENT '联系电话'")
    private String telephone;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(100)   COMMENT '邮箱'")
    private String email;

    /**
     * 传真
     */
    @Column(name = "fax", columnDefinition = "VARCHAR(100)   COMMENT '传真'")
    private String fax;

    /**
     * 信息完成度
     */
    @Column(name = "execution", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '信息完成度'")
    private Double execution;

    /**
     * 营业执照附件
     */
    @Column(name = "is_enclosure", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '营业执照附件'")
    private Boolean enclosure;

    /**
     * 公司网址
     */
    @Column(name = "url", columnDefinition = "VARCHAR(100)   COMMENT '公司网址'")
    private String url;

    /**
     * 法定代表人
     */
    @Column(name = "representative", columnDefinition = "VARCHAR(50)   COMMENT '法定代表人'")
    private String representative;

    /**
     * 工商注册号
     */
    @Column(name = "registrationNumber", columnDefinition = "VARCHAR(100)   COMMENT '工商注册号'")
    private String registrationNumber;

    /**
     * 税务登记证编号
     */
    @Column(name = "taxNumber", columnDefinition = "VARCHAR(80)   COMMENT '税务登记证编号'")
    private String taxNumber;

    /**
     * 组织机构代码
     */
    @Column(name = "institutionCode", columnDefinition = "VARCHAR(255)   COMMENT '组织机构代码'")
    private String institutionCode;

    /**
     * 注册资本
     */
    @Column(name = "registeredCapital", columnDefinition = "INT(11)   COMMENT '注册资本'")
    private Integer registeredCapital;

    /**
     * 成立时间
     */
    @Column(name = "establishTime", columnDefinition = "DATE   COMMENT '成立时间'")
    private LocalDate establishTime;

    /**
     * 地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 邮编
     */
    @Column(name = "zipCode", columnDefinition = "VARCHAR(255)   COMMENT '邮编'")
    private String zipCode;

    /**
     * 开户银行
     */
    @Column(name = "bank", columnDefinition = "VARCHAR(50)   COMMENT '开户银行'")
    private String bank;

    /**
     * 账号
     */
    @Column(name = "account", columnDefinition = "VARCHAR(50)   COMMENT '账号'")
    private String account;

    /**
     * 信用等级
     */
    @Column(name = "credit", columnDefinition = "VARCHAR(50)   COMMENT '信用等级'")
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

    public Boolean isEnclosure() {
        return enclosure;
    }

    public void isEnclosure(Boolean enclosure) {
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

    public LocalDate getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(LocalDate establishTime) {
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