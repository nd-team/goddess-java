package com.bjike.goddess.supplier.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 供应商信息管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 03:55 ]
 * @Description: [ 供应商信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "supplier_supplierinforegistra")
public class SupplierInfoRegistra extends BaseEntity {

    /**
     * 供应商名称
     */
    @Column(name = "supplierName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商名称'")
    private String supplierName;

    /**
     * 供应商类型
     */
    @Column(name = "supplierType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '供应商类型'")
    private String supplierType;

    /**
     * 工商注册号
     */
    @Column(name = "bussRegistraName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工商注册号'")
    private String bussRegistraName;

    /**
     * 组织机构代码
     */
    @Column(name = "organizationCode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '组织机构代码'")
    private String organizationCode;

    /**
     * 公司类型
     */
    @Column(name = "companyType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司类型'")
    private String companyType;

    /**
     * 地址
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String area;

    /**
     * 公司网址
     */
    @Column(name = "companyWeb", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司网址'")
    private String companyWeb;

    /**
     * 法定代表人
     */
    @Column(name = "legalRepresen", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '法定代表人'")
    private String legalRepresen;

    /**
     * 税务登记证编号
     */
    @Column(name = "certifiNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税务登记证编号'")
    private String certifiNum;

    /**
     * 注册资本
     */
    @Column(name = "registraCapital", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注册资本'")
    private String registraCapital;

    /**
     * 成立时间
     */
    @Column(name = "establishDate", nullable = false, columnDefinition = "DATE   COMMENT '成立时间'")
    private LocalDate establishDate;

    /**
     * 邮编
     */
    @Column(name = "postcode", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '邮编'")
    private String postcode;

    /**
     * 经营范围
     */
    @Column(name = "bussScope", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经营范围'")
    private String bussScope;

    /**
     * 主要产品/服务
     */
    @Column(name = "mainProtducts", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主要产品/服务'")
    private String mainProtducts;

    /**
     * 开户银行
     */
    @Column(name = "bankDeposit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开户银行'")
    private String bankDeposit;

    /**
     * 账号
     */
    @Column(name = "accountNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '账号'")
    private String accountNum;

    /**
     * 信用等级
     */
    @Column(name = "creditRating", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '信用等级'")
    private String creditRating;


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getBussRegistraName() {
        return bussRegistraName;
    }

    public void setBussRegistraName(String bussRegistraName) {
        this.bussRegistraName = bussRegistraName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getLegalRepresen() {
        return legalRepresen;
    }

    public void setLegalRepresen(String legalRepresen) {
        this.legalRepresen = legalRepresen;
    }

    public String getCertifiNum() {
        return certifiNum;
    }

    public void setCertifiNum(String certifiNum) {
        this.certifiNum = certifiNum;
    }

    public String getRegistraCapital() {
        return registraCapital;
    }

    public void setRegistraCapital(String registraCapital) {
        this.registraCapital = registraCapital;
    }

    public LocalDate getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(LocalDate establishDate) {
        this.establishDate = establishDate;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getBussScope() {
        return bussScope;
    }

    public void setBussScope(String bussScope) {
        this.bussScope = bussScope;
    }

    public String getMainProtducts() {
        return mainProtducts;
    }

    public void setMainProtducts(String mainProtducts) {
        this.mainProtducts = mainProtducts;
    }

    public String getBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(String bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }
}