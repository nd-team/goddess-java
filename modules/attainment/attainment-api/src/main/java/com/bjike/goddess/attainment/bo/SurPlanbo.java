package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-07-22 09:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SurPlanbo extends BaseBO {
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
}
