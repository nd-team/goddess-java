package com.bjike.goddess.foreigntax.vo;

/**
 * 优惠备案表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DiscountRecordVO {

    /**
     * id
     */
    private String id;
    /**
     * 减免审批事项
     */
    private String approvalItems;

    /**
     * 减免税类型
     */
    private String taxDeductibleType;

    /**
     * 申报日期
     */
    private String declarationDate;

    /**
     * 减免税种
     */
    private String taxRelief;

    /**
     * 免征/减征期限起
     */
    private String reductionStart;

    /**
     * 免征/减征期限止
     */
    private String reductionEnd;

    /**
     * 减征税率(%)
     */
    private Double reductionRate;

    /**
     * 减征幅度
     */
    private Double reductionRange;

    /**
     * 减征额
     */
    private Double reductionFrontal;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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