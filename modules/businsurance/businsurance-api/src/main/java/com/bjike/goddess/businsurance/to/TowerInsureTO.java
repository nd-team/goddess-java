package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 塔工意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TowerInsureTO extends BaseTO {

    public interface TestAddAndEdit{}

    /**
     * 合同号
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "合同号不能为空")
    private String contractNum;

    /**
     * 投保单号
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "投保单号不能为空")
    private String insureNumber;

    /**
     * 投保人
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "投保人不能为空")
    private String insurer;

    /**
     * 投保人客户号
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "投保人客户号不能为空")
    private String insureCusNum;

    /**
     * 被投保人
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "被投保人不能为空")
    private Double insureByPerson;

    /**
     * 被投保人客户号
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "被投保人客户号不能为空")
    private String insureByCusNum;

    /**
     * 合同生效日期
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "合同生效日期不能为空，日期格式年月日")
    private String effectDate;

    /**
     * 合同期满日期
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "合同期满日期不能为空,日期格式年月日")
    private String expireDate;

    /**
     * 保险期间
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "保险期间不能为空")
    private String insurePeriod;

    /**
     * 保险费合计
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "保险费合计不能为空")
    private Double insureTotalFee;

    /**
     * 币种
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "币种不能为空")
    private String billType;

    /**
     * 缴费方式
     */
    @NotBlank(groups = TowerInsureTO.TestAddAndEdit.class , message = "缴费方式不能为空")
    private String payWay;

    /**
     * 账户形式
     */
    private String accountType;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 帐号
     */
    private String account;

    /**
     * 账户所有人姓名
     */
    private String accountOwner;

    /**
     * 险种代码
     */
    private String insureCode;

    /**
     * 险种名称
     */
    private String insureName;

    /**
     * 保险责任
     */
    private String insureRespon;

    /**
     * 保险金额
     */
    private Double insureMoney;

    /**
     * 保险费
     */
    private Double insureFee;

    /**
     * 投保人姓名
     */
    private String personName;

    /**
     * 投保人性别（男/女）
     */
    private String personSex;

    /**
     * 投保人出生日期
     */
    private String personBorn;

    /**
     * 投保人国籍
     */
    private String personCountry;

    /**
     * 投保人证件类型
     */
    private String personFileType;

    /**
     * 投保人证件号
     */
    private String personFileNum;

    /**
     * 投保人证件有效期限
     */
    private String personFileEffTerm;

    /**
     * 投保人职业
     */
    private String personJob;

    /**
     * 投保人职业代码
     */
    private String personJobCode;

    /**
     * 投保人通讯地址
     */
    private String personAddr;

    /**
     * 投保人联系方式
     */
    private String personContact;

    /**
     * 被投保人是投保人的
     */
    private String personByRelation;

    /**
     * 被投保人姓名
     */
    private String personByName;

    /**
     * 被投保人性别（男/女）
     */
    private String personBySex;

    /**
     * 被投保人出生日期
     */
    private String personByBorn;

    /**
     * 被投保人国籍
     */
    private String personByCountry;

    /**
     * 被投保人证件类型
     */
    private String personByFileType;

    /**
     * 被投保人证件号
     */
    private String personByFileNum;

    /**
     * 被投保人证件有效期限
     */
    private String personByFileEffTerm;

    /**
     * 被投保人职业
     */
    private String personByJob;

    /**
     * 被投保人职业代码
     */
    private String personByJobCode;

    /**
     * 被投保人通讯地址
     */
    private String personByAddr;

    /**
     * 被投保人联系方式
     */
    private String personByContact;

    /**
     * 被投保人是否享有社保
     */
    private String personBySocial;

    /**
     * 受益顺序
     */
    private String benefitOrder;

    /**
     * 受益人
     */
    private String benefiter;

    /**
     * 受益人性别（男/女）
     */
    private String benefitSex;

    /**
     * 受益人出生日期
     */
    private String benefitBorn;

    /**
     * 受益人证件类型
     */
    private String benefitFileType;

    /**
     * 受益人证件号
     */
    private String benefitFileNum;

    /**
     * 是被投保人的
     */
    private String benefitBy;

    /**
     * 受益份额
     */
    private String benefitShare;

    /**
     * 机构名称
     */
    private String organName;

    /**
     * 机构代码
     */
    private String organCode;

    /**
     * 销售员名称
     */
    private String saleName;

    /**
     * 销售员代码
     */
    private String salerCode;

    /**
     * 机构联系方式
     */
    private String organContact;

    /**
     * 机构地址
     */
    private String organAddr;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 是否续签
     */
    private String reSign;

    /**
     * 意外险记录id
     */
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

    public Double getInsureByPerson() {
        return insureByPerson;
    }

    public void setInsureByPerson(Double insureByPerson) {
        this.insureByPerson = insureByPerson;
    }

    public String getInsureByCusNum() {
        return insureByCusNum;
    }

    public void setInsureByCusNum(String insureByCusNum) {
        this.insureByCusNum = insureByCusNum;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
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

    public String getPersonBorn() {
        return personBorn;
    }

    public void setPersonBorn(String personBorn) {
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

    public String getPersonByBorn() {
        return personByBorn;
    }

    public void setPersonByBorn(String personByBorn) {
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

    public String getBenefitBorn() {
        return benefitBorn;
    }

    public void setBenefitBorn(String benefitBorn) {
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