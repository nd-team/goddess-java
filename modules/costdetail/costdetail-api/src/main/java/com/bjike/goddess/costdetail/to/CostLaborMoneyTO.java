package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 劳务成本
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 劳务成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostLaborMoneyTO extends BaseTO {


    /**
     * 劳务成本
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "劳务成本名不能为空")
    private String costLaborName;
    /**
     * 劳务成本合计
     */
    private Double laborCostSum;

    /**
     * 劳务成本十日
     */
    private Double laborCostTen;

    /**
     * 劳务成本十五日
     */
    private Double laborCostFift;

    /**
     * 劳务成本二十日
     */
    private Double laborCostTwtenty;

    /**
     * 劳务成本三十日
     */
    private Double laborCostThirty;

    public String getCostLaborName() {
        return costLaborName;
    }

    public void setCostLaborName(String costLaborName) {
        this.costLaborName = costLaborName;
    }

    public Double getLaborCostSum() {
        return laborCostSum;
    }

    public void setLaborCostSum(Double laborCostSum) {
        this.laborCostSum = laborCostSum;
    }

    public Double getLaborCostTen() {
        return laborCostTen;
    }

    public void setLaborCostTen(Double laborCostTen) {
        this.laborCostTen = laborCostTen;
    }

    public Double getLaborCostFift() {
        return laborCostFift;
    }

    public void setLaborCostFift(Double laborCostFift) {
        this.laborCostFift = laborCostFift;
    }

    public Double getLaborCostTwtenty() {
        return laborCostTwtenty;
    }

    public void setLaborCostTwtenty(Double laborCostTwtenty) {
        this.laborCostTwtenty = laborCostTwtenty;
    }

    public Double getLaborCostThirty() {
        return laborCostThirty;
    }

    public void setLaborCostThirty(Double laborCostThirty) {
        this.laborCostThirty = laborCostThirty;
    }
}