package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 分红情况查询业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红情况查询业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusCaseBO extends BaseBO {

    /**
     * 分红日期
     */
    private String shareOutBonusDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 分红名称
     */
    private String shareOutBonusName;

    /**
     * 股东姓名
     */
    private String shareholderName;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 持股数量
     */
    private Integer holdNum;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareOutBonusName() {
        return shareOutBonusName;
    }

    public void setShareOutBonusName(String shareOutBonusName) {
        this.shareOutBonusName = shareOutBonusName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
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