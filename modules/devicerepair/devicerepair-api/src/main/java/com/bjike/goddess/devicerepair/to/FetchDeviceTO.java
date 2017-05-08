package com.bjike.goddess.devicerepair.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 取回设备to
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-03 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FetchDeviceTO extends BaseTO {

    /**
     * 厂家收款方式
     */
    private String venderReceiptMode;

    /**
     * 开户名称
     */
    private String accountName;

    /**
     * 开户账号
     */
    private String accountNumber;

    /**
     * 开户行
     */
    private String bankOfDeposit;

    /**
     * 是否付款
     */
    private Boolean whetherPayment;

    public String getVenderReceiptMode() {
        return venderReceiptMode;
    }

    public void setVenderReceiptMode(String venderReceiptMode) {
        this.venderReceiptMode = venderReceiptMode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankOfDeposit() {
        return bankOfDeposit;
    }

    public void setBankOfDeposit(String bankOfDeposit) {
        this.bankOfDeposit = bankOfDeposit;
    }

    public Boolean getWhetherPayment() {
        return whetherPayment;
    }

    public void setWhetherPayment(Boolean whetherPayment) {
        this.whetherPayment = whetherPayment;
    }
}
