package com.bjike.goddess.foreigntax.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 进项发票业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IncomeInvoiceBO extends BaseBO {

    /**
     * 开票时间
     */
    private String invoicingTime;

    /**
     * 公司
     */
    private String company;
    /**
     * 税号
     */
    private String taxCode;

    /**
     * 主要商品名称
     */
    private String mainName;

    /**
     * 税率
     */
    private Double rate;
    /**
     * 不含税金额
     */
    private Double notTax;

    /**
     * 税金
     */
    private Double tax;

    /**
     * 签收人
     */
    private String signer;

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getInvoicingTime() {
        return invoicingTime;
    }

    public void setInvoicingTime(String invoicingTime) {
        this.invoicingTime = invoicingTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getNotTax() {
        return notTax;
    }

    public void setNotTax(Double notTax) {
        this.notTax = notTax;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}