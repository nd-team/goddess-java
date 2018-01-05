package com.bjike.goddess.attainment.vo;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-22 09:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SurPlanvo {
    /**
     * ID
     */
    private String id;
    /**
     * 调研计划id
     */
    private String surPlanId;
    /**
     * 调研计划的编号
     */
    private String serialNumber;

    public String getSurPlanId() {
        return surPlanId;
    }

    public void setSurPlanId(String surPlanId) {
        this.surPlanId = surPlanId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
