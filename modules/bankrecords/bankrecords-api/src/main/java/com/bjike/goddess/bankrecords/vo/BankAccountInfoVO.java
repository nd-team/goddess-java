package com.bjike.goddess.bankrecords.vo;

/**
 * 账号信息表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankAccountInfoVO {
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
     * 银行(开户行)
     */
    private String bank;

    /**
     * 银行地址(开户行地址)
     */
    private String bankAddress;

    /**
     * 类型(开户行属性)
     */
    private String type;

    /**
     * 备注
     */
    private  String remark;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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