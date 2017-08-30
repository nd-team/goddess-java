package com.bjike.goddess.foreigntax.to;

/**
 * 汇总条件传输对象
 *
 * @Author: [xiazhili]
 * @Date: [17-4-20]
 * @Description: [汇总条件传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public class CollectTo {
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
}
