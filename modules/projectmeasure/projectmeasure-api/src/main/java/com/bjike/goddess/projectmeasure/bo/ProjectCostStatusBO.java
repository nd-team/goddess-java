package com.bjike.goddess.projectmeasure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectmeasure.type.InvoiceForm;
import com.bjike.goddess.projectmeasure.type.PaymentForm;
import com.bjike.goddess.projectmeasure.type.RemitContent;

/**
 * 项目费用情况业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCostStatusBO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 付款形式
     */
    private PaymentForm paymentForm;

    /**
     * 汇款内容
     */
    private RemitContent remitContent;

    /**
     * 发票形式
     */
    private InvoiceForm invoiceForm;

    /**
     * 税金
     */
    private Double taxes;

    /**
     * 服务费用
     */
    private Double serviceCharge;

    /**
     * 提成
     */
    private Double royalties;

    /**
     * 招待费
     */
    private Double serveCharge;

    /**
     * 需求费用
     */
    private Double demandCharge;

    /**
     * 其他
     */
    private String other;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public PaymentForm getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(PaymentForm paymentForm) {
        this.paymentForm = paymentForm;
    }

    public RemitContent getRemitContent() {
        return remitContent;
    }

    public void setRemitContent(RemitContent remitContent) {
        this.remitContent = remitContent;
    }

    public InvoiceForm getInvoiceForm() {
        return invoiceForm;
    }

    public void setInvoiceForm(InvoiceForm invoiceForm) {
        this.invoiceForm = invoiceForm;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getRoyalties() {
        return royalties;
    }

    public void setRoyalties(Double royalties) {
        this.royalties = royalties;
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Double getDemandCharge() {
        return demandCharge;
    }

    public void setDemandCharge(Double demandCharge) {
        this.demandCharge = demandCharge;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}