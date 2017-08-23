package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 塔工意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_towerinsure")
public class TowerInsure extends BaseEntity {

    /**
     * 合同号
     */
    @Column(name = "contractNum", columnDefinition = "VARCHAR(255)   COMMENT '合同号'")
    private String contractNum;

    /**
     * 投保单号
     */
    @Column(name = "insureNumber", columnDefinition = "VARCHAR(255)   COMMENT '投保单号'")
    private String insureNumber;

    /**
     * 投保人
     */
    @Column(name = "insurer", columnDefinition = "VARCHAR(255)   COMMENT '投保人'")
    private String insurer;

    /**
     * 投保人客户号
     */
    @Column(name = "insureCusNum", columnDefinition = "VARCHAR(255)   COMMENT '投保人客户号'")
    private String insureCusNum;

    /**
     * 被投保人
     */
    @Column(name = "insureByPerson", columnDefinition = "VARCHAR(255)   COMMENT '被投保人'")
    private String insureByPerson;

    /**
     * 被投保人客户号
     */
    @Column(name = "insureByCusNum", columnDefinition = "VARCHAR(255)   COMMENT '被投保人客户号'")
    private String insureByCusNum;

    /**
     * 合同生效日期
     */
    @Column(name = "effectDate", columnDefinition = "DATE  COMMENT '合同生效日期'")
    private LocalDate effectDate;

    /**
     * 合同期满日期
     */
    @Column(name = "expireDate", columnDefinition = "DATE   COMMENT '合同期满日期'")
    private LocalDate expireDate;

    /**
     * 保险期间
     */
    @Column(name = "insurePeriod", columnDefinition = "VARCHAR(255)   COMMENT '保险期间'")
    private String insurePeriod;

    /**
     * 保险费合计
     */
    @Column(name = "insureTotalFee", columnDefinition = "DECIMAL(10,2)   COMMENT '保险费合计'")
    private Double insureTotalFee;

    /**
     * 币种
     */
    @Column(name = "billType", columnDefinition = "VARCHAR(255)   COMMENT '币种'")
    private String billType;

    /**
     * 缴费方式
     */
    @Column(name = "payWay", columnDefinition = "VARCHAR(255)   COMMENT '缴费方式'")
    private String payWay;

    /**
     * 账户形式
     */
    @Column(name = "accountType", columnDefinition = "VARCHAR(255)   COMMENT '账户形式'")
    private String accountType;

    /**
     * 开户银行
     */
    @Column(name = "bank", columnDefinition = "VARCHAR(255)   COMMENT '开户银行'")
    private String bank;

    /**
     * 帐号
     */
    @Column(name = "account", columnDefinition = "VARCHAR(255)   COMMENT '帐号'")
    private String account;

    /**
     * 账户所有人姓名
     */
    @Column(name = "accountOwner", columnDefinition = "VARCHAR(255)   COMMENT '账户所有人姓名'")
    private String accountOwner;

    /**
     * 险种代码
     */
    @Column(name = "insureCode", columnDefinition = "VARCHAR(255)   COMMENT '险种代码'")
    private String insureCode;

    /**
     * 险种名称
     */
    @Column(name = "insureName", columnDefinition = "VARCHAR(255)   COMMENT '险种名称'")
    private String insureName;

    /**
     * 保险责任
     */
    @Column(name = "insureRespon", columnDefinition = "VARCHAR(255)   COMMENT '保险责任'")
    private String insureRespon;

    /**
     * 保险金额
     */
    @Column(name = "insureMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '保险金额'")
    private Double insureMoney;

    /**
     * 保险费
     */
    @Column(name = "insureFee", columnDefinition = "DECIMAL(10,2)   COMMENT '保险费'")
    private Double insureFee;

    /**
     * 投保人姓名
     */
    @Column(name = "personName", columnDefinition = "VARCHAR(255)   COMMENT '投保人姓名'")
    private String personName;

    /**
     * 投保人性别（男/女）
     */
    @Column(name = "personSex", columnDefinition = "VARCHAR(255)   COMMENT '投保人性别（男/女）'")
    private String personSex;

    /**
     * 投保人出生日期
     */
    @Column(name = "personBorn", columnDefinition = "DATE  COMMENT '投保人出生日期'")
    private LocalDate personBorn;

    /**
     * 投保人国籍
     */
    @Column(name = "personCountry", columnDefinition = "VARCHAR(255)   COMMENT '投保人国籍'")
    private String personCountry;

    /**
     * 投保人证件类型
     */
    @Column(name = "personFileType", columnDefinition = "VARCHAR(255)   COMMENT '投保人证件类型'")
    private String personFileType;

