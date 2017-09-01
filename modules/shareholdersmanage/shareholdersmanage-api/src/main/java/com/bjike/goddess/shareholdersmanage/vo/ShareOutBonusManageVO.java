package com.bjike.goddess.shareholdersmanage.vo;

/**
 * 分红管理表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusManageVO {

    /**
     * id
     */
    private String id;
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
     * 股权类型
     */
    private String equityType;

    /**
     * 共派股
     */
    private Integer totalSentStocks;
    /**
     * 每股分红
     */
    private Double perShareDividends;
    /**
     * 共分红
     */
    private Double totalShareOutBonus;

    /**
     * 共缴所得税
     */
    private Double totalIncomeTax;

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

    public Double getPerShareDividends() {
        return perShareDividends;
    }

    public void setPerShareDividends(Double perShareDividends) {
        this.perShareDividends = perShareDividends;
    }

    public Double getTotalIncomeTax() {
        return totalIncomeTax;
    }

    public void setTotalIncomeTax(Double totalIncomeTax) {
        this.totalIncomeTax = totalIncomeTax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}