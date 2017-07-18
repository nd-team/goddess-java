package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 实际资金缺口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 实际资金缺口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostActualMoneyTO extends BaseTO {
    /**
     * 实际资金缺口
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "实际资金缺口名不能为空")
    private String costActualName;
    /**
     * 实际资金缺口合计
     */
    private Double actualGapSum;

    /**
     * 实际资金缺口十日
     */
    private Double actualGapTen;

    /**
     * 实际资金缺口十五日
     */
    private Double actualGapFift;

    /**
     * 实际资金缺口二十日
     */
    private Double actualGapTwtenty;

    /**
     * 实际资金缺口三十日
     */
    private Double actualGapThirty;

    public String getCostActualName() {
        return costActualName;
    }

    public void setCostActualName(String costActualName) {
        this.costActualName = costActualName;
    }

    public Double getActualGapSum() {
        return actualGapSum;
    }

    public void setActualGapSum(Double actualGapSum) {
        this.actualGapSum = actualGapSum;
    }

    public Double getActualGapTen() {
        return actualGapTen;
    }

    public void setActualGapTen(Double actualGapTen) {
        this.actualGapTen = actualGapTen;
    }

    public Double getActualGapFift() {
        return actualGapFift;
    }

    public void setActualGapFift(Double actualGapFift) {
        this.actualGapFift = actualGapFift;
    }

    public Double getActualGapTwtenty() {
        return actualGapTwtenty;
    }

    public void setActualGapTwtenty(Double actualGapTwtenty) {
        this.actualGapTwtenty = actualGapTwtenty;
    }

    public Double getActualGapThirty() {
        return actualGapThirty;
    }

    public void setActualGapThirty(Double actualGapThirty) {
        this.actualGapThirty = actualGapThirty;
    }
}