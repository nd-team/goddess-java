package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 投资条件-股权投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_equityinvest")
public class EquityInvest extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 信用等级
     */
    @Column(name = "creditRating", columnDefinition = "VARCHAR(255)   COMMENT '信用等级'")
    private String creditRating;

    /**
     * 固定资产名称
     */
    @Column(name = "fixedAssetName",columnDefinition = "VARCHAR(255)   COMMENT '固定资产名称'")
    private String fixedAssetName;

    /**
     * 固定资产价值
     */
    @Column(name = "fixedAssetValue" , columnDefinition = "VARCHAR(255)   COMMENT '固定资产价值'")
    private String fixedAssetValue;

    /**
     * 流动资金
     */
    @Column(name = "liquidity", columnDefinition = "DECIMAL(10,2)   COMMENT '流动资金'")
    private Double liquidity;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}