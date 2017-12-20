package com.bjike.goddess.supplier.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.supplier.bo.AwardsBO;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.bo.EnterpriseQualificaBO;
import com.bjike.goddess.supplier.bo.ProvideProductBO;

import java.time.LocalDate;
import java.util.List;

/**
 * 供应商信息管理业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 03:55 ]
 * @Description: [ 供应商信息管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierInfoRegistraDataVO{

    /**
     * id
     */
    private String id;
    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商类型
     */
    private String supplierType;

    /**
     * 工商注册号
     */
    private String bussRegistraName;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 公司类型
     */
    private String companyType;

    /**
     * 地址
     */
    private String area;

    /**
     * 公司网址
     */
    private String companyWeb;

    /**
     * 法定代表人
     */
    private String legalRepresen;

    /**
     * 税务登记证编号
     */
    private String certifiNum;

    /**
     * 注册资本
     */
    private String registraCapital;

    /**
     * 成立时间
     */
    private LocalDate establishDate;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 经营范围
     */
    private String bussScope;

    /**
     * 主要产品/服务
     */
    private String mainProtducts;

    /**
     * 开户银行
     */
    private String bankDeposit;

    /**
     * 账号
     */
    private String accountNum;

    /**
     * 信用等级
     */
    private String creditRating;

    /**
     * 企业资质
     */
    private List<EnterpriseQualificaBO> enterpriseQualificaBOList;

    /**
     * 获奖情况
     */
    private List<AwardsBO> awardsBOList;
    /**
     * 联络情况
     */
    private List<ContactSituationBO> contactSituationBOList;
    /**
     * 针对拟为我公司提供产品
     */
    private List<ProvideProductBO> provideProductBOList;


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

    public List<EnterpriseQualificaBO> getEnterpriseQualificaBOList() {
        return enterpriseQualificaBOList;
    }

    public void setEnterpriseQualificaBOList(List<EnterpriseQualificaBO> enterpriseQualificaBOList) {
        this.enterpriseQualificaBOList = enterpriseQualificaBOList;
    }

    public List<AwardsBO> getAwardsBOList() {
        return awardsBOList;
    }

    public void setAwardsBOList(List<AwardsBO> awardsBOList) {
        this.awardsBOList = awardsBOList;
    }

    public List<ContactSituationBO> getContactSituationBOList() {
        return contactSituationBOList;
    }

    public void setContactSituationBOList(List<ContactSituationBO> contactSituationBOList) {
        this.contactSituationBOList = contactSituationBOList;
    }

    public List<ProvideProductBO> getProvideProductBOList() {
        return provideProductBOList;
    }

    public void setProvideProductBOList(List<ProvideProductBO> provideProductBOList) {
        this.provideProductBOList = provideProductBOList;
    }
}