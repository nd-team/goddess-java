package com.bjike.goddess.oilcardprepared.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 等待付款
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}
    public interface ConfirmPay{}

    /**
     * 油卡编号
     */
    @NotBlank(message = "油卡编号不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private String oilCardCode;

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private String oilCardNumber;

    /**
     * 充值日期
     */
    @NotBlank(message = "充值日期不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private String rechargeDate;

    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private Double rechargeMoney;

    /**
     * 充值人
     */
    @NotBlank(message = "充值人不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private String rechargeUser;

    /**
     * 充值方式
     */
    @NotBlank(message = "充值方式不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private String rechargeWay;

    /**
     * 初始余额
     */
    @NotNull(message = "初始余额不能为空",groups = {WaitPayTO.TestAdd.class,WaitPayTO.TestEdit.class})
    private Double oilCardBalance;

    /**
     * 是否付款
     */
    @NotNull(groups = {WaitPayTO.ConfirmPay.class},message = "是否付款不能为空")
    private Boolean pay;


    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(String rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay;
    }

    public Double getOilCardBalance() {
        return oilCardBalance;
    }

    public void setOilCardBalance(Double oilCardBalance) {
        this.oilCardBalance = oilCardBalance;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }
}