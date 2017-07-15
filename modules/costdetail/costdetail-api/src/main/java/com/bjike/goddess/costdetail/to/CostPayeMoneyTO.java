package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 应交税金
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 应交税金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostPayeMoneyTO extends BaseTO {

    /**
     * 应交税金
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "应交税金名不能为空")
    private String costPayeName;
    /**
     * 应交税金合计
     */
    private Double payableTaxSum;

    /**
     * 应交税金十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "应交税金十日不能为空")
    private Double payableTaxTen;

    /**
     * 应交税金十五日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "应交税金十五日不能为空")
    private Double payableTaxFift;

    /**
     * 应交税金二十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "应交税金二十日不能为空")
    private Double payableTaxTwtenty;

    /**
     * 应交税金三十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "应交税金三十日不能为空")
    private Double payableTaxThirty;

    public String getCostPayeName() {
        return costPayeName;
    }

    public void setCostPayeName(String costPayeName) {
        this.costPayeName = costPayeName;
    }

    public Double getPayableTaxSum() {
        return payableTaxSum;
    }

    public void setPayableTaxSum(Double payableTaxSum) {
        this.payableTaxSum = payableTaxSum;
    }

    public Double getPayableTaxTen() {
        return payableTaxTen;
    }

    public void setPayableTaxTen(Double payableTaxTen) {
        this.payableTaxTen = payableTaxTen;
    }

    public Double getPayableTaxFift() {
        return payableTaxFift;
    }

    public void setPayableTaxFift(Double payableTaxFift) {
        this.payableTaxFift = payableTaxFift;
    }

    public Double getPayableTaxTwtenty() {
        return payableTaxTwtenty;
    }

    public void setPayableTaxTwtenty(Double payableTaxTwtenty) {
        this.payableTaxTwtenty = payableTaxTwtenty;
    }

    public Double getPayableTaxThirty() {
        return payableTaxThirty;
    }

    public void setPayableTaxThirty(Double payableTaxThirty) {
        this.payableTaxThirty = payableTaxThirty;
    }
}