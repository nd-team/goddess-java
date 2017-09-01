package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 股权合并
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityCoalescTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 合并方
     */
    private String combined;

    /**
     * 被合并方
     */
    private String beCombined;

    /**
     * 合并方式
     */
    private String coalescWay;

    /**
     * 合并日期
     */
    private String coalescDate;

    /**
     * 占股比例
     */
    private Double percentage;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCombined() {
        return combined;
    }

    public void setCombined(String combined) {
        this.combined = combined;
    }

    public String getBeCombined() {
        return beCombined;
    }

    public void setBeCombined(String beCombined) {
        this.beCombined = beCombined;
    }

    public String getCoalescWay() {
        return coalescWay;
    }

    public void setCoalescWay(String coalescWay) {
        this.coalescWay = coalescWay;
    }

    public String getCoalescDate() {
        return coalescDate;
    }

    public void setCoalescDate(String coalescDate) {
        this.coalescDate = coalescDate;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}