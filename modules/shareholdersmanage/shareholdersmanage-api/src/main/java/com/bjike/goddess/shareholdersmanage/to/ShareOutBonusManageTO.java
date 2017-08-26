package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 分红管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusManageTO extends BaseTO {

    /**
     * 分红日期
     */
    private String ShareOutBonusDate;

    /**
     * 地区
     */
    private String area;

    /**
     * 分红名称
     */
    private String ShareOutBonusName;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 共派股Double
     */
    private Integer totalSentStocks;

    /**
     * 共分红
     */
    private Double totalShareOutBonus;

    /**
     * 共缴所得税
     */
    private Double TotalIncomeTax;

    /**
     * 备注
     */
    private String remark;


    public String getShareOutBonusDate() {
        return ShareOutBonusDate;
    }

    public void setShareOutBonusDate(String shareOutBonusDate) {
        ShareOutBonusDate = shareOutBonusDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareOutBonusName() {
        return ShareOutBonusName;
    }

    public void setShareOutBonusName(String ShareOutBonusName) {
        this.ShareOutBonusName = ShareOutBonusName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalSentStocks() {
        return totalSentStocks;
    }

    public void setTotalSentStocks(Integer totalSentStocks) {
        this.totalSentStocks = totalSentStocks;
    }

    public Double getTotalShareOutBonus() {
        return totalShareOutBonus;
    }

    public void setTotalShareOutBonus(Double totalShareOutBonus) {
        this.totalShareOutBonus = totalShareOutBonus;
    }

    public Double getTotalIncomeTax() {
        return TotalIncomeTax;
    }

    public void setTotalIncomeTax(Double TotalIncomeTax) {
        this.TotalIncomeTax = TotalIncomeTax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}