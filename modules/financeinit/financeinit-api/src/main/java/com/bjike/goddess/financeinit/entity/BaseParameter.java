package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.AccountingPeriod;
import com.bjike.goddess.financeinit.enums.AccountingSystem;
import com.bjike.goddess.financeinit.enums.ScaleShape;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 基本参数
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数 ]
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
     * 会计期间界定方式
     */
    @Column(name = "defWayAccountPerod", nullable = false, columnDefinition = "VARCHAR(255)  COMMENT '会计期间界定方式'")
    private String defWayAccountPerod;

    /**
     * 会计年度启用时间
     */
    @Column(name = "accountingYear", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会计年度启用时间'")
    private String accountingYear;

    /**
     * 账套会计期间启用日期
     */
    @Column(name = "dateDuringPeriod", nullable = false, columnDefinition = "DATE   COMMENT '账套会计期间启用日期'")
    private LocalDate dateDuringPeriod;

    /**
     * 账套会计启用日期
     */
    @Column(name = "apDate", nullable = false, columnDefinition = "DATE   COMMENT '账套会计启用日期'")
    private LocalDate accountOpening;

//    /**
//     * 税号
//     */
//    @Column(name = "ein", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '税号'")
//    private String ein;
//
//    /**
//     * 电话
//     */
//    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话'")
//    private String phone;
//
//    /**
//     * 地址
//     */
//    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
//    private String address;
//
//    /**
//     * 开户银行
//     */
//    @Column(name = "bank", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '开户银行'")
//    private String bank;
//
//    /**
//     * 银行账号
//     */
//    @Column(name = "account", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行账号'")
//    private String account;
//
//    /**
//     * 公司规模形式
//     */
//    @Column(name = "scaleShape", nullable = false, columnDefinition = "TINYINT(2)    COMMENT '公司规模形式'")
//    private ScaleShape scaleShape;
//
//    /**
//     * 备注
//     */
//    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
//    private String remark;


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

    public String getDefWayAccountPerod() {
        return defWayAccountPerod;
    }

    public void setDefWayAccountPerod(String defWayAccountPerod) {
        this.defWayAccountPerod = defWayAccountPerod;
    }

    public String getAccountingYear() {
        return accountingYear;
    }

    public void setAccountingYear(String accountingYear) {
        this.accountingYear = accountingYear;
    }

    public LocalDate getDateDuringPeriod() {
        return dateDuringPeriod;
    }

    public void setDateDuringPeriod(LocalDate dateDuringPeriod) {
        this.dateDuringPeriod = dateDuringPeriod;
    }

    public LocalDate getAccountOpening() {
        return accountOpening;
    }

    public void setAccountOpening(LocalDate accountOpening) {
        this.accountOpening = accountOpening;
    }

}