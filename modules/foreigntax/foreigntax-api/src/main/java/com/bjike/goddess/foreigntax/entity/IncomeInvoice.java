package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 进项发票
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_incomeinvoice")
public class IncomeInvoice extends BaseEntity {

    /**
     * 开票时间
     */
    @Column(name = "invoicingTime",  columnDefinition = "DATE   COMMENT '开票时间'")
    private LocalDate invoicingTime;

    /**
     * 公司
     */
    @Column(name = "company",  columnDefinition = "VARCHAR(255)   COMMENT '公司'")
    private String company;
    /**
     * 税号
     */
    @Column(name = "taxCode",  columnDefinition = "VARCHAR(255)   COMMENT '税号'")
    private String taxCode;

    /**
     * 主要商品名称
     */
    @Column(name = "mainName", columnDefinition = "VARCHAR(255)   COMMENT '主要商品名称'")
    private String mainName;
    /**
     * 税率
     */
    @Column(name = "rate", columnDefinition = "DECIMAL(10,2)   COMMENT '税率'")
    private Double rate;

    /**
     * 不含税金额
     */
    @Column(name = "notTax",  columnDefinition = "DECIMAL(10,2)   COMMENT '不含税金额'")
    private Double notTax;

    /**
     * 税金
     */
    @Column(name = "tax",  columnDefinition = "DECIMAL(10,2)   COMMENT '税金'")
    private Double tax;

    /**
     * 签收人
     */
    @Column(name = "signer",  columnDefinition = "VARCHAR(255)   COMMENT '签收人'")
    private String signer;

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public LocalDate getInvoicingTime() {
        return invoicingTime;
    }

    public void setInvoicingTime(LocalDate invoicingTime) {
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