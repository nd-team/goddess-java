package com.bjike.goddess.projectprocing.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-31 20:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectData implements Serializable {
    /**
     * 外包单位
     */
    private String outsourcingUnit;
    /**
     * 未初验
     */
    private Integer startCheckCount;
    /**
     * 已初验
     */
    private Integer noStartCheckCount;
    /**
     * 已制作结算资料
     */
    private Integer settleFileCount;
    /**
     * 未制作结算资料
     */
    private Integer noSettleFileCount;
    /**
     * 结算进度A
     */
    private Integer settleProcingACount;
    /**
     * 结算进度B
     */
    private Integer settleProcingBCount;
    /**
     * 结算进度C
     */
    private Integer settleProcingCCount;
    /**
     * 违约金
     */
    private Double betraMoney;
    /**
     * 管理费
     */
    private Double manageFee;
    /**
     * 结算金额
     */
    private Double settleMoney;
    /**
     * 剩余结算金额
     */
    private Double resetSettleMoney;
    /**
     * 到帐金额
     */
    private Double receiveSettleMoney;

    /**
     * 业务方向科目
     */
    private List<CollectDataForBusiness> collectDataForBusinessList;

    public String getOutsourcingUnit() {
        return outsourcingUnit;
    }

    public void setOutsourcingUnit(String outsourcingUnit) {
        this.outsourcingUnit = outsourcingUnit;
    }

    public Integer getStartCheckCount() {
        return startCheckCount;
    }

    public void setStartCheckCount(Integer startCheckCount) {
        this.startCheckCount = startCheckCount;
    }

    public Integer getNoStartCheckCount() {
        return noStartCheckCount;
    }

    public void setNoStartCheckCount(Integer noStartCheckCount) {
        this.noStartCheckCount = noStartCheckCount;
    }

    public Integer getSettleFileCount() {
        return settleFileCount;
    }

    public void setSettleFileCount(Integer settleFileCount) {
        this.settleFileCount = settleFileCount;
    }

    public Integer getNoSettleFileCount() {
        return noSettleFileCount;
    }

    public void setNoSettleFileCount(Integer noSettleFileCount) {
        this.noSettleFileCount = noSettleFileCount;
    }

    public Integer getSettleProcingACount() {
        return settleProcingACount;
    }

    public void setSettleProcingACount(Integer settleProcingACount) {
        this.settleProcingACount = settleProcingACount;
    }

    public Integer getSettleProcingBCount() {
        return settleProcingBCount;
    }

    public void setSettleProcingBCount(Integer settleProcingBCount) {
        this.settleProcingBCount = settleProcingBCount;
    }

    public Integer getSettleProcingCCount() {
        return settleProcingCCount;
    }

    public void setSettleProcingCCount(Integer settleProcingCCount) {
        this.settleProcingCCount = settleProcingCCount;
    }

    public Double getBetraMoney() {
        return betraMoney;
    }

    public void setBetraMoney(Double betraMoney) {
        this.betraMoney = betraMoney;
    }

    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    public Double getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(Double settleMoney) {
        this.settleMoney = settleMoney;
    }

    public Double getResetSettleMoney() {
        return resetSettleMoney;
    }

    public void setResetSettleMoney(Double resetSettleMoney) {
        this.resetSettleMoney = resetSettleMoney;
    }

    public Double getReceiveSettleMoney() {
        return receiveSettleMoney;
    }

    public void setReceiveSettleMoney(Double receiveSettleMoney) {
        this.receiveSettleMoney = receiveSettleMoney;
    }

    public List<CollectDataForBusiness> getCollectDataForBusinessList() {
        return collectDataForBusinessList;
    }

    public void setCollectDataForBusinessList(List<CollectDataForBusiness> collectDataForBusinessList) {
        this.collectDataForBusinessList = collectDataForBusinessList;
    }
}
