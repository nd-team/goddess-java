package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 投资条件-现金投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_cashinvest")
public class CashInvest extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 年收入
     */
    @Column(name = "annualIncome",  columnDefinition = "DECIMAL(10,2)   COMMENT '年收入'")
    private Double annualIncome;

    /**
     * 信用等级
     */
    @Column(name = "creditRating", columnDefinition = "VARCHAR(255)   COMMENT '信用等级'")
    private String creditRating;

    /**
     * 固定资产名称
     */
    @Column(name = "fixedAssetName", columnDefinition = "VARCHAR(255)   COMMENT '固定资产名称'")
    private String fixedAssetName;

    /**
     * 固定资产价值
     */
    @Column(name = "fixedAssetValue", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '固定资产价值'")
    private String fixedAssetValue;

    /**
     * 流动资金
     */
    @Column(name = "liquidity", columnDefinition = "DECIMAL(10,2)   COMMENT '流动资金'")
    private Double liquidity;

    /**
     * 担保人
     */
    @Column(name = "bondsman",  columnDefinition = "VARCHAR(255)   COMMENT '担保人'")
    private String bondsman;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getFixedAssetName() {
        return fixedAssetName;
    }

    public void setFixedAssetName(String fixedAssetName) {
        this.fixedAssetName = fixedAssetName;
    }

    public String getFixedAssetValue() {
        return fixedAssetValue;
    }

    public void setFixedAssetValue(String fixedAssetValue) {
        this.fixedAssetValue = fixedAssetValue;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Double liquidity) {
        this.liquidity = liquidity;
    }

    public String getBondsman() {
        return bondsman;
    }

    public void setBondsman(String bondsman) {
        this.bondsman = bondsman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}