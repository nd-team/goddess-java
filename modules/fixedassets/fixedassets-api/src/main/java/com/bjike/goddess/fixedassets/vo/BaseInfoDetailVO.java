package com.bjike.goddess.fixedassets.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 基本信息明细业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BaseInfoDetailVO {

    /**
     * 固定资产名称
     */
    private String fixedAssetName;

    /**
     * 使用部门
     */
    private String department;

    /**
     * 月折旧率%
     */
    private Double depreciationMonthRate;

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

    public String getFixedAssetName() {
        return fixedAssetName;
    }

    public void setFixedAssetName(String fixedAssetName) {
        this.fixedAssetName = fixedAssetName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getDepreciationMonthRate() {
        return depreciationMonthRate;
    }

    public void setDepreciationMonthRate(Double depreciationMonthRate) {
        this.depreciationMonthRate = depreciationMonthRate;
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