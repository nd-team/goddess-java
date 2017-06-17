package com.bjike.goddess.moneyside.vo;

/**
 * 投资条件-现金投资表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CashInvestVO {

    /**
     * id
     */
    private String id;
    /**
     * 投资人
     */
    private String investor;

    /**
     * 年收入
     */
    private Double annualIncome;

    /**
     * 信用等级
     */
    private String creditRating;

    /**
     * 固定资产名称
     */
    private String fixedAssetName;

    /**
     * 固定资产价值
     */
    private String fixedAssetValue;

    /**
     * 流动资金
     */
    private Double liquidity;

    /**
     * 担保人
     */
    private String bondsman;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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