package com.bjike.goddess.oilcardmanage.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:50]
 * @Package:[ com.bjike.goddess.oilcardmanage.vo ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardRechargeVO {

    /**
     * id
     */
    private String id;

    /**
     * 油卡编号
     */
    private String oilCardCode;

    /**
     * 卡号
     */
    private String oilCardNumber;

    /**
     * 所属主卡
     */
    private String mainOrDeputy;

    /**
     * 充值日期
     */
    private String rechargeDate;

    /**
     * 充值金额
     */
    private Double rechargeMoney;

    /**
     * 充值人
     */
    private String rechargeUser;

    /**
     * 充值方式
     */
    private String rechargeWay;

    /**
     * 期初金额
     */
    private Double cycleEarlyMoney;

    /**
     * 是否充值
     */
    private Boolean ifRecharge;

    /**
     * 备用金
     */
    private Double pettyCash;

    /**
     * 充值前备用金
     */
    private Double rechargeBeforePettyCash;

    /**
     * 是否上传截图附件
     */
    private Boolean ifUploadScreenshot;

    /**
     * 是否充值通报
     */
    private Boolean ifPrepaidNotification;

    /**
     * 是否上传充值后截图附件
     */
    private Boolean ifUploadRecharge;

    /**
     * 充值后总金额
     */
    private Double afterRechargeTotalMoney;

    /**
     * 充值后油卡余额
     */
    private Double afterRechargeBalance;

    /**
     * 油卡id
     */

    private String oilCardBasicId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMainOrDeputy() {
        return mainOrDeputy;
    }

    public void setMainOrDeputy(String mainOrDeputy) {
        this.mainOrDeputy = mainOrDeputy;
    }

    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
    }

    public Boolean getIfRecharge() {
        return ifRecharge;
    }

    public void setIfRecharge(Boolean ifRecharge) {
        this.ifRecharge = ifRecharge;
    }

    public Double getPettyCash() {
        return pettyCash;
    }

    public void setPettyCash(Double pettyCash) {
        this.pettyCash = pettyCash;
    }

    public Double getRechargeBeforePettyCash() {
        return rechargeBeforePettyCash;
    }

    public void setRechargeBeforePettyCash(Double rechargeBeforePettyCash) {
        this.rechargeBeforePettyCash = rechargeBeforePettyCash;
    }

    public Boolean getIfUploadScreenshot() {
        return ifUploadScreenshot;
    }

    public void setIfUploadScreenshot(Boolean ifUploadScreenshot) {
        this.ifUploadScreenshot = ifUploadScreenshot;
    }

    public Boolean getIfPrepaidNotification() {
        return ifPrepaidNotification;
    }

    public void setIfPrepaidNotification(Boolean ifPrepaidNotification) {
        this.ifPrepaidNotification = ifPrepaidNotification;
    }

    public Boolean getIfUploadRecharge() {
        return ifUploadRecharge;
    }

    public void setIfUploadRecharge(Boolean ifUploadRecharge) {
        this.ifUploadRecharge = ifUploadRecharge;
    }

    public Double getAfterRechargeTotalMoney() {
        return afterRechargeTotalMoney;
    }

    public void setAfterRechargeTotalMoney(Double afterRechargeTotalMoney) {
        this.afterRechargeTotalMoney = afterRechargeTotalMoney;
    }

    public Double getAfterRechargeBalance() {
        return afterRechargeBalance;
    }

    public void setAfterRechargeBalance(Double afterRechargeBalance) {
        this.afterRechargeBalance = afterRechargeBalance;
    }
}
