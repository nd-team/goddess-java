package com.bjike.goddess.foreigntax.bo;

import java.io.Serializable;

/**
 * @Author: [xiazhili]
 * @Date: [17-4-20]
 * @Description: [汇总条件传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public class TaxCollectBO implements Serializable {
    /**
     * 公司
     */
    private String company;

    /**
     * 所属月份
     */
    private String month;

    /**
     * 税种
     */
    private String taxType;

    /**
     * 税率(%)
     */
    private Double rate;

    /**
     * 税金
     */
    private Double tax;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }


}
