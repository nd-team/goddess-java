package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 油卡充值传输对象
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:49]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardRechargeTO extends BaseTO {
    /**
     * 数据状态
     */
    private Status status;

    /**
     * 油卡信息Id
     */
    @NotNull(message = "油卡id不能为空!")
    private String oilCardBasicId;

    /**
     * 充值日期
     */
    @NotNull(message = "充值日期不能为空!")
    private String rechargeDate;

    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空!")
    private Double rechargeMoney;

    /**
     * 充值人
     */
    @NotNull(message = "充值人不能为空!")
    private String rechargeUser;

    /**
     * 充值方式
     */
    @NotNull(message = "充值方式不能为空!")
    private String rechargeWay;

    /**
     * 期初金额
     */
    private Double cycleEarlyMoney;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
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

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }
}
