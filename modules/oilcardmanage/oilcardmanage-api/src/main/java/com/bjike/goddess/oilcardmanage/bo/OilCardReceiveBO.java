package com.bjike.goddess.oilcardmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;

/**
 * 油卡领用业务对象
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:37]
 * @Package:[ com.bjike.goddess.oilcardmanage.bo ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardReceiveBO extends BaseBO {

    /**
     * 油卡编号
     */
    private String oilCardCode;

    /**
     * 卡号
     */
    private String oilCardNumber;

    /**
     * 主卡或副卡
     */
    private String mainOrDeputy;

    /**
     * 所属主卡
     */
    private String belongMainCard;
    /**
     * 领用日期
     */
    private String receiveDate;

    /**
     * 归还日期
     */
    private String returnDate;

    /**
     * 领卡人
     */
    private String receiveUser;

    /**
     * 原因
     */
    private String receiveReason;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 审核意见
     */
    private String auditSuggestion;

    /**
     * 审核结果
     */
    private OilCardReceiveResult auditResult;

    /**
     * 备注
     */
    private String remark;

    /**
     * 油卡基本信息
     *
     */
    private OilCardBasicBO oilCardBasicBO;

    /**
     * 油卡id
     */
    private String oilCardBasicId;

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

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }


    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getReceiveReason() {
        return receiveReason;
    }

    public void setReceiveReason(String receiveReason) {
        this.receiveReason = receiveReason;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public String getAuditSuggestion() {
        return auditSuggestion;
    }

    public void setAuditSuggestion(String auditSuggestion) {
        this.auditSuggestion = auditSuggestion;
    }

    public OilCardReceiveResult getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(OilCardReceiveResult auditResult) {
        this.auditResult = auditResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OilCardBasicBO getOilCardBasicBO() {
        return oilCardBasicBO;
    }

    public void setOilCardBasicBO(OilCardBasicBO oilCardBasicBO) {
        this.oilCardBasicBO = oilCardBasicBO;
    }

    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
    }
}