    /**
     * 投保人证件号
     */
    @Column(name = "personFileNum", columnDefinition = "VARCHAR(255)   COMMENT '投保人证件号'")
    private String personFileNum;

    /**
     * 投保人证件有效期限
     */
    @Column(name = "personFileEffTerm", columnDefinition = "VARCHAR(255)   COMMENT '投保人证件有效期限'")
    private String personFileEffTerm;

    /**
     * 投保人职业
     */
    @Column(name = "personJob", columnDefinition = "VARCHAR(255)   COMMENT '投保人职业'")
    private String personJob;

    /**
     * 投保人职业代码
     */
    @Column(name = "personJobCode", columnDefinition = "VARCHAR(255)   COMMENT '投保人职业代码'")
    private String personJobCode;

    /**
     * 投保人通讯地址
     */
    @Column(name = "personAddr", columnDefinition = "VARCHAR(255)   COMMENT '投保人通讯地址'")
    private String personAddr;

    /**
     * 投保人联系方式
     */
    @Column(name = "personContact", columnDefinition = "VARCHAR(255)   COMMENT '投保人联系方式'")
    private String personContact;

    /**
     * 被投保人是投保人的
     */
    @Column(name = "personByRelation", columnDefinition = "VARCHAR(255)   COMMENT '被投保人是投保人的'")
    private String personByRelation;

    /**
     * 被投保人姓名
     */
    @Column(name = "personByName", columnDefinition = "VARCHAR(255)   COMMENT '被投保人姓名'")
    private String personByName;

    /**
     * 被投保人性别（男/女）
     */
    @Column(name = "personBySex", columnDefinition = "VARCHAR(255)   COMMENT '被投保人性别（男/女）'")
    private String personBySex;

    /**
     * 被投保人出生日期
     */
    @Column(name = "personByBorn", columnDefinition = "DATE COMMENT '被投保人出生日期'")
    private LocalDate personByBorn;

    /**
     * 被投保人国籍
     */
    @Column(name = "personByCountry", columnDefinition = "VARCHAR(255)   COMMENT '被投保人国籍'")
    private String personByCountry;

    /**
     * 被投保人证件类型
     */
    @Column(name = "personByFileType", columnDefinition = "VARCHAR(255)   COMMENT '被投保人证件类型'")
    private String personByFileType;

    /**
     * 被投保人证件号
     */
    @Column(name = "personByFileNum", columnDefinition = "VARCHAR(255)   COMMENT '被投保人证件号'")
    private String personByFileNum;

    /**
     * 被投保人证件有效期限
     */
    @Column(name = "personByFileEffTerm", columnDefinition = "VARCHAR(255)   COMMENT '被投保人证件有效期限'")
    private String personByFileEffTerm;

    /**
     * 被投保人职业
     */
    @Column(name = "personByJob", columnDefinition = "VARCHAR(255)   COMMENT '被投保人职业'")
    private String personByJob;

    /**
     * 被投保人职业代码
     */
    @Column(name = "personByJobCode", columnDefinition = "VARCHAR(255)   COMMENT '被投保人职业代码'")
    private String personByJobCode;

    /**
     * 被投保人通讯地址
     */
    @Column(name = "personByAddr", columnDefinition = "VARCHAR(255)   COMMENT '被投保人通讯地址'")
    private String personByAddr;

    /**
     * 被投保人联系方式
     */
    @Column(name = "personByContact", columnDefinition = "VARCHAR(255)   COMMENT '被投保人联系方式'")
    private String personByContact;

    /**
     * 被投保人是否享有社保
     */
    @Column(name = "personBySocial", columnDefinition = "VARCHAR(255)   COMMENT '被投保人是否享有社保'")
    private String personBySocial;

    /**
     * 受益顺序
     */
    @Column(name = "benefitOrder", columnDefinition = "VARCHAR(255)   COMMENT '受益顺序'")
    private String benefitOrder;

    /**
     * 受益人
     */
    @Column(name = "benefiter", columnDefinition = "VARCHAR(255)   COMMENT '受益人'")
    private String benefiter;

    /**
     * 受益人性别（男/女）
     */
    @Column(name = "benefitSex", columnDefinition = "VARCHAR(255)   COMMENT '受益人性别（男/女）'")
    private String benefitSex;

    /**
     * 受益人出生日期
     */
    @Column(name = "benefitBorn", columnDefinition = "DATE  COMMENT '受益人出生日期'")
    private LocalDate benefitBorn;

    /**
     * 受益人证件类型
     */
    @Column(name = "benefitFileType", columnDefinition = "VARCHAR(255)   COMMENT '受益人证件类型'")
    private String benefitFileType;

