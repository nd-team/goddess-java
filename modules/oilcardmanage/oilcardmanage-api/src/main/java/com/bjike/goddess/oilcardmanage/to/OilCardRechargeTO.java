package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

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
     * 油卡信息Id
     */
    @NotBlank(message = "油卡id不能为空!",groups = {ADD.class, EDIT.class})
    private String oilCardBasicId;

    /**
     * 充值日期
     */
    @NotBlank(message = "充值日期不能为空!",groups ={ADD.class,EDIT.class} )
    private String rechargeDate;

    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空!",groups = {ADD.class,EDIT.class})
    private Double rechargeMoney;

    /**
     * 充值人
     */
    @NotBlank(message = "充值人不能为空!",groups = {ADD.class,EDIT.class})
    private String rechargeUser;

    /**
     * 充值方式
     */
    @NotBlank(message = "充值方式不能为空!",groups = {ADD.class,EDIT.class})
    private String rechargeWay;

    /**
     * 期初金额
     */
    @NotNull(message = "期初金额不能为空!",groups = {ADD.class,EDIT.class})
    private Double cycleEarlyMoney;

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
