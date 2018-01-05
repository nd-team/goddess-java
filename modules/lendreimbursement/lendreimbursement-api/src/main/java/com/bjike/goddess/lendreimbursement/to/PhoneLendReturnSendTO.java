package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款还款和寄件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款还款和寄件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneLendReturnSendTO extends BaseTO {

    public interface TESTRETURNSEND{}


    /**
     * 报销金额（实际用款）
     */
    @NotNull(groups = {PhoneLendReturnSendTO.TESTRETURNSEND.class},message ="报销金额不能为空，且为数字double" )
    private Double reimMoney;


    /**
     * 退回金额
     */
    @NotNull(groups = {PhoneLendReturnSendTO.TESTRETURNSEND.class},message ="退回金额不能为空，且为数字double" )
    private Double returnMoney;

    /**
     * 退回日期（还款时间）
     */
    @NotBlank(groups = {PhoneLendReturnSendTO.TESTRETURNSEND.class},message ="还款时间不能为空，日期格式未年-月-日" )
    private String returnDate;

    /**
     * 归还方式(还款方式)
     */
    @NotBlank(groups = {PhoneLendReturnSendTO.TESTRETURNSEND.class},message ="还款方式不能为空" )
    private String returnWays;

    /**
     * 归还账户
     */
    private String returnAccount;

    /**
     * 还款说明
     */
    private String returnRemark;

    /**
     * 是否有发票(是或否)
     */
    @NotBlank(groups = {PhoneLendReturnSendTO.TESTRETURNSEND.class},message ="是否有发票不能为空,填是或否" )
    private String invoice;


    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public Double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Double returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnWays() {
        return returnWays;
    }

    public void setReturnWays(String returnWays) {
        this.returnWays = returnWays;
    }

    public String getReturnAccount() {
        return returnAccount;
    }

    public void setReturnAccount(String returnAccount) {
        this.returnAccount = returnAccount;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}