package com.bjike.goddess.oilcardmanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.oilcardmanage.enums.OilCardReceiveResult;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 油卡领用记录
 * @Author: [Jason]
 * @Date: [17-3-14 下午2:21]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.oilcardmanage.entity ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "oilcardmanage_receive")
public class OilCardReceive extends BaseEntity {


    /**
     * 油卡信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oilCardBasic_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '油卡信息'")
    private OilCardBasic oilCardBasic;

    /**
     * 领用日期
     */
    @Column(columnDefinition = "DATETIME COMMENT '领用日期'",nullable = false)
    private LocalDateTime receiveDate;

    /**
     * 归还日期
     */
    @Column(columnDefinition = "DATETIME COMMENT '归还日期'")
    private LocalDateTime returnDate;

    /**
     * 办理人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '办理人'",nullable = false)
    private String transactor;

    /**
     * 办理日期
     */
    @Column(columnDefinition = "DATE COMMENT '办理日期'",nullable = false)
    private LocalDate transactDate;

    /**
     * 油卡领用人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '油卡领用人'",nullable = false)
    private String receiveUser;

    /**
     * 领用原因
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '领用原因'",nullable = false)
    private String receiveReason;

    /**
     * 审核人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '审核人'",nullable = false)
    private String auditUser;

    /**
     * 审核意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '审核意见'")
    private String auditSuggestion;

    /**
     * 审核状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '审核状态'", nullable = false, insertable = false)
    private OilCardReceiveResult auditResult;

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    public OilCardBasic getOilCardBasic() {
        return oilCardBasic;
    }

    public void setOilCardBasic(OilCardBasic oilCardBasic) {
        this.oilCardBasic = oilCardBasic;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
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

    public String getTransactor() {
        return transactor;
    }

    public void setTransactor(String transactor) {
        this.transactor = transactor;
    }

    public LocalDate getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(LocalDate transactDate) {
        this.transactDate = transactDate;
    }
}
