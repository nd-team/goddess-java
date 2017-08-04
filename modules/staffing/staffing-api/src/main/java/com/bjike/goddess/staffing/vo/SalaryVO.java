package com.bjike.goddess.staffing.vo;

/**
 * 工资区间表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryVO {

    /**
     * id
     */
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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