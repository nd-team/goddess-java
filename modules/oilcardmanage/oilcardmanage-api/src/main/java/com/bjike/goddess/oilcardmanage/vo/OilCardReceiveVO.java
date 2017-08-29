package com.bjike.goddess.oilcardmanage.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;

/**
 * @Author: [Jason]
 * @Date: [17-3-14 下午4:38]
 * @Package:[ com.bjike.goddess.oilcardmanage.vo ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardReceiveVO {

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
     * 主卡/副卡
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
     * 地区
     */
    private String area;

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
    private OilCardBasicVO oilCardBasicVO;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public OilCardBasicVO getOilCardBasicVO() {
        return oilCardBasicVO;
    }

    public void setOilCardBasicVO(OilCardBasicVO oilCardBasicVO) {
        this.oilCardBasicVO = oilCardBasicVO;
    }

    public String getOilCardBasicId() {
        return oilCardBasicId;
    }

    public void setOilCardBasicId(String oilCardBasicId) {
        this.oilCardBasicId = oilCardBasicId;
    }
}
