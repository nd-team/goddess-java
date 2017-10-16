package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.AccountingPeriod;
import com.bjike.goddess.financeinit.enums.AccountingSystem;
import com.bjike.goddess.financeinit.enums.ScaleShape;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 基本参数
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 04:11 ]
 * @Description: [ 基本参数 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseParameterTO extends BaseTO {

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 本位币
     */
    @NotBlank(message = "本位币不能为空",groups = {ADD.class, EDIT.class})
    private String functionalCurrency;

    /**
     * 会计制度
     */
    @NotNull(message = "会计制度不能为空",groups = {ADD.class, EDIT.class})
    private AccountingSystem accountingSystem;

    /**
     * 会计期间
     */
    @NotNull(message = "会计期间不能为空",groups = {ADD.class, EDIT.class})
    private AccountingPeriod accountingPeriod;

    /**
     * 会计期间对应时间
     */
    @NotBlank(message = "会计期间对应时间不能为空",groups = {ADD.class, EDIT.class})
    private String apDate;

    /**
     * 税号
     */
    @NotBlank(message = "税号不能为空",groups = {EDIT.class})
    private String ein;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空",groups = {EDIT.class})
    private String phone;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空",groups = {EDIT.class})
    private String address;

    /**
     * 开户银行
     */
    @NotBlank(message = "开户银行不能为空",groups = {EDIT.class})
    private String bank;

    /**
     * 银行账号
     */
    @NotBlank(message = "银行账号不能为空",groups = {EDIT.class})
    private String account;

    /**
     * 公司规模形式
     */
    @NotNull(message = "公司规模形式不能为空",groups = {EDIT.class})
    private ScaleShape scaleShape;

    /**
     * 备注
     */
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