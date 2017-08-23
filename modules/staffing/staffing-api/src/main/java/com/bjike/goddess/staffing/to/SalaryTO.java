package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 工资区间
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryTO extends BaseTO {

    /**
     * 区间最低工资
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "区间最低工资不能为空")
    private Double min;

    /**
     * 区间最高工资
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "区间最高工资不能为空")
    private Double max;

    /**
     * 说明
     */
    private String rate;


    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}