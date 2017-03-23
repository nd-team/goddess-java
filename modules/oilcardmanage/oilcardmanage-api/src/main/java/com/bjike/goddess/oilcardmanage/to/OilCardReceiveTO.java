package com.bjike.goddess.oilcardmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;

import javax.validation.constraints.NotNull;

/**
 * 油卡领用传输对象
 *
 * @Author: [Jason]
 * @Date: [17-3-14下午4:37]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardReceiveTO extends BaseTO {
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
     * 领用日期
     */
    @NotNull(message = "领用日期不能为空!")
    private String receiveDate;

    /**
     * 归还日期
     */
    private String returnDate;

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空!")
    private String area;

    /**
     * 领卡人
     */
    private String receiveUser;

    /**
     * 原因
     */
    @NotNull(message = "原因不能为空!")
    private String receiveReason;

    /**
     * 审核人
     */
    @NotNull(message = "审核人不能为空!")
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
}
