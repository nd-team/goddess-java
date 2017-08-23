package com.bjike.goddess.business.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 工商注册业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusinessRegisterBO extends BaseBO {

    /**
     * 注册公司名称
     */
    private String registerCompanyName;

    /**
     * 注册号/统一社会信用代码
     */
    private String registerNum;

    /**
     * 经营期限
     */
    private String operationPeriod;

    /**
     * 注册类型
     */
    private String registerType;

    /**
     * 注册资本
     */
    private String registerCapital;

    /**
     * 经营范围
     */
    private String operationScope;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 股东
     */
    private String shareholders;

    /**
     * 股权比例
     */
    private String equityRatio;

    /**
     * 地址
     */
    private String address;

    /**
     * 相关资料（名称）
     */
    private String cursorAdapter;

    /**
     * 备注
     */
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