package com.bjike.goddess.budget.vo;

/**
 * 预警表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-17 10:35 ]
 * @Description: [ 预警表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WarnVO {

    /**
     * id
     */
    private String id;
    /**
     * 预警的值
     */
    private Double warnValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(Double warnValue) {
        this.warnValue = warnValue;
    }
}