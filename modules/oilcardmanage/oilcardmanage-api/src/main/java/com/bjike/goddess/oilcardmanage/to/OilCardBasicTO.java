package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardStatus;

import javax.validation.constraints.NotNull;

/**
 * 油卡基础信息传输对象
 *
 * @Author: [Jason]
 * @Date: [17-3-13 下午5:03]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardBasicTO extends BaseTO {

    /**
     * 数据状态
     */
    private Status status;

    /**
     * 油卡编号
     */
    @NotNull(message = "油卡编号不能为空!")
    private String oilCardCode;

    /**
     * 卡号
     */
    @NotNull(message = "卡号不能为空!")
    private String oilCardNumber;

    /**
     * 主卡/副卡
     */
    private String mainOrDeputy;

    /**
     * 所属主卡
     */
    private String belongMainCard;

    /**
     * 办理人
     */
    private String handlingUser;

    /**
     * 办理日期
     */
    private String handlingDate;

    /**
     * 密码
     */
    private String cardPassWord;

    /**
     * 使用地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 油卡状态
     */
    private OilCardStatus cardStatus;

    /**
     * 使用状态更新时间
     */
    private String updateStatusTime;

    /**
     * 期初金额
     */
    private Double cycleEarlyMoney;

    /**
     * 余额
     */
    private Double balance;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getBelongMainCard() {
        return belongMainCard;
    }

    public void setBelongMainCard(String belongMainCard) {
        this.belongMainCard = belongMainCard;
    }

    public String getHandlingUser() {
        return handlingUser;
    }

    public void setHandlingUser(String handlingUser) {
        this.handlingUser = handlingUser;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public OilCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(OilCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getHandlingDate() {
        return handlingDate;
    }

    public void setHandlingDate(String handlingDate) {
        this.handlingDate = handlingDate;
    }

    public String getUpdateStatusTime() {
        return updateStatusTime;
    }

    public void setUpdateStatusTime(String updateStatusTime) {
        this.updateStatusTime = updateStatusTime;
    }

}
