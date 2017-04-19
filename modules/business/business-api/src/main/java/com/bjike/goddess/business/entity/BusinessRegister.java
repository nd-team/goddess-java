package com.bjike.goddess.business.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 工商注册
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "business_businessregister")
public class BusinessRegister extends BaseEntity {

    /**
     * 注册公司名称
     */
    @Column(name = "registerCompanyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注册公司名称'")
    private String registerCompanyName;

    /**
     * 注册号/统一社会信用代码
     */
    @Column(name = "registerNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注册号/统一社会信用代码'")
    private String registerNum;

    /**
     * 经营期限
     */
    @Column(name = "operationPeriod", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经营期限'")
    private String operationPeriod;

    /**
     * 注册类型
     */
    @Column(name = "registerType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注册类型'")
    private String registerType;

    /**
     * 注册资本
     */
    @Column(name = "registerCapital", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '注册资本'")
    private String registerCapital;

    /**
     * 经营范围
     */
    @Column(name = "operationScope", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '经营范围'")
    private String operationScope;

    /**
     * 法人
     */
    @Column(name = "legalPerson", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '法人'")
    private String legalPerson;

    /**
     * 股东
     */
    @Column(name = "shareholders", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股东'")
    private String shareholders;

    /**
     * 股权比例
     */
    @Column(name = "equityRatio", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '股权比例'")
    private String equityRatio;

    /**
     * 地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 相关资料（名称）
     */
    @Column(name = "cursorAdapter", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '相关资料（名称）'")
    private String cursorAdapter;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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

    public String getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(String equityRatio) {
        this.equityRatio = equityRatio;
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
}