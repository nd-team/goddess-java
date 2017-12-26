package com.bjike.goddess.fixedassets.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.fixedassets.enums.*;

/**
 * 汇总业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationBO extends BaseBO {

    /**
     * 类别
     */
    private String fixedAssetType;

    /**
     * 原值
     */
    private Double originalValue;

    /**
     * 月折旧额
     */
    private Double depreciationMonth;

    /**
     * 期末累计折旧
     */
    private Double accumDepreciation;

    /**
     * 本年累计折旧
     */
    private Double yearAccumDepreciation;

    /**
     * 减值准备
     */
    private Double impairmentLoss;

    /**
     * 期末净值
     */
    private Double netFinalValue;

    public String getFixedAssetType() {
        return fixedAssetType;
    }

    public void setFixedAssetType(String fixedAssetType) {
        this.fixedAssetType = fixedAssetType;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public Double getDepreciationMonth() {
        return depreciationMonth;
    }

    public void setDepreciationMonth(Double depreciationMonth) {
        this.depreciationMonth = depreciationMonth;
    }

    public Double getAccumDepreciation() {
        return accumDepreciation;
    }

    public void setAccumDepreciation(Double accumDepreciation) {
        this.accumDepreciation = accumDepreciation;
    }

    public Double getYearAccumDepreciation() {
        return yearAccumDepreciation;
    }

    public void setYearAccumDepreciation(Double yearAccumDepreciation) {
        this.yearAccumDepreciation = yearAccumDepreciation;
    }

    public Double getImpairmentLoss() {
        return impairmentLoss;
    }

    public void setImpairmentLoss(Double impairmentLoss) {
        this.impairmentLoss = impairmentLoss;
    }

    public Double getNetFinalValue() {
        return netFinalValue;
    }

    public void setNetFinalValue(Double netFinalValue) {
        this.netFinalValue = netFinalValue;
    }
}