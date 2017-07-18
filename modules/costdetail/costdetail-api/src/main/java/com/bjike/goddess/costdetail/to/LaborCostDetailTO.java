package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 劳务成本明细
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LaborCostDetailTO extends BaseTO {

    /**
     * 分类名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "分类名")
    private String typeName;

    /**
     * 劳务成本合计
     */
    private Double laborCostSum;

    /**
     * 劳务成本十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "劳务成本十日")
    private Double laborCostTen;

    /**
     * 劳务成本十五日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "劳务成本十五日")
    private Double laborCostFift;

    /**
     * 劳务成本二十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "劳务成本二十日")
    private Double laborCostTwtenty;

    /**
     * 劳务成本三十日
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "劳务成本三十日")
    private Double laborCostThirty;

    /**
     * 成本明细表id
     */
    private String costId;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }
}