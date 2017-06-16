package com.bjike.goddess.moneyside.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 投资转让业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvestTransferBO extends BaseBO {

    /**
     * 项目名称
     */
    private String innerProject;

    /**
     * 结算进度
     */
    private String settlementProgress;

    /**
     * 转让时间
     */
    private String transferTime;

    /**
     * 转让人
     */
    private String transferor;

    /**
     * 协议投资金额
     */
    private Double amountAgreement;

    /**
     * 累计投资金额
     */
    private Double accumulatedInvestAmount;

    /**
     * 剩余投资金额
     */
    private Double surplusAmount;

    /**
     * 被转让人
     */
    private String transferee;

    /**
     * 需投资金额
     */
    private Double needInvestAmount;

    /**
     * 是否备案
     */
    private Boolean filing;

    /**
     * 备案时间
     */
    private String filingTime;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



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

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
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

    public String getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}