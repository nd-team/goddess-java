package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.AccountingPeriod;
import com.bjike.goddess.financeinit.enums.AccountingSystem;
import com.bjike.goddess.financeinit.enums.ScaleShape;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 初始化数据录入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 初始化数据录入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_baseparameter")
public class BaseParameter extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 本位币
     */
    @Column(name = "functionalCurrency", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '本位币'")
    private String functionalCurrency;

    /**
     * 会计制度
     */
    @Column(name = "accountingSystem", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '会计制度'")
    private AccountingSystem accountingSystem;

    /**
     * 会计期间
     */
    @Column(name = "accountingPeriod", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '会计期间'")
    private AccountingPeriod accountingPeriod;

    /**
     * 会计期间对应时间
     */
    @Column(name = "apDate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会计期间对应时间'")
    private String apDate;

    /**
     * 税号
     */
    @Column(name = "ein", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税号'")
    private String ein;

    /**
     * 电话
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话'")
    private String phone;

    /**
     * 地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 开户银行
     */
    @Column(name = "bank", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开户银行'")
    private String bank;

    /**
     * 银行账号
     */
    @Column(name = "account", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行账号'")
    private String account;

    /**
     * 公司规模形式
     */
    @Column(name = "scaleShape", nullable = false, columnDefinition = "TINYINT(2)    COMMENT '公司规模形式'")
    private ScaleShape scaleShape;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFunctionalCurrency() {
        return functionalCurrency;
    }

    public void setFunctionalCurrency(String functionalCurrency) {
        this.functionalCurrency = functionalCurrency;
    }

    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    public void setAccountingSystem(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public AccountingPeriod getAccountingPeriod() {
        return accountingPeriod;
    }

    public void setAccountingPeriod(AccountingPeriod accountingPeriod) {
        this.accountingPeriod = accountingPeriod;
    }

    public String getApDate() {
        return apDate;
    }

    public void setApDate(String apDate) {
        this.apDate = apDate;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ScaleShape getScaleShape() {
        return scaleShape;
    }

    public void setScaleShape(ScaleShape scaleShape) {
        this.scaleShape = scaleShape;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}