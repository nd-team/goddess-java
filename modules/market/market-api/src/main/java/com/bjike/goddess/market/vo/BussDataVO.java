package com.bjike.goddess.market.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 根据市场编号获取字段信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 根据市场编号获取字段信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BussDataVO {

    /**
     * 内部项目名称
     */
    private String internalProjectName;

    /**
     * 预估转正金额
     */
    private Double estimateTransferAmount;

    /**
     * 预估市场亏损金额
     */
    private Double estimateMarketLoss;

    public String getInternalProjectName() {
        return internalProjectName;
    }

    public void setInternalProjectName(String internalProjectName) {
        this.internalProjectName = internalProjectName;
    }

    public Double getEstimateTransferAmount() {
        return estimateTransferAmount;
    }

    public void setEstimateTransferAmount(Double estimateTransferAmount) {
        this.estimateTransferAmount = estimateTransferAmount;
    }

    public Double getEstimateMarketLoss() {
        return estimateMarketLoss;
    }

    public void setEstimateMarketLoss(Double estimateMarketLoss) {
        this.estimateMarketLoss = estimateMarketLoss;
    }
}