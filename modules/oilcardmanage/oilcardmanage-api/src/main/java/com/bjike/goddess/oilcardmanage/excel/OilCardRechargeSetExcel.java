package com.bjike.goddess.oilcardmanage.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-18 16:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardRechargeSetExcel {
    /**
     * 卡号
     */
    @ExcelHeader(name = "卡号",notNull = true)
    private String  oilCardNumber;

    /**
     * 主卡/副卡
     */
    @ExcelHeader(name = "主卡/副卡",notNull = true)
    private String mainOrDeputy;

    /**
     * 所属主卡
     */
    @ExcelHeader(name = "所属主卡",notNull = true)
    private String belongMainCard;

    /**
     * 油卡编号
     */
    @ExcelHeader(name = "油卡编号",notNull = true)
    private String oilCardCode;

    /**
     * 密码
     */
    @ExcelHeader(name = "密码",notNull = true)
    private String cardPassWord;

    /**
     * 使用地区
     */
    @ExcelHeader(name = "使用地区",notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门",notNull = true)
    private String department;

    /**
     * 领用状态
     */
    @ExcelHeader(name = "领用状态")
    private OilCardStatus cardStatus;

    /**
     * 更新时间
     */
    @ExcelHeader(name = "更新时间",notNull = true)
    private String updateTime;

    /**
     * 油卡余额
     */
    @ExcelHeader(name = "油卡余额",notNull = true)
    private Double balance;

    /**
     * 充值前备用金
     */
    @ExcelHeader(name = "充值前备用金",notNull = true)
    private Double rechargeBeforePettyCash;

    /**
     * 是否上传截图附件
     */
    @ExcelHeader(name = "是否上传截图附件",notNull = true)
    private Boolean ifUploadScreenshot;

    /**
     * 是否充值通报
     */
    @ExcelHeader(name = "是否充值通报",notNull = true)
    private Boolean ifPrepaidNotification;

    /**
     * 是否充值
     */
    @ExcelHeader(name = "是否充值",notNull = true)
    private Boolean ifRecharge;

    /**
     * 充值金额
     */
    @ExcelHeader(name = "充值金额",notNull = true)
    private Double rechargeMoney;

    /**
     * 充值完成时间
     */
    @ExcelHeader(name = "充值完成时间",notNull = true)
    private String rechargeDate;

    /**
     * 是否上传充值后截图附件
     */
    @ExcelHeader(name = "是否上传充值后截图附件",notNull = true)
    private Boolean ifUploadRecharge;

    /**
     * 充值后总金额
     */
    @ExcelHeader(name = "充值后总金额",notNull = true)
    private Double afterRechargeTotalMoney;

    /**
     * 充值后油卡余额
     */
    @ExcelHeader(name = "充值后油卡余额",notNull = true)
    private Double afterRechargeBalance;

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

    public String getBelongMainCard() {
        return belongMainCard;
    }

    public void setBelongMainCard(String belongMainCard) {
        this.belongMainCard = belongMainCard;
    }

    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public String getCardPassWord() {
        return cardPassWord;
    }

    public void setCardPassWord(String cardPassWord) {
        this.cardPassWord = cardPassWord;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public OilCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(OilCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public Boolean getIfRecharge() {
        return ifRecharge;
    }

    public void setIfRecharge(Boolean ifRecharge) {
        this.ifRecharge = ifRecharge;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
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
