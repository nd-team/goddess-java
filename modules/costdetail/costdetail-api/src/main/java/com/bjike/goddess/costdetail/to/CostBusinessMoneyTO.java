package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 营业成本
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 营业成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostBusinessMoneyTO extends BaseTO {


    /**
     * 营业成本
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "营业成本名不能为空")
    private String costBusinessName;
    /**
     * 营业成本合计
     */
    private Double operatingSum;

    /**
     * 营业成本十日
     */
    private Double operatingTen;

    /**
     * 营业成本十五日
     */
    private Double operatingFift;

    /**
     * 营业成本二十日
     */
    private Double operatingTwtenty;

    /**
     * 营业成本三十日
     */
    private Double operatingThirty;

    public String getCostBusinessName() {
        return costBusinessName;
    }

    public void setCostBusinessName(String costBusinessName) {
        this.costBusinessName = costBusinessName;
    }

    public Double getOperatingSum() {
        return operatingSum;
    }

    public void setOperatingSum(Double operatingSum) {
        this.operatingSum = operatingSum;
    }

    public Double getOperatingTen() {
        return operatingTen;
    }

    public void setOperatingTen(Double operatingTen) {
        this.operatingTen = operatingTen;
    }

    public Double getOperatingFift() {
        return operatingFift;
    }

    public void setOperatingFift(Double operatingFift) {
        this.operatingFift = operatingFift;
    }

    public Double getOperatingTwtenty() {
        return operatingTwtenty;
    }

    public void setOperatingTwtenty(Double operatingTwtenty) {
        this.operatingTwtenty = operatingTwtenty;
    }

    public Double getOperatingThirty() {
        return operatingThirty;
    }

    public void setOperatingThirty(Double operatingThirty) {
        this.operatingThirty = operatingThirty;
    }
}