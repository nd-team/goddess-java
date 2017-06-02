package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectmeasure.type.InvoiceForm;
import com.bjike.goddess.projectmeasure.type.PaymentForm;
import com.bjike.goddess.projectmeasure.type.RemitContent;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目费用情况
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCostStatusTO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 付款形式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "付款形式不能为空")
    private PaymentForm paymentForm;

    /**
     * 汇款内容
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "汇款内容不能为空")
    private RemitContent remitContent;

    /**
     * 发票形式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "发票形式不能为空")
    private InvoiceForm invoiceForm;

    /**
     * 税金
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "税金不能为空")
    private Double taxes;

    /**
     * 服务费用
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "服务费用不能为空")
    private Double serviceCharge;

    /**
     * 提成
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "提成不能为空")
    private Double royalties;

    /**
     * 招待费
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "招待费不能为空")
    private Double serveCharge;

    /**
     * 需求费用
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "需求费用不能为空")
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