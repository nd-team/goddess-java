package com.bjike.goddess.foreigntax.vo;

/**
 * @Author: [xiazhili]
 * @Date: [2017-10-17 14:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CompareCollectVO {
    /**
     * 时间
     */
    private String invoicingTime;

    /**
     * 销项发票不含税金额
     */
    private Double outputNotTax;

    /**
     * 销项发票税金
     */
    private Double outputTax;
    /**
     * 进项发票不含税金额
     */
    private Double incomeNotTax;

    /**
     * 进项发票税金
     */
    private Double incomeTax;

    public String getInvoicingTime() {
        return invoicingTime;
    }

    public void setInvoicingTime(String invoicingTime) {
        this.invoicingTime = invoicingTime;
    }

    public Double getOutputNotTax() {
        return outputNotTax;
    }

    public void setOutputNotTax(Double outputNotTax) {
        this.outputNotTax = outputNotTax;
    }

    public Double getOutputTax() {
        return outputTax;
    }

    public void setOutputTax(Double outputTax) {
        this.outputTax = outputTax;
    }

    public Double getIncomeNotTax() {
        return incomeNotTax;
    }

    public void setIncomeNotTax(Double incomeNotTax) {
        this.incomeNotTax = incomeNotTax;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }
}
