package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 冻结股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FreezeEquityTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 股份数量
     */
    private Integer totalHoldNum;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 冻结日期
     */
    private String freezeDate;

    /**
     * 冻结原因
     */
    private String freezeWhy;

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

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getTotalHoldNum() {
        return totalHoldNum;
    }

    public void setTotalHoldNum(Integer totalHoldNum) {
        this.totalHoldNum = totalHoldNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(String freezeDate) {
        this.freezeDate = freezeDate;
    }

    public String getFreezeWhy() {
        return freezeWhy;
    }

    public void setFreezeWhy(String freezeWhy) {
        this.freezeWhy = freezeWhy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}