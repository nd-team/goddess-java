package com.bjike.goddess.materialsummary.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;

/**
 * 地区调动年汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:47 ]
 * @Description: [ 地区调动年汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AreaTransferYearSumTO extends BaseTO {

    /**
     * 汇总起始时间
     */
    private String sumStartTime;

    /**
     * 汇总结束时间
     */
    private String sumEndTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门(项目组)
     */
    private String projectGroup;

    /**
     * 物资类型
     */
    private String deviceType;

    /**
     * 原存储地区总数量
     */
    private Integer oriStorageAreaTotalQty;

    /**
     * 调入地区总数量
     */
    private Integer transferredAreaTotalQty;

    /**
     * 状态
     */
    private Status status;

    public String getSumStartTime() {
        return sumStartTime;
    }

    public void setSumStartTime(String sumStartTime) {
        this.sumStartTime = sumStartTime;
    }

    public String getSumEndTime() {
        return sumEndTime;
    }

    public void setSumEndTime(String sumEndTime) {
        this.sumEndTime = sumEndTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getOriStorageAreaTotalQty() {
        return oriStorageAreaTotalQty;
    }

    public void setOriStorageAreaTotalQty(Integer oriStorageAreaTotalQty) {
        this.oriStorageAreaTotalQty = oriStorageAreaTotalQty;
    }

    public Integer getTransferredAreaTotalQty() {
        return transferredAreaTotalQty;
    }

    public void setTransferredAreaTotalQty(Integer transferredAreaTotalQty) {
        this.transferredAreaTotalQty = transferredAreaTotalQty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}