    /**
     * 受益人证件号
     */
    @Column(name = "benefitFileNum", columnDefinition = "VARCHAR(255)   COMMENT '受益人证件号'")
    private String benefitFileNum;

    /**
     * 是被投保人的
     */
    @Column(name = "benefitBy", columnDefinition = "VARCHAR(255)   COMMENT '是被投保人的'")
    private String benefitBy;

    /**
     * 受益份额
     */
    @Column(name = "benefitShare", columnDefinition = "VARCHAR(255)   COMMENT '受益份额'")
    private String benefitShare;

    /**
     * 机构名称
     */
    @Column(name = "organName", columnDefinition = "VARCHAR(255)   COMMENT '机构名称'")
    private String organName;

    /**
     * 机构代码
     */
    @Column(name = "organCode", columnDefinition = "VARCHAR(255)   COMMENT '机构代码'")
    private String organCode;

    /**
     * 销售员名称
     */
    @Column(name = "saleName", columnDefinition = "VARCHAR(255)   COMMENT '销售员名称'")
    private String saleName;

    /**
     * 销售员代码
     */
    @Column(name = "salerCode", columnDefinition = "VARCHAR(255)   COMMENT '销售员代码'")
    private String salerCode;

    /**
     * 机构联系方式
     */
    @Column(name = "organContact", columnDefinition = "VARCHAR(255)   COMMENT '机构联系方式'")
    private String organContact;

    /**
     * 机构地址
     */
    @Column(name = "organAddr", columnDefinition = "VARCHAR(255)   COMMENT '机构地址'")
    private String organAddr;

    /**
     * 邮政编码
     */
    @Column(name = "postCode", columnDefinition = "VARCHAR(255)   COMMENT '邮政编码'")
    private String postCode;

    /**
     * 是否续签
     */
    @Column(name = "reSign", columnDefinition = "VARCHAR(255)   COMMENT '是否续签(在保/不在保)'")
    private String reSign;

    /**
     * 意外险记录id
     */
    @Column(name = "insureRecordId", columnDefinition = "VARCHAR(255)   COMMENT '意外险记录id'")
    private String insureRecordId;

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getInsureNumber() {
        return insureNumber;
    }

