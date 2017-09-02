package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 分红明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:55 ]
 * @Description: [ 分红明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusDetailTO extends BaseTO {

    /**
     * 股东姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股东姓名不能为空")
    private String shareholderName;

    /**
     * 分红比例
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "分红比例不能为空")
    private Double shareOutBonusPropor;

    /**
     * 分红额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "分红额不能为空")
    private Double shareOutBonusAmount;

    /**
     * 所得税比例
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "所得税比例不能为空")
    private Double incomeTaxPropor;

    /**
     * 所得税
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "所得税不能为空")
    private Double incomeTax;

    /**
     * 税后利润
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "税后利润不能为空")
    private Double afterTaxProfits;

    /**
     * 分红管理id
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分红管理id不能为空")
    private String shareOutBonusManageId;


    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public Double getShareOutBonusPropor() {
        return shareOutBonusPropor;
    }

    public void setShareOutBonusPropor(Double shareOutBonusPropor) {
        this.shareOutBonusPropor = shareOutBonusPropor;
    }

    public Double getShareOutBonusAmount() {
        return shareOutBonusAmount;
    }

    public void setShareOutBonusAmount(Double shareOutBonusAmount) {
        this.shareOutBonusAmount = shareOutBonusAmount;
    }

    public Double getIncomeTaxPropor() {
        return incomeTaxPropor;
    }

    public void setIncomeTaxPropor(Double incomeTaxPropor) {
        this.incomeTaxPropor = incomeTaxPropor;
    }

    public Double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getAfterTaxProfits() {
        return afterTaxProfits;
    }

    public void setAfterTaxProfits(Double afterTaxProfits) {
        this.afterTaxProfits = afterTaxProfits;
    }

    public String getShareOutBonusManageId() {
        return shareOutBonusManageId;
    }

    public void setShareOutBonusManageId(String shareOutBonusManageId) {
        this.shareOutBonusManageId = shareOutBonusManageId;
    }
}