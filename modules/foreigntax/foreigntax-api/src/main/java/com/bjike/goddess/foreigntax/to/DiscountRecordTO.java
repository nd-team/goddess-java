package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 优惠备案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DiscountRecordTO extends BaseTO {

    /**
     * 减免审批事项
     */
    @NotBlank(message = "减免审批事项不能为空",groups = {ADD.class, EDIT.class})
    private String approvalItems;

    /**
     * 减免税类型
     */
    @NotBlank(message = "减免税类型不能为空",groups = {ADD.class, EDIT.class})
    private String taxDeductibleType;

    /**
     * 申报日期
     */
    @NotBlank(message = "申报日期不能为空",groups = {ADD.class, EDIT.class})
    private String declarationDate;

    /**
     * 减免税种
     */
    @NotBlank(message = "减免税种不能为空",groups = {ADD.class, EDIT.class})
    private String taxRelief;

    /**
     * 免征/减征期限起
     */
    @NotBlank(message = "免征/减征期限起不能为空",groups = {ADD.class, EDIT.class})
    private String reductionStart;

    /**
     * 免征/减征期限止
     */
    @NotBlank(message = "免征/减征期限止不能为空",groups = {ADD.class, EDIT.class})
    private String reductionEnd;

    /**
     * 减征税率(%)
     */
    @NotNull(message = "减征税率(%)不能为空",groups = {ADD.class, EDIT.class})
    private Double reductionRate;

    /**
     * 减征幅度
     */
    @NotNull(message = "减征幅度不能为空",groups = {ADD.class, EDIT.class})
    private Double reductionRange;

    /**
     * 减征额
     */
    @NotNull(message = "减征额不能为空",groups = {ADD.class, EDIT.class})
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