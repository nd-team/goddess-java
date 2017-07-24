package com.bjike.goddess.businsurance.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 商业保险方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-21 09:44 ]
 * @Description: [ 商业保险方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BusInsuranceExcel extends BaseTO {

    /**
     * 保险公司
     */
    @ExcelHeader(name = "保险公司")
    private String insureComapny;

    /**
     * 投保险种
     */
    @ExcelHeader(name = "投保险种")
    private String insureType;

    /**
     * 保险条件
     */
    @ExcelHeader(name = "保险条件")
    private String insureCondition;

    /**
     * 参保人总数
     */
    @ExcelHeader(name = "参保人总数")
    private String personNum;

    /**
     * 保全金额
     */
    @ExcelHeader(name = "保全金额")
    private Double insureMoney;

    /**
     * 保险期限
     */
    @ExcelHeader(name = "保险期限")
    private String insureTerm;

    /**
     * 责任期限
     */
    @ExcelHeader(name = "责任期限")
    private String liabilityTerm;

    /**
     * 投保金额
     */
    @ExcelHeader(name = "投保金额")
    private Double money;

    /**
     * 缴费年限
     */
    @ExcelHeader(name = "缴费年限")
    private String payPeriod;

    /**
     * 缴费类别
     */
    @ExcelHeader(name = "缴费类别")
    private String payType;

    /**
     * 缴费方式
     */
    @ExcelHeader(name = "缴费方式")
    private String payWay;

    /**
     * 购买要求
     */
    @ExcelHeader(name = "购买要求")
    private String buyQuired;

    /**
     * 资料记录
     */
    @ExcelHeader(name = "资料记录")
    private String fileRecord;

    /**
     * 福利模块意见
     */
    @ExcelHeader(name = "福利模块意见")
    private String warefareAdvice;

    /**
     * 运营商务部意见
     */
    @ExcelHeader(name = "运营商务部意见")
    private String operaAdvice;

    /**
     * 总经办是否通过
     */
    @ExcelHeader(name = "总经办是否通过")
    private String manageAdvice;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String remark;

    public String getInsureComapny() {
        return insureComapny;
    }

    public void setInsureComapny(String insureComapny) {
        this.insureComapny = insureComapny;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getInsureCondition() {
        return insureCondition;
    }

    public void setInsureCondition(String insureCondition) {
        this.insureCondition = insureCondition;
    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public Double getInsureMoney() {
        return insureMoney;
    }

    public void setInsureMoney(Double insureMoney) {
        this.insureMoney = insureMoney;
    }

    public String getInsureTerm() {
        return insureTerm;
    }

    public void setInsureTerm(String insureTerm) {
        this.insureTerm = insureTerm;
    }

    public String getLiabilityTerm() {
        return liabilityTerm;
    }

    public void setLiabilityTerm(String liabilityTerm) {
        this.liabilityTerm = liabilityTerm;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getBuyQuired() {
        return buyQuired;
    }

    public void setBuyQuired(String buyQuired) {
        this.buyQuired = buyQuired;
    }

    public String getFileRecord() {
        return fileRecord;
    }

    public void setFileRecord(String fileRecord) {
        this.fileRecord = fileRecord;
    }

    public String getWarefareAdvice() {
        return warefareAdvice;
    }

    public void setWarefareAdvice(String warefareAdvice) {
        this.warefareAdvice = warefareAdvice;
    }

    public String getOperaAdvice() {
        return operaAdvice;
    }

    public void setOperaAdvice(String operaAdvice) {
        this.operaAdvice = operaAdvice;
    }

    public String getManageAdvice() {
        return manageAdvice;
    }

    public void setManageAdvice(String manageAdvice) {
        this.manageAdvice = manageAdvice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}