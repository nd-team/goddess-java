package com.bjike.goddess.shareholdersmanage.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 股东开户业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareRosterDetailVO {

    /**
     * 分红日期
     */
    private String shareOutBonusDate;

    /**
     * 分红名称
     */
    private String shareOutBonusName;
    /**
     * 分红所得
     */
    private Double shareOutBonusAmount;
    /**
     * 所得税
     */
    private Double incomeTax;
    /**
     * 税后利润
     */
    private Double afterTaxProfits;
    /**
     * 备注
     */
    private String remark;

    public String getShareOutBonusDate() {
        return shareOutBonusDate;
    }

    public void setShareOutBonusDate(String shareOutBonusDate) {
        this.shareOutBonusDate = shareOutBonusDate;
    }

    public String getShareOutBonusName() {
        return shareOutBonusName;
    }

    public void setShareOutBonusName(String shareOutBonusName) {
        this.shareOutBonusName = shareOutBonusName;
    }

    public Double getShareOutBonusAmount() {
        return shareOutBonusAmount;
    }

    public void setShareOutBonusAmount(Double shareOutBonusAmount) {
        this.shareOutBonusAmount = shareOutBonusAmount;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getAfterTaxProfits() {
        return afterTaxProfits;
    }

    public void setAfterTaxProfits(Double afterTaxProfits) {
        this.afterTaxProfits = afterTaxProfits;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}