package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 实收资本
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 实收资本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostPaidMoneyTO extends BaseTO {

    /**
     * 实收资本
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "实收资本名不能为空")
    private String costPaidName;
    /**
     * 实收资本合计
     */
    private Double paidCapitalSum;

    /**
     * 实收资本十日
     */
    private Double paidCapitalTen;

    /**
     * 实收资本十五日
     */
    private Double paidCapitalFift;

    /**
     * 实收资本二十日
     */
    private Double paidCapitalTwtenty;

    /**
     * 实收资本三十日
     */
    private Double paidCapitalThirty;

    public String getCostPaidName() {
        return costPaidName;
    }

    public void setCostPaidName(String costPaidName) {
        this.costPaidName = costPaidName;
    }

    public Double getPaidCapitalSum() {
        return paidCapitalSum;
    }

    public void setPaidCapitalSum(Double paidCapitalSum) {
        this.paidCapitalSum = paidCapitalSum;
    }

    public Double getPaidCapitalTen() {
        return paidCapitalTen;
    }

    public void setPaidCapitalTen(Double paidCapitalTen) {
        this.paidCapitalTen = paidCapitalTen;
    }

    public Double getPaidCapitalFift() {
        return paidCapitalFift;
    }

    public void setPaidCapitalFift(Double paidCapitalFift) {
        this.paidCapitalFift = paidCapitalFift;
    }

    public Double getPaidCapitalTwtenty() {
        return paidCapitalTwtenty;
    }

    public void setPaidCapitalTwtenty(Double paidCapitalTwtenty) {
        this.paidCapitalTwtenty = paidCapitalTwtenty;
    }

    public Double getPaidCapitalThirty() {
        return paidCapitalThirty;
    }

    public void setPaidCapitalThirty(Double paidCapitalThirty) {
        this.paidCapitalThirty = paidCapitalThirty;
    }
}