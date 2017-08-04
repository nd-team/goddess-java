package com.bjike.goddess.staffing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 工资区间业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryBO extends BaseBO {

    /**
     * 区间最低工资
     */
    private Double min;

    /**
     * 区间最高工资
     */
    private Double max;

    /**
     * 工资区间
     */
    private String sal;

    /**
     * 说明
     */
    private String rate;

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

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