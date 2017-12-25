package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 记账凭证部分数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证部分数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PartBO extends BaseBO {

    /**
     * 油卡充值
     */
    private Double oilRecharge;

    /**
     * 房租
     */
    private Double rent;

    /**
     * 社保
     */
    private Double socialSecurity;

    /**
     * 员工工资
     */
    private Double staffWage;

    /**
     * 办公费
     */
    private Double office;

    /**
     * 市场费
     */
    private Double marketCost;

    /**
     * 税金
     */
    private Double tax;

    public Double getOilRecharge() {
        return oilRecharge;
    }

    public void setOilRecharge(Double oilRecharge) {
        this.oilRecharge = oilRecharge;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(Double socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Double getStaffWage() {
        return staffWage;
    }

    public void setStaffWage(Double staffWage) {
        this.staffWage = staffWage;
    }

    public Double getOffice() {
        return office;
    }

    public void setOffice(Double office) {
        this.office = office;
    }

    public Double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(Double marketCost) {
        this.marketCost = marketCost;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}