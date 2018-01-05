package com.bjike.goddess.businsurance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 团体意外险信息管理业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GroupInsureBO extends BaseBO {

    /**
     * 联系人
     */
    private String contractor;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 首期投保人数合计
     */
    private Double initialInsurer;

    /**
     * 交费方式
     */
    private String payWay;

    /**
     * 首期保费合计
     */
    private Double initialTotalFee;

    /**
     * 保险开始时间
     */
    private String insureStartDate;

    /**
     * 保险结束时间
     */
    private String insureEndDate;

    /**
     * 保险期限
     */
    private String insureTerm;

    /**
     * 保险层级
     */
    private String insureLevel;

    /**
     * 保险层级描述
     */
    private String insureLevelDes;

    /**
     * 本层级收集投保人数
     */
    private Double insurePersonNum;

    /**
     * 本层级保险期限
     */
    private String insureLevelTerm;

    /**
     * 保险项目
     */
    private String insureProject;

    /**
     * 保险金额明细
     */
    private String insureFeeDetail;

    /**
     * 保费合计
     */
    private Double totalMoney;

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
    private String salerName;

    /**
     * 销售员代码
     */
    private String salerCode;

    /**
     * 机构联系方式
     */
    private String organContract;

    /**
     * 机构地址
     */
    private String organAddr;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 意外险记录id
     */
    private String insureRecordId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getInitialInsurer() {
        return initialInsurer;
    }

    public void setInitialInsurer(Double initialInsurer) {
        this.initialInsurer = initialInsurer;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Double getInitialTotalFee() {
        return initialTotalFee;
    }

    public void setInitialTotalFee(Double initialTotalFee) {
        this.initialTotalFee = initialTotalFee;
    }

    public String getInsureStartDate() {
        return insureStartDate;
    }

    public void setInsureStartDate(String insureStartDate) {
        this.insureStartDate = insureStartDate;
    }

    public String getInsureEndDate() {
        return insureEndDate;
    }

    public void setInsureEndDate(String insureEndDate) {
        this.insureEndDate = insureEndDate;
    }

    public String getInsureTerm() {
        return insureTerm;
    }

    public void setInsureTerm(String insureTerm) {
        this.insureTerm = insureTerm;
    }

    public String getInsureLevel() {
        return insureLevel;
    }

    public void setInsureLevel(String insureLevel) {
        this.insureLevel = insureLevel;
    }

    public String getInsureLevelDes() {
        return insureLevelDes;
    }

    public void setInsureLevelDes(String insureLevelDes) {
        this.insureLevelDes = insureLevelDes;
    }

    public Double getInsurePersonNum() {
        return insurePersonNum;
    }

    public void setInsurePersonNum(Double insurePersonNum) {
        this.insurePersonNum = insurePersonNum;
    }

    public String getInsureLevelTerm() {
        return insureLevelTerm;
    }

    public void setInsureLevelTerm(String insureLevelTerm) {
        this.insureLevelTerm = insureLevelTerm;
    }

    public String getInsureProject() {
        return insureProject;
    }

    public void setInsureProject(String insureProject) {
        this.insureProject = insureProject;
    }

    public String getInsureFeeDetail() {
        return insureFeeDetail;
    }

    public void setInsureFeeDetail(String insureFeeDetail) {
        this.insureFeeDetail = insureFeeDetail;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
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

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getSalerCode() {
        return salerCode;
    }

    public void setSalerCode(String salerCode) {
        this.salerCode = salerCode;
    }

    public String getOrganContract() {
        return organContract;
    }

    public void setOrganContract(String organContract) {
        this.organContract = organContract;
    }

    public String getOrganAddr() {
        return organAddr;
    }

    public void setOrganAddr(String organAddr) {
        this.organAddr = organAddr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getInsureRecordId() {
        return insureRecordId;
    }

    public void setInsureRecordId(String insureRecordId) {
        this.insureRecordId = insureRecordId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}