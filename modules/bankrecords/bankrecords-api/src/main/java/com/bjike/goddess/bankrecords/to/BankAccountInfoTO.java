package com.bjike.goddess.bankrecords.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 账号信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankAccountInfoTO extends BaseTO {

    /**
     * 账号名称
     */
    @NotBlank(message = "账户名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 账号
     */
    @NotBlank(message = "账户不能为空", groups = {ADD.class, EDIT.class})
    private String number;

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空", groups = {ADD.class, EDIT.class})
    private String cardNumber;

    /**
     * 公司
     */
    @NotBlank(message = "公司不能为空", groups = {ADD.class, EDIT.class})
    private String company;

    /**
     * 银行
     */
    @NotBlank(message = "银行不能为空", groups = {ADD.class, EDIT.class})
    private String bank;

    /**
     * 银行地址
     */
    @NotBlank(message = "账户名称不能为空", groups = {ADD.class, EDIT.class})
    private String bankAddress;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = {ADD.class, EDIT.class})
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