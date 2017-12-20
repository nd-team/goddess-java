package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.time.LocalDate;

/**
 * 获奖情况
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:00 ]
 * @Description: [ 获奖情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardsTO extends BaseTO {

    /**
     * 供应商信息登记id
     */
    private String supplierInfoRegiId;
    /**
     * 奖励名称
     */
    private String rewardName;

    /**
     * 获得时间
     */
    private String forTime;

    public String getSupplierInfoRegiId() {
        return supplierInfoRegiId;
    }

    public void setSupplierInfoRegiId(String supplierInfoRegiId) {
        this.supplierInfoRegiId = supplierInfoRegiId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getForTime() {
        return forTime;
    }

    public void setForTime(String forTime) {
        this.forTime = forTime;
    }
}