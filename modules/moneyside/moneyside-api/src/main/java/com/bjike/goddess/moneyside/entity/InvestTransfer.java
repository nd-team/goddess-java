package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 投资转让
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_investtransfer")
public class InvestTransfer extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "innerProject", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String innerProject;

    /**
     * 结算进度
     */
    @Column(name = "settlementProgress", columnDefinition = "VARCHAR(255)   COMMENT '结算进度'")
    private String settlementProgress;

    /**
     * 转让时间
     */
    @Column(name = "transferTime", columnDefinition = "DATE   COMMENT '转让时间'")
    private LocalDate transferTime;

    /**
     * 转让人
     */
    @Column(name = "transferor", columnDefinition = "VARCHAR(255)   COMMENT '转让人'")
    private String transferor;

    /**
     * 协议投资金额
     */
    @Column(name = "amountAgreement", columnDefinition = "DECIMAL(10,2)   COMMENT '协议投资金额'")
    private Double amountAgreement;

    /**
     * 累计投资金额
     */
    @Column(name = "accumulatedInvestAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '累计投资金额'")
    private Double accumulatedInvestAmount;

    /**
     * 剩余投资金额
     */
    @Column(name = "surplusAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '剩余投资金额'")
    private Double surplusAmount;

    /**
     * 被转让人
     */
    @Column(name = "transferee", columnDefinition = "VARCHAR(255)   COMMENT '被转让人'")
    private String transferee;

    /**
     * 需投资金额
     */
    @Column(name = "needInvestAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '需投资金额'")
    private Double needInvestAmount;

    /**
     * 是否备案
     */
    @Column(name = "is_filing", columnDefinition = "TINYINT(2)   COMMENT '是否备案'")
    private Boolean filing;

    /**
     * 备案时间
     */
    @Column(name = "filingTime", columnDefinition = "DATE   COMMENT '备案时间'")
    private LocalDate filingTime;


    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getSettlementProgress() {
        return settlementProgress;
    }

    public void setSettlementProgress(String settlementProgress) {
        this.settlementProgress = settlementProgress;
    }

    public LocalDate getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalDate transferTime) {
        this.transferTime = transferTime;
    }

    public String getTransferor() {
        return transferor;
    }

    public void setTransferor(String transferor) {
        this.transferor = transferor;
    }

    public Double getAmountAgreement() {
        return amountAgreement;
    }

    public void setAmountAgreement(Double amountAgreement) {
        this.amountAgreement = amountAgreement;
    }

    public Double getAccumulatedInvestAmount() {
        return accumulatedInvestAmount;
    }

    public void setAccumulatedInvestAmount(Double accumulatedInvestAmount) {
        this.accumulatedInvestAmount = accumulatedInvestAmount;
    }

    public Double getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Double surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public String getTransferee() {
        return transferee;
    }

    public void setTransferee(String transferee) {
        this.transferee = transferee;
    }

    public Double getNeedInvestAmount() {
        return needInvestAmount;
    }

    public void setNeedInvestAmount(Double needInvestAmount) {
        this.needInvestAmount = needInvestAmount;
    }

    public Boolean getFiling() {
        return filing;
    }

    public void setFiling(Boolean filing) {
        this.filing = filing;
    }

    public LocalDate getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(LocalDate filingTime) {
        this.filingTime = filingTime;
    }
}