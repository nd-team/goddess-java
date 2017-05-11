package com.bjike.goddess.bankrecords.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 账号信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bankrecords_bankaccountinfo")
public class BankAccountInfo extends BaseEntity {

    /**
     * 账号名称
     */
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '账号名称'")
    private String name;

    /**
     * 账号
     */
    @Column(name = "number", nullable = false, unique = true, columnDefinition = "VARCHAR(255)   COMMENT '账号'")
    private String number;

    /**
     * 卡号
     */
    @Column(name = "cardNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '卡号'")
    private String cardNumber;

    /**
     * 公司
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;

    /**
     * 银行
     */
    @Column(name = "bank", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行'")
    private String bank;

    /**
     * 银行地址
     */
    @Column(name = "bankAddress", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '银行地址'")
    private String bankAddress;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}