package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 优惠备案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_discountrecord")
public class DiscountRecord extends BaseEntity {

    /**
     * 减免审批事项
     */
    @Column(name = "approvalItems", columnDefinition = "VARCHAR(255)   COMMENT '减免审批事项'")
    private String approvalItems;

    /**
     * 减免税类型
     */
    @Column(name = "taxDeductibleType", columnDefinition = "VARCHAR(255)   COMMENT '减免税类型'")
    private String taxDeductibleType;

    /**
     * 申报日期
     */
    @Column(name = "declarationDate", columnDefinition = "DATE   COMMENT '申报日期'")
    private String declarationDate;

    /**
     * 减免税种
     */
    @Column(name = "taxRelief", columnDefinition = "VARCHAR(255)   COMMENT '减免税种'")
    private String taxRelief;

    /**
     * 免征/减征期限起
     */
    @Column(name = "reductionStart", columnDefinition = "DATE   COMMENT '免征/减征期限起'")
    private String reductionStart;

    /**
     * 免征/减征期限止
     */
    @Column(name = "reductionEnd", columnDefinition = "DATE   COMMENT '免征/减征期限止'")
    private String reductionEnd;

    /**
     * 减征税率(%)
     */
    @Column(name = "reductionRate", columnDefinition = "DECIMAL(10,2)   COMMENT '减征税率(%)'")
    private Double reductionRate;

    /**
     * 减征幅度
     */
    @Column(name = "reductionRange", columnDefinition = "DECIMAL(10,2)   COMMENT '减征幅度'")
    private Double reductionRange;

    /**
     * 减征额
     */
    @Column(name = "reductionFrontal", columnDefinition = "DECIMAL(10,2)   COMMENT '减征额'")
    private Double reductionFrontal;


    public String getApprovalItems() {
        return approvalItems;
    }

    public void setApprovalItems(String approvalItems) {
        this.approvalItems = approvalItems;
    }

    public String getTaxDeductibleType() {
        return taxDeductibleType;
    }

    public void setTaxDeductibleType(String taxDeductibleType) {
        this.taxDeductibleType = taxDeductibleType;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getTaxRelief() {
        return taxRelief;
    }

    public void setTaxRelief(String taxRelief) {
        this.taxRelief = taxRelief;
    }

    public String getReductionStart() {
        return reductionStart;
    }

    public void setReductionStart(String reductionStart) {
        this.reductionStart = reductionStart;
    }

    public String getReductionEnd() {
        return reductionEnd;
    }

    public void setReductionEnd(String reductionEnd) {
        this.reductionEnd = reductionEnd;
    }

    public Double getReductionRate() {
        return reductionRate;
    }

    public void setReductionRate(Double reductionRate) {
        this.reductionRate = reductionRate;
    }

    public Double getReductionRange() {
        return reductionRange;
    }

    public void setReductionRange(Double reductionRange) {
        this.reductionRange = reductionRange;
    }

    public Double getReductionFrontal() {
        return reductionFrontal;
    }

    public void setReductionFrontal(Double reductionFrontal) {
        this.reductionFrontal = reductionFrontal;
    }
}