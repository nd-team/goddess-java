package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

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
     * 油卡编号
     */
    @NotBlank(message = "油卡编号不能为空!", groups = {ADD.class, EDIT.class})
    private String oilCardCode;

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空!", groups = {ADD.class, EDIT.class})
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
     * 期初金额
     */
    @NotNull(message = "期初金额不能为空!", groups = {ADD.class, EDIT.class})
    private Double cycleEarlyMoney;

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

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }

    public String getHandlingDate() {
        return handlingDate;
    }

    public void setHandlingDate(String handlingDate) {
        this.handlingDate = handlingDate;
    }

}
