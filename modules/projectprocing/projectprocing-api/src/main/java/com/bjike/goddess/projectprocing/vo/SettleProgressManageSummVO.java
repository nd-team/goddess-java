package com.bjike.goddess.projectprocing.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 结算进度管理汇总传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 结算进度管理汇总传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleProgressManageSummVO {

    /**
     * 所属项目组
     */
    private String project;

    /**
     * 总合同数量
     */
    private Integer contractTotal;
    /**
     * 总金额/万
     */
    private Double amountTotal;

    /**
     * 已完工数量
     */
    private Integer completedCount;
    /**
     * 已完工金额
     */
    private Double completedAmount;

    /**
     * 未完工数量
     */
    private Integer uncompletedCount;
    /**
     * 未完工金额
     */
    private Double uncompletedAmount;

    /**
     * 已完工已启动结算
     */
    private Integer settleCompletedStart;
    /**
     * 已完工已启动结算金额
     */
    private Double settleCompletedStartAmount;

    /**
     * 已完工未启动结算
     */
    private Integer settleCompletedNoStart;
    /**
     * 已完工未启动结算金额
     */
    private Double settleCompletedNoStartAmount;

    /**
     * 未完工已启动结算
     */
    private Integer settleUnCompletedStart;
    /**
     * 未完工已启动结算金额
     */
    private Double settleUnCompletedStartAmount;

    /**
     * 未完工未结算
     */
    private Integer unfinishedSettled;
    /**
     * 未完工未结算金额
     */
    private Double unfinishedSettledAmount;

    /**
     * 已回款单数
     */
    private Integer returnedItemsNum;
    /**
     * 已回款金额
     */
    private Double returnedItemsAmount;

    /**
     * 未回款单数
     */
    private Integer noReturnSingular;
    /**
     * 未回款金额
     */
    private Double noReturnSingularAmount;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getContractTotal() {
        return contractTotal;
    }

    public void setContractTotal(Integer contractTotal) {
        this.contractTotal = contractTotal;
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    public Double getCompletedAmount() {
        return completedAmount;
    }

    public void setCompletedAmount(Double completedAmount) {
        this.completedAmount = completedAmount;
    }

    public Integer getUncompletedCount() {
        return uncompletedCount;
    }

    public void setUncompletedCount(Integer uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }

    public Double getUncompletedAmount() {
        return uncompletedAmount;
    }

    public void setUncompletedAmount(Double uncompletedAmount) {
        this.uncompletedAmount = uncompletedAmount;
    }

    public Integer getSettleCompletedStart() {
        return settleCompletedStart;
    }

    public void setSettleCompletedStart(Integer settleCompletedStart) {
        this.settleCompletedStart = settleCompletedStart;
    }

    public Double getSettleCompletedStartAmount() {
        return settleCompletedStartAmount;
    }

    public void setSettleCompletedStartAmount(Double settleCompletedStartAmount) {
        this.settleCompletedStartAmount = settleCompletedStartAmount;
    }

    public Integer getSettleCompletedNoStart() {
        return settleCompletedNoStart;
    }

    public void setSettleCompletedNoStart(Integer settleCompletedNoStart) {
        this.settleCompletedNoStart = settleCompletedNoStart;
    }

    public Double getSettleCompletedNoStartAmount() {
        return settleCompletedNoStartAmount;
    }

    public void setSettleCompletedNoStartAmount(Double settleCompletedNoStartAmount) {
        this.settleCompletedNoStartAmount = settleCompletedNoStartAmount;
    }

    public Integer getSettleUnCompletedStart() {
        return settleUnCompletedStart;
    }

    public void setSettleUnCompletedStart(Integer settleUnCompletedStart) {
        this.settleUnCompletedStart = settleUnCompletedStart;
    }

    public Double getSettleUnCompletedStartAmount() {
        return settleUnCompletedStartAmount;
    }

    public void setSettleUnCompletedStartAmount(Double settleUnCompletedStartAmount) {
        this.settleUnCompletedStartAmount = settleUnCompletedStartAmount;
    }

    public Integer getUnfinishedSettled() {
        return unfinishedSettled;
    }

    public void setUnfinishedSettled(Integer unfinishedSettled) {
        this.unfinishedSettled = unfinishedSettled;
    }

    public Double getUnfinishedSettledAmount() {
        return unfinishedSettledAmount;
    }

    public void setUnfinishedSettledAmount(Double unfinishedSettledAmount) {
        this.unfinishedSettledAmount = unfinishedSettledAmount;
    }

    public Integer getReturnedItemsNum() {
        return returnedItemsNum;
    }

    public void setReturnedItemsNum(Integer returnedItemsNum) {
        this.returnedItemsNum = returnedItemsNum;
    }

    public Double getReturnedItemsAmount() {
        return returnedItemsAmount;
    }

    public void setReturnedItemsAmount(Double returnedItemsAmount) {
        this.returnedItemsAmount = returnedItemsAmount;
    }

    public Integer getNoReturnSingular() {
        return noReturnSingular;
    }

    public void setNoReturnSingular(Integer noReturnSingular) {
        this.noReturnSingular = noReturnSingular;
    }

    public Double getNoReturnSingularAmount() {
        return noReturnSingularAmount;
    }

    public void setNoReturnSingularAmount(Double noReturnSingularAmount) {
        this.noReturnSingularAmount = noReturnSingularAmount;
    }
}