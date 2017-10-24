package com.bjike.goddess.foreigntax.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 优惠备案数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DiscountRecordDTO extends BaseDTO {
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
}