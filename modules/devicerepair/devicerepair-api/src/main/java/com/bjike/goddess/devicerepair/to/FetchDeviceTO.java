package com.bjike.goddess.devicerepair.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 取回设备to
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-03 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FetchDeviceTO implements Serializable {

    public interface FetchDevice{}

    /**
     * id
     */
    @NotBlank(groups = {FetchDeviceTO.FetchDevice.class}, message = "设备维修id不能为空")
    private String id;

    /**
     * 厂家收款方式
     */
    @NotBlank(groups = {FetchDeviceTO.FetchDevice.class}, message = "厂家收款方式不能为空")
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
    @NotNull(groups = {FetchDeviceTO.FetchDevice.class}, message = "是否收款不能为空")
    private Boolean whetherPayment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
