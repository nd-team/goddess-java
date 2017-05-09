package com.bjike.goddess.projectmeasure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectmeasure.type.InvoiceForm;
import com.bjike.goddess.projectmeasure.type.PaymentForm;
import com.bjike.goddess.projectmeasure.type.RemitContent;

import javax.persistence.*;


/**
 * 项目费用情况
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectmeasure_projectcoststatus")
public class ProjectCostStatus extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;

    /**
     * 付款形式
     */
    @Column(name = "paymentForm", nullable = false, columnDefinition = "TINYINT(2) COMMENT '付款形式'")
    private PaymentForm paymentForm;

    /**
     * 汇款内容
     */
    @Column(name = "remitContent", nullable = false, columnDefinition = "TINYINT(2) COMMENT '汇款内容'")
    private RemitContent remitContent;

    /**
     * 发票形式
     */
    @Column(name = "invoiceForm", nullable = false, columnDefinition = "TINYINT(2) COMMENT '发票形式'")
    private InvoiceForm invoiceForm;

    /**
     * 税金
     */
    @Column(name = "taxes", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '税金'")
    private Double taxes;

    /**
     * 服务费用
     */
    @Column(name = "serviceCharge", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '服务费用'")
    private Double serviceCharge;

    /**
     * 提成
     */
    @Column(name = "royalties", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '提成'")
    private Double royalties;

    /**
     * 招待费
     */
    @Column(name = "serveCharge", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '招待费'")
    private Double serveCharge;

    /**
     * 需求费用
     */
    @Column(name = "demandCharge", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '需求费用'")
    private Double demandCharge;

    /**
     * 其他
     */
    @Column(name = "other", columnDefinition = "VARCHAR(255) COMMENT '其他'")
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