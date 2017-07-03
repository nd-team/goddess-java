package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 团体意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_groupinsure")
public class GroupInsure extends BaseEntity {

    /**
     * 联系人
     */
    @Column(name = "contractor",  columnDefinition = "VARCHAR(255)   COMMENT '联系人'")
    private String contractor;

    /**
     * 联系电话
     */
    @Column(name = "tel",  columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String tel;

    /**
     * 首期投保人数合计
     */
    @Column(name = "initialInsurer",  columnDefinition = "DECIMAL(10,2)   COMMENT '首期投保人数合计'")
    private Double initialInsurer;

    /**
     * 交费方式
     */
    @Column(name = "payWay",  columnDefinition = "VARCHAR(255)   COMMENT '交费方式'")
    private String payWay;

    /**
     * 首期保费合计
     */
    @Column(name = "initialTotalFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '首期保费合计'")
    private Double initialTotalFee;

    /**
     * 保险开始时间
     */
    @Column(name = "insureStartDate",  columnDefinition = "DATE   COMMENT '保险开始时间'")
    private LocalDate insureStartDate;

    /**
     * 保险结束时间
     */
    @Column(name = "insureEndDate",  columnDefinition = "DATE  COMMENT '保险结束时间'")
    private LocalDate insureEndDate;

    /**
     * 保险期限
     */
    @Column(name = "insureTerm",  columnDefinition = "VARCHAR(255)   COMMENT '保险期限'")
    private String insureTerm;

    /**
     * 保险层级
     */
    @Column(name = "insureLevel",  columnDefinition = "VARCHAR(255)   COMMENT '保险层级'")
    private String insureLevel;

    /**
     * 保险层级描述
     */
    @Column(name = "insureLevelDes",  columnDefinition = "VARCHAR(255)   COMMENT '保险层级描述'")
    private String insureLevelDes;

    /**
     * 本层级收集投保人数
     */
    @Column(name = "insurePersonNum",  columnDefinition = "DECIMAL(10,2)   COMMENT '本层级收集投保人数'")
    private Double insurePersonNum;

    /**
     * 本层级保险期限
     */
    @Column(name = "insureLevelTerm",  columnDefinition = "VARCHAR(255)   COMMENT '本层级保险期限'")
    private String insureLevelTerm;

    /**
     * 保险项目
     */
    @Column(name = "insureProject",  columnDefinition = "VARCHAR(255)   COMMENT '保险项目'")
    private String insureProject;

    /**
     * 保险金额明细
     */
    @Column(name = "insureFeeDetail",  columnDefinition = "VARCHAR(255)   COMMENT '保险金额明细'")
    private String insureFeeDetail;

    /**
     * 保费合计
     */
    @Column(name = "totalMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '保费合计'")
    private Double totalMoney;

    /**
     * 机构名称
     */
    @Column(name = "organName",  columnDefinition = "VARCHAR(255)   COMMENT '机构名称'")
    private String organName;

    /**
     * 机构代码
     */
    @Column(name = "organCode",  columnDefinition = "VARCHAR(255)   COMMENT '机构代码'")
    private String organCode;

    /**
     * 销售员名称
     */
    @Column(name = "salerName",  columnDefinition = "VARCHAR(255)   COMMENT '销售员名称'")
    private String salerName;

    /**
     * 销售员代码
     */
    @Column(name = "salerCode",  columnDefinition = "VARCHAR(255)   COMMENT '销售员代码'")
    private String salerCode;

    /**
     * 机构联系方式
     */
    @Column(name = "organContract",  columnDefinition = "VARCHAR(255)   COMMENT '机构联系方式'")
    private String organContract;

    /**
     * 机构地址
     */
    @Column(name = "organAddr",  columnDefinition = "VARCHAR(255)   COMMENT '机构地址'")
    private String organAddr;

    /**
     * 邮政编码
     */
    @Column(name = "postcode",  columnDefinition = "VARCHAR(255)   COMMENT '邮政编码'")
    private String postcode;

    /**
     * 意外险记录id
     */
    @Column(name = "insureRecordId",  columnDefinition = "VARCHAR(255)   COMMENT '意外险记录id'")
    private String insureRecordId;

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

    public LocalDate getInsureStartDate() {
        return insureStartDate;
    }

    public void setInsureStartDate(LocalDate insureStartDate) {
        this.insureStartDate = insureStartDate;
    }

    public LocalDate getInsureEndDate() {
        return insureEndDate;
    }

    public void setInsureEndDate(LocalDate insureEndDate) {
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
}