    public void setInsureNumber(String insureNumber) {
        this.insureNumber = insureNumber;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getInsureCusNum() {
        return insureCusNum;
    }

    public void setInsureCusNum(String insureCusNum) {
        this.insureCusNum = insureCusNum;
    }

    public String getInsureByPerson() {
        return insureByPerson;
    }

    public void setInsureByPerson(String insureByPerson) {
        this.insureByPerson = insureByPerson;
    }

    public String getInsureByCusNum() {
        return insureByCusNum;
    }

    public void setInsureByCusNum(String insureByCusNum) {
        this.insureByCusNum = insureByCusNum;
    }

    public LocalDate getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getInsurePeriod() {
        return insurePeriod;
    }

    public void setInsurePeriod(String insurePeriod) {
        this.insurePeriod = insurePeriod;
    }

    public Double getInsureTotalFee() {
        return insureTotalFee;
    }

    public void setInsureTotalFee(Double insureTotalFee) {
        this.insureTotalFee = insureTotalFee;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getInsureRespon() {
        return insureRespon;
    }

    public void setInsureRespon(String insureRespon) {
        this.insureRespon = insureRespon;
    }

    public Double getInsureMoney() {
        return insureMoney;
    }

    public void setInsureMoney(Double insureMoney) {
        this.insureMoney = insureMoney;
    }

    public Double getInsureFee() {
        return insureFee;
    }

    public void setInsureFee(Double insureFee) {
        this.insureFee = insureFee;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public LocalDate getPersonBorn() {
        return personBorn;
    }

    public void setPersonBorn(LocalDate personBorn) {
        this.personBorn = personBorn;
    }

    public String getPersonCountry() {
        return personCountry;
    }

    public void setPersonCountry(String personCountry) {
        this.personCountry = personCountry;
    }

    public String getPersonFileType() {
        return personFileType;
    }

    public void setPersonFileType(String personFileType) {
        this.personFileType = personFileType;
    }

    public String getPersonFileNum() {
        return personFileNum;
    }

    public void setPersonFileNum(String personFileNum) {
        this.personFileNum = personFileNum;
    }

    public String getPersonFileEffTerm() {
        return personFileEffTerm;
    }

    public void setPersonFileEffTerm(String personFileEffTerm) {
        this.personFileEffTerm = personFileEffTerm;
    }

    public String getPersonJob() {
        return personJob;
    }

    public void setPersonJob(String personJob) {
        this.personJob = personJob;
    }

    public String getPersonJobCode() {
        return personJobCode;
    }

    public void setPersonJobCode(String personJobCode) {
        this.personJobCode = personJobCode;
    }

    public String getPersonAddr() {
        return personAddr;
    }

    public void setPersonAddr(String personAddr) {
        this.personAddr = personAddr;
    }

    public String getPersonContact() {
        return personContact;
    }

    public void setPersonContact(String personContact) {
        this.personContact = personContact;
    }

    public String getPersonByRelation() {
        return personByRelation;
    }

    public void setPersonByRelation(String personByRelation) {
        this.personByRelation = personByRelation;
    }

    public String getPersonByName() {
        return personByName;
    }

    public void setPersonByName(String personByName) {
        this.personByName = personByName;
    }

    public String getPersonBySex() {
        return personBySex;
    }

    public void setPersonBySex(String personBySex) {
        this.personBySex = personBySex;
    }

    public LocalDate getPersonByBorn() {
        return personByBorn;
    }

    public void setPersonByBorn(LocalDate personByBorn) {
        this.personByBorn = personByBorn;
    }

    public String getPersonByCountry() {
        return personByCountry;
    }

    public void setPersonByCountry(String personByCountry) {
        this.personByCountry = personByCountry;
    }

    public String getPersonByFileType() {
        return personByFileType;
    }

    public void setPersonByFileType(String personByFileType) {
        this.personByFileType = personByFileType;
    }

    public String getPersonByFileNum() {
        return personByFileNum;
    }

    public void setPersonByFileNum(String personByFileNum) {
        this.personByFileNum = personByFileNum;
    }

    public String getPersonByFileEffTerm() {
        return personByFileEffTerm;
    }

    public void setPersonByFileEffTerm(String personByFileEffTerm) {
        this.personByFileEffTerm = personByFileEffTerm;
    }

    public String getPersonByJob() {
        return personByJob;
    }

    public void setPersonByJob(String personByJob) {
        this.personByJob = personByJob;
    }

    public String getPersonByJobCode() {
        return personByJobCode;
    }

    public void setPersonByJobCode(String personByJobCode) {
        this.personByJobCode = personByJobCode;
    }

    public String getPersonByAddr() {
        return personByAddr;
    }

    public void setPersonByAddr(String personByAddr) {
        this.personByAddr = personByAddr;
    }

    public String getPersonByContact() {
        return personByContact;
    }

    public void setPersonByContact(String personByContact) {
        this.personByContact = personByContact;
    }

    public String getPersonBySocial() {
        return personBySocial;
    }

    public void setPersonBySocial(String personBySocial) {
        this.personBySocial = personBySocial;
    }

    public String getBenefitOrder() {
        return benefitOrder;
    }

    public void setBenefitOrder(String benefitOrder) {
        this.benefitOrder = benefitOrder;
    }

    public String getBenefiter() {
        return benefiter;
    }

    public void setBenefiter(String benefiter) {
        this.benefiter = benefiter;
    }

    public String getBenefitSex() {
        return benefitSex;
    }

    public void setBenefitSex(String benefitSex) {
        this.benefitSex = benefitSex;
    }

    public LocalDate getBenefitBorn() {
        return benefitBorn;
    }

    public void setBenefitBorn(LocalDate benefitBorn) {
        this.benefitBorn = benefitBorn;
    }

    public String getBenefitFileType() {
        return benefitFileType;
    }

    public void setBenefitFileType(String benefitFileType) {
        this.benefitFileType = benefitFileType;
    }

    public String getBenefitFileNum() {
        return benefitFileNum;
    }

    public void setBenefitFileNum(String benefitFileNum) {
        this.benefitFileNum = benefitFileNum;
    }

    public String getBenefitBy() {
        return benefitBy;
    }

    public void setBenefitBy(String benefitBy) {
        this.benefitBy = benefitBy;
    }

    public String getBenefitShare() {
        return benefitShare;
    }

    public void setBenefitShare(String benefitShare) {
        this.benefitShare = benefitShare;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSalerCode() {
        return salerCode;
    }

    public void setSalerCode(String salerCode) {
        this.salerCode = salerCode;
    }

    public String getOrganContact() {
        return organContact;
    }

    public void setOrganContact(String organContact) {
        this.organContact = organContact;
    }

    public String getOrganAddr() {
        return organAddr;
    }

    public void setOrganAddr(String organAddr) {
        this.organAddr = organAddr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getReSign() {
        return reSign;
    }

    public void setReSign(String reSign) {
        this.reSign = reSign;
    }

    public String getInsureRecordId() {
        return insureRecordId;
    }

    public void setInsureRecordId(String insureRecordId) {
        this.insureRecordId = insureRecordId;
    }
}