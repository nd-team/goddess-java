package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 分红明细业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusDetailBO extends BaseBO {

    /**
     * 股东姓名
     */
    private String shareholderName;

    /**
     * 共分红
     */
    private Double totalShareOutBonus;

    /**
     * 分红比例
     */
    private Double shareOutBonusPropor;

    /**
     * 分红额
     */
    private Double shareOutBonusAmount;

    /**
     * 所得税比例
     */
    private Double incomeTaxPropor;

    /**
     * 所得税
     */
    private Double incomeTax;

    /**
     * 税后利润
     */
    private Double AfterTaxProfits;

    /**
     * 分红管理id
     */
    private String ShareOutBonusManageId;


    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public Double getTotalShareOutBonus() {
        return totalShareOutBonus;
    }

    public void setTotalShareOutBonus(Double totalShareOutBonus) {
        this.totalShareOutBonus = totalShareOutBonus;
    }

    public Double getShareOutBonusPropor() {
        return shareOutBonusPropor;
    }

    public void setShareOutBonusPropor(Double shareOutBonusPropor) {
        this.shareOutBonusPropor = shareOutBonusPropor;
    }

    public Double getShareOutBonusAmount() {
        return shareOutBonusAmount;
    }

    public void setShareOutBonusAmount(Double shareOutBonusAmount) {
        this.shareOutBonusAmount = shareOutBonusAmount;
    }

    public Double getIncomeTaxPropor() {
        return incomeTaxPropor;
    }

    public void setIncomeTaxPropor(Double incomeTaxPropor) {
        this.incomeTaxPropor = incomeTaxPropor;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getAfterTaxProfits() {
        return AfterTaxProfits;
    }

    public void setAfterTaxProfits(Double AfterTaxProfits) {
        this.AfterTaxProfits = AfterTaxProfits;
    }

    public String getShareOutBonusManageId() {
        return ShareOutBonusManageId;
    }

    public void setShareOutBonusManageId(String ShareOutBonusManageId) {
        this.ShareOutBonusManageId = ShareOutBonusManageId;
    }
}