package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 账号信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankAccountInfoBO extends BaseBO {
    /**
     * 账号id
     */
    private String id;
    /**
     * 账号名称
     */
    private String name;

    /**
     * 账号
     */
    private String number;

    /**
     * 卡号
     */
    private String cardNumber;

    /**
     * 公司
     */
    private String company;

    /**
     * 银行
     */
    private String bank;

    /**
     * 银行地址
     */
    private String bankAddress;

    /**
     * 类型
     */
    private String type;

    /**
     * 备注
     */
    private  String remark;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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