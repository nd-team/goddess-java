package com.bjike.goddess.supplier.vo;

import java.time.LocalDate;

/**
 * 获奖情况表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:00 ]
 * @Description: [ 获奖情况表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AwardsVO {

    /**
     * id
     */
    private String id;
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
    private LocalDate forTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDate getForTime() {
        return forTime;
    }

    public void setForTime(LocalDate forTime) {
        this.forTime = forTime;
    }
}