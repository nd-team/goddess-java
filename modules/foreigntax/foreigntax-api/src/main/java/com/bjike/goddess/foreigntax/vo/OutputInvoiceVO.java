package com.bjike.goddess.foreigntax.vo;

/**
 * 销项发票表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:43 ]
 * @Description: [ 销项发票表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutputInvoiceVO {

    /**
     * id
     */
    private String id;
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

    /**
     * 开票人
     */
    private String drawer;
    /**
     * 作废标志
     */
    private String invalidSign;
    /**
     * 作废日期
     */
    private String invalidDate;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getInvalidSign() {
        return invalidSign;
    }

    public void setInvalidSign(String invalidSign) {
        this.invalidSign = invalidSign;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }
}