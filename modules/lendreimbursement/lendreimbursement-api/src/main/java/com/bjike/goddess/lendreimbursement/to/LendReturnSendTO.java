package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 网页版的申请借款还款有误编辑
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 网页版的申请借款还款有误编辑 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendReturnSendTO extends BaseTO {

    public interface TESTRETURNSEND{}


    /**
     * 报销金额（实际用款）
     */
    @NotNull(groups = {LendReturnSendTO.TESTRETURNSEND.class},message ="报销金额不能为空，且为数字double" )
    private Double reimMoney;


    /**
     * 借款金额
     */
    @NotNull(groups = {ApplyLendTO.TESTReturnMoney.class},message ="借款金额不能为空，且为数字double" )
    private Double lendMoney;


    /**
     * 退回金额
     */
    @NotNull(groups = {LendReturnSendTO.TESTRETURNSEND.class},message ="退回金额不能为空，且为数字double" )
    private Double returnMoney;

    /**
     * 退回日期（还款时间）
     */
    @NotBlank(groups = {LendReturnSendTO.TESTRETURNSEND.class},message ="还款时间不能为空，日期格式未年-月-日" )
    private String returnDate;

    /**
     * 归还方式(还款方式)
     */
    @NotBlank(groups = {LendReturnSendTO.TESTRETURNSEND.class},message ="还款方式不能为空" )
    private String returnWays;

    /**
     * 归还账户
     */
    private String returnAccount;


    public Double getReimMoney() {
        return reimMoney;
    }

    public void setReimMoney(Double reimMoney) {
        this.reimMoney = reimMoney;
    }

    public Double getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(Double lendMoney) {
        this.lendMoney = lendMoney;
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
}