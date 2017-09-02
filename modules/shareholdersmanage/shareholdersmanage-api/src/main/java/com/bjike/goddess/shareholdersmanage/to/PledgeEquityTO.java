package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 质押股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PledgeEquityTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 出质人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "出质人不能为空")
    private String investor;

    /**
     * 债权人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "债权人不能为空")
    private String creditor;

    /**
     * 担保人
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "担保人不能为空")
    private String guarantor;

    /**
     * 股权类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "股权类型不能为空")
    private String equityType;

    /**
     * 股权数量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "股权数量不能为空")
    private Integer holdNum;

    /**
     * 金额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "金额不能为空")
    private Double amount;

    /**
     * 出资日期
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "出资日期不能为空")
    private String pledgeDate;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(String guarantor) {
        this.guarantor = guarantor;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPledgeDate() {
        return pledgeDate;
    }

    public void setPledgeDate(String pledgeDate) {
        this.pledgeDate = pledgeